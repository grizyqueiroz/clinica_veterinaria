package com.clinicavet.core.facade;

import java.util.List;
import com.clinicavet.core.model.Factory;
import com.clinicavet.core.model.Pet;
import com.clinicavet.core.model.Atendimento;
import com.clinicavet.core.model.Diagnostico;
import com.clinicavet.core.model.Prescricao;
import com.clinicavet.core.model.LogSistema;
import com.clinicavet.core.dao.PetDAO;
import com.clinicavet.core.dao.AtendimentoDAO;
import com.clinicavet.core.adapter.SistemaPagamento;
import com.clinicavet.core.adapter.AdapterPagamento;
import com.clinicavet.core.notification.SistemaNotificacoes;
import com.clinicavet.core.notification.VeterinarioObserver;
import com.clinicavet.core.notification.ClienteObserver;
import com.clinicavet.core.strategy.CalculadoraPreco;
import com.clinicavet.core.strategy.PrecoEmergencia;
import com.clinicavet.core.strategy.PrecoEspecializada;
import com.clinicavet.core.strategy.PrecoConsultaSimples;
import com.clinicavet.core.command.Command;
import com.clinicavet.core.command.CommandProcessor;
import com.clinicavet.core.command.AgendarConsultaCommand;
import com.clinicavet.core.command.ReagendarConsultaCommand;

public class ClinicaFacade {
    private static ClinicaFacade instancia;
    private Factory factory;
    private PetDAO petDAO;
    private AtendimentoDAO atendimentoDAO;
    private LogSistema logger;
    private SistemaPagamento sistemaPagamento;
    private SistemaNotificacoes sistemaNotificacoes;
    private CalculadoraPreco calculadoraPreco;
    private CommandProcessor commandProcessor;

    private ClinicaFacade() {
        super(); // Chamada explícita ao construtor da superclasse
        this.factory = Factory.getInstancia();
        this.petDAO = PetDAO.getInstancia();
        this.atendimentoDAO = AtendimentoDAO.getInstancia();
        this.logger = LogSistema.getInstancia();
        this.sistemaPagamento = AdapterPagamento.getInstancia();
        this.sistemaNotificacoes = SistemaNotificacoes.getInstancia();
        this.calculadoraPreco = new CalculadoraPreco();
        this.commandProcessor = CommandProcessor.getInstancia();
        
        // Configurar observers padrão
        configurarObservers();
    }

    public static ClinicaFacade getInstancia() {
        if (instancia == null) {
            instancia = new ClinicaFacade();
        }
        return instancia;
    }

    // Configurar observers padrão
    private void configurarObservers() {
        sistemaNotificacoes.adicionarObserver(new VeterinarioObserver("Silva"));
        sistemaNotificacoes.adicionarObserver(new VeterinarioObserver("Santos"));
        sistemaNotificacoes.adicionarObserver(new ClienteObserver("Joao"));
        sistemaNotificacoes.adicionarObserver(new ClienteObserver("Maria"));
    }

    // Metodo simplificado para cadastrar pet
    public Pet cadastrarPet(String nome, String especie, int idade) {
        logger.registrar("Cadastrando pet: " + nome);
        
        Pet pet = new Pet.PetBuilder()
                .setNome(nome)
                .setEspecie(especie)
                .setIdade(idade)
                .build();
        
        petDAO.salvar(pet);
        logger.registrar("Pet cadastrado com sucesso: " + nome);
        
        return pet;
    }

    // Metodo simplificado para criar atendimento completo
    public Atendimento criarAtendimentoCompleto(String nomePet, String data, String descricao, 
                                               String diagnostico, String medicamento, String dosagem) {
        logger.registrar("Criando atendimento completo para: " + nomePet);
        
        // Busca o pet no DAO
        Pet pet = petDAO.buscarPorNome(nomePet);
        if (pet == null) {
            logger.registrar("ERRO: Pet nao encontrado: " + nomePet);
            return null;
        }

        // Cria o atendimento usando Builder
        Atendimento atendimento = new Atendimento.AtendimentoBuilder()
                .setPet(pet)
                .setData(data)
                .setDescricao(descricao)
                .build();

        // Cria diagnostico e prescricao usando Factory
        @SuppressWarnings("unused")
        Diagnostico diag = factory.criarDiagnostico(diagnostico);
        @SuppressWarnings("unused")
        Prescricao presc = factory.criarPrescricao(medicamento, dosagem);

        // Salva no DAO
        atendimentoDAO.salvar(atendimento);
        
        logger.registrar("Atendimento completo criado com sucesso");
        
        return atendimento;
    }

    // NOVO: Metodo para agendar consulta usando Command
    public void agendarConsulta(String nomePet, String data, String veterinario, String motivo) {
        logger.registrar("Agendando consulta para: " + nomePet);
        
        Pet pet = petDAO.buscarPorNome(nomePet);
        if (pet == null) {
            logger.registrar("ERRO: Pet nao encontrado: " + nomePet);
            return;
        }

        Command comando = new AgendarConsultaCommand(pet, data, veterinario, motivo);
        commandProcessor.processar(comando);
        
        logger.registrar("Comando de agendamento processado");
    }

    // NOVO: Metodo para reagendar consulta
    public void reagendarConsulta(String nomePet, String novaData, String novoVeterinario) {
        logger.registrar("Reagendando consulta para: " + nomePet);
        
        // Buscar comando original no historico
        List<Command> historico = commandProcessor.getHistoricoComandos();
        for (Command comando : historico) {
            if (comando instanceof AgendarConsultaCommand) {
                AgendarConsultaCommand cmd = (AgendarConsultaCommand) comando;
                if (cmd.getConsulta() != null && cmd.getConsulta().getPet().getNome().equals(nomePet)) {
                    Command comandoReagendamento = new ReagendarConsultaCommand(cmd, novaData, novoVeterinario);
                    commandProcessor.processar(comandoReagendamento);
                    return;
                }
            }
        }
        
        logger.registrar("ERRO: Consulta nao encontrada para reagendamento");
    }

    // NOVO: Metodo para calcular preco usando Strategy
    public double calcularPreco(String nomePet, String tipoConsulta, String estrategia) {
        logger.registrar("Calculando preco para: " + nomePet + " - " + tipoConsulta);
        
        Pet pet = petDAO.buscarPorNome(nomePet);
        if (pet == null) {
            logger.registrar("ERRO: Pet nao encontrado: " + nomePet);
            return 0.0;
        }

        // Definir estrategia baseada no tipo
        switch (estrategia.toLowerCase()) {
            case "emergencia":
                calculadoraPreco.setEstrategia(new PrecoEmergencia());
                break;
            case "especializada":
                calculadoraPreco.setEstrategia(new PrecoEspecializada());
                break;
            default:
                calculadoraPreco.setEstrategia(new PrecoConsultaSimples());
        }

        double preco = calculadoraPreco.calcularPreco(pet, tipoConsulta);
        logger.registrar("Preco calculado: R$ " + preco + " usando " + calculadoraPreco.getEstrategiaAtual());
        
        return preco;
    }

    // NOVO: Metodo para notificar sobre vacinas vencendo
    public void notificarVacinaVencendo(String nomePet, String vacina) {
        logger.registrar("Notificando sobre vacina vencendo: " + nomePet);
        
        Pet pet = petDAO.buscarPorNome(nomePet);
        if (pet != null) {
            sistemaNotificacoes.notificarVacinaVencendo(pet, vacina);
        }
    }

    // NOVO: Metodo para notificar sobre resultados de exames
    public void notificarResultadoExame(String nomePet, String resultado) {
        logger.registrar("Notificando sobre resultado de exame: " + nomePet);
        
        Pet pet = petDAO.buscarPorNome(nomePet);
        if (pet != null) {
            sistemaNotificacoes.notificarResultadoExame(pet, resultado);
        }
    }

    // NOVO: Metodo para desfazer ultimo comando
    public void desfazerUltimoComando() {
        commandProcessor.desfazerUltimo();
    }

    // NOVO: Metodo para refazer comando
    public void refazerComando() {
        commandProcessor.refazer();
    }

    // NOVO: Metodo para mostrar historico de comandos
    public void mostrarHistoricoComandos() {
        commandProcessor.mostrarHistorico();
    }

    // Metodo para processar pagamento usando Adapter
    public boolean processarPagamentoConsulta(double valor, String nomePet, String tipoConsulta) {
        logger.registrar("Processando pagamento para consulta do pet: " + nomePet);
        
        String descricao = "Consulta " + tipoConsulta + " - Pet: " + nomePet;
        boolean sucesso = sistemaPagamento.processarPagamento(valor, descricao);
        
        if (sucesso) {
            logger.registrar("Pagamento processado com sucesso: R$ " + valor);
        } else {
            logger.registrar("ERRO: Falha no processamento do pagamento");
        }
        
        return sucesso;
    }

    // Metodo para gerar recibo
    public String gerarReciboPagamento(String transacaoId) {
        logger.registrar("Gerando recibo para transacao: " + transacaoId);
        return sistemaPagamento.gerarRecibo(transacaoId);
    }

    // Metodo para relatorio simplificado usando DAOs
    public void gerarRelatorio() {
        logger.registrar("Gerando relatorio da clinica");
        
        System.out.println("=== RELATORIO DA CLINICA ===");
        System.out.println("Total de Pets: " + petDAO.buscarTodos().size());
        System.out.println("Total de Atendimentos: " + atendimentoDAO.buscarTodos().size());
        
        for (Pet pet : petDAO.buscarTodos()) {
            System.out.println("- " + pet.toString());
        }
        
        logger.registrar("Relatorio gerado com sucesso");
    }

    // Metodo para buscar historico de atendimentos de um pet
    public void buscarHistoricoPet(String nomePet) {
        logger.registrar("Buscando historico do pet: " + nomePet);
        
        List<Atendimento> atendimentos = atendimentoDAO.buscarPorPet(nomePet);
        
        System.out.println("=== HISTORICO DO PET: " + nomePet + " ===");
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento encontrado.");
        } else {
            for (Atendimento atendimento : atendimentos) {
                System.out.println("- " + atendimento.toString());
            }
        }
        
        logger.registrar("Historico consultado com sucesso");
    }

    // Metodo auxiliar para buscar pet (mantido para compatibilidade)
    @SuppressWarnings("unused")
    private Pet buscarPetPorNome(String nome) {
        return petDAO.buscarPorNome(nome);
    }

    // Metodos getter para acesso direto aos DAOs (usado pelos controllers web)
    public PetDAO getPetDAO() {
        return petDAO;
    }

    public AtendimentoDAO getAtendimentoDAO() {
        return atendimentoDAO;
    }
} 


