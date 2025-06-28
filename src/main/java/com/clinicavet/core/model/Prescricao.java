package com.clinicavet.core.model;

public class Prescricao {
    private String medicamento;
    private String dosagem;
    
    public Prescricao(String medicamento, String dosagem) {
        super();
        this.medicamento = medicamento;
        this.dosagem = dosagem;
    }
    
    public String getMedicamento() {
        return medicamento;
    }
    
    public String getDosagem() {
        return dosagem;
    }
    
    @Override
    public String toString() {
        return "Prescrição: " + medicamento + " - " + dosagem;
    }
}


