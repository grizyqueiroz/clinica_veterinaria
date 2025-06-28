package com.clinicavet.core.model;

// Factory para criar objetos de diagnóstico e prescrição
public class Factory {
    private static Factory instancia;
    
    private Factory() {
        super(); // Chamada explícita ao construtor da superclasse
        // Construtor privado para evitar instanciamento externo
    }
    
    public static Factory getInstancia() {
        if (instancia == null) {
            instancia = new Factory();
        }
        return instancia;
    }
    
    public Diagnostico criarDiagnostico(String resultado) {
        return new Diagnostico(resultado);
    }
    
    public Prescricao criarPrescricao(String medicamento, String dosagem) {
        return new Prescricao(medicamento, dosagem);
    }
}


