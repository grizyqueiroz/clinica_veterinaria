package com.clinicavet.core.command;

import java.util.UUID;
import com.clinicavet.core.model.Pet;
import com.clinicavet.core.model.Consulta;
import com.clinicavet.core.notification.SistemaNotificacoes;

// Comando para agendar consulta
public class AgendarConsultaCommand implements Command {
    private String id;
    private Pet pet;
    private String data;
    private String veterinario;
    private String motivo;
    private boolean executado;
    private Consulta consultaAgendada;
    
    public AgendarConsultaCommand(Pet pet, String data, String veterinario, String motivo) {
        super(); // Chamada explícita ao construtor da superclasse
        this.id = UUID.randomUUID().toString();
        this.pet = pet;
        this.data = data;
        this.veterinario = veterinario;
        this.motivo = motivo;
        this.executado = false;
    }
    
    @Override
    public void executar() {
        if (!executado) {
            consultaAgendada = new Consulta.ConsultaBuilder()
                    .setPet(pet)
                    .setData(data)
                    .setMotivo(motivo)
                    .setVeterinario(veterinario)
                    .build();
            
            executado = true;
            System.out.println("Consulta agendada: " + getDescricao());
            // Notificar veterinario sobre nova consulta
            SistemaNotificacoes.getInstancia().notificarPetChegou(pet);
        } else {
            System.out.println("Comando ja foi executado");
        }
    }
    
    @Override
    public void desfazer() {
        if (executado) {
            executado = false;
            consultaAgendada = null;
            System.out.println("Consulta cancelada: " + getDescricao());
        } else {
            System.out.println("Comando nao foi executado ainda");
        }
    }
    
    @Override
    public String getDescricao() {
        return "Agendar consulta para " + pet.getNome() + " com " + veterinario + " em " + data + " - " + motivo;
    }
    
    public String getId() {
        return id;
    }
    
    public Consulta getConsulta() {
        return consultaAgendada;
    }
    
    public boolean isExecutado() {
        return executado;
    }
} 


