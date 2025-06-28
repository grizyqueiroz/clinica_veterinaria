package com.clinicavet.core.notification;

import com.clinicavet.core.model.Pet;
// Observer para clientes
public class ClienteObserver implements Observer {
    private String nomeCliente;
    
    public ClienteObserver(String nomeCliente) {
        super(); // Chamada explícita ao construtor da superclasse
        this.nomeCliente = nomeCliente;
    }
    @Override
    public void atualizar(String mensagem, Object dados) {
        switch (mensagem) {
            case "RESULTADO_EXAME":
                Object[] exameData = (Object[]) dados;
                Pet petExame = (Pet) exameData[0];
                String resultado = (String) exameData[1];
                System.out.println("Cliente " + nomeCliente + ": Resultado do exame do " + petExame.getNome() + " está pronto: " + resultado);
                break;
                
            case "VACINA_VENCENDO":
                Object[] vacinaData = (Object[]) dados;
                Pet petVacina = (Pet) vacinaData[0];
                String vacina = (String) vacinaData[1];
                System.out.println("Cliente " + nomeCliente + ": Lembrete - " + petVacina.getNome() + " precisa renovar a vacina " + vacina);
                break;
        }
    }
}


