package com.clinicavet.core.notification;

import com.clinicavet.core.model.Pet;
// Observer para veterinários
public class VeterinarioObserver implements Observer {
    private String nome;
    
    public VeterinarioObserver(String nome) {
        super(); // Chamada explícita ao construtor da superclasse
        this.nome = nome;
    }
    @Override
    public void atualizar(String mensagem, Object dados) {
        switch (mensagem) {
            case "PET_CHEGOU":
                Pet pet = (Pet) dados;
                System.out.println("Dr. " + nome + ": Pet " + pet.getNome() + " chegou para consulta!");
                break;
                
            case "VACINA_VENCENDO":
                Object[] vacinaData = (Object[]) dados;
                Pet petVacina = (Pet) vacinaData[0];
                String vacina = (String) vacinaData[1];
                System.out.println("Dr. " + nome + ": Pet " + petVacina.getNome() + " tem vacina " + vacina + " vencendo!");
                break;
                
            case "RESULTADO_EXAME":
                Object[] exameData = (Object[]) dados;
                Pet petExame = (Pet) exameData[0];
                String resultado = (String) exameData[1];
                System.out.println("Dr. " + nome + ": Resultado do exame do pet " + petExame.getNome() + ": " + resultado);
                break;
        }
    }
}


