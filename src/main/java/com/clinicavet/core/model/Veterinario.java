package com.clinicavet.core.model;

public class Veterinario {
    private String nome;
    private String especialidade;
    
    public Veterinario(String nome, String especialidade) {
        super();
        this.nome = nome;
        this.especialidade = especialidade;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public Diagnostico emitirDiagnostico(String descricao) {
        return new Diagnostico(descricao);
    }
    
    @Override
    public String toString() {
        return "Veterinário: " + nome + " - " + especialidade;
    }
}


