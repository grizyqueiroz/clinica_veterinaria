package com.clinicavet.core.command;

import com.clinicavet.core.model.Consulta;
import com.clinicavet.core.notification.SistemaNotificacoes;

// Comando para reagendar consulta
public class ReagendarConsultaCommand implements Command {
    private AgendarConsultaCommand comandoOriginal;
    private String novaData;
    private String novoVeterinario;
    private String dataAnterior;
    private String veterinarioAnterior;
    
    public ReagendarConsultaCommand(AgendarConsultaCommand comandoOriginal, String novaData, String novoVeterinario) {
        super(); // Chamada explícita ao construtor da superclasse
        this.comandoOriginal = comandoOriginal;
        this.novaData = novaData;
        this.novoVeterinario = novoVeterinario;
        this.dataAnterior = comandoOriginal.getConsulta().getData();
        this.veterinarioAnterior = comandoOriginal.getConsulta().getVeterinario();
    }
    
    @Override
    public void executar() {
        if (comandoOriginal.isExecutado()) {
            // Atualizar a consulta com novos dados
            @SuppressWarnings("unused")
            Consulta consultaAtualizada = new Consulta.ConsultaBuilder()
                    .setPet(comandoOriginal.getConsulta().getPet())
                    .setData(novaData)
                    .setMotivo(comandoOriginal.getConsulta().getMotivo())
                    .setVeterinario(novoVeterinario)
                    .build();
            
            System.out.println("Consulta reagendada: " + getDescricao());
            // Notificar sobre reagendamento
            SistemaNotificacoes.getInstancia().notificarPetChegou(comandoOriginal.getConsulta().getPet());
        } else {
            System.out.println("Consulta original nao foi agendada ainda");
        }
    }
    
    @Override
    public void desfazer() {
        // Voltar para a data e veterinário anteriores
        @SuppressWarnings("unused")
        Consulta consultaOriginal = new Consulta.ConsultaBuilder()
                .setPet(comandoOriginal.getConsulta().getPet())
                .setData(dataAnterior)
                .setMotivo(comandoOriginal.getConsulta().getMotivo())
                .setVeterinario(veterinarioAnterior)
                .build();
        
        System.out.println("Reagendamento desfeito: voltando para " + dataAnterior + " com " + veterinarioAnterior);
    }
    
    @Override
    public String getDescricao() {
        return "Reagendar consulta de " + comandoOriginal.getConsulta().getPet().getNome() + 
               " de " + dataAnterior + " para " + novaData + 
               " (veterinário: " + veterinarioAnterior + " -> " + novoVeterinario + ")";
    }
}


