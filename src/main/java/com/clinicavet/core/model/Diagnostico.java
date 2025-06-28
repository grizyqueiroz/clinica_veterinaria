package com.clinicavet.core.model;

public class Diagnostico {
    private String descricao;
    
    public Diagnostico(String descricao) {
        super();
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return "Diagnóstico: " + descricao;
    }
}


