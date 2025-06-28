package com.clinicavet.core.model;

public class LogSistema {
    private static LogSistema instancia;
    
    private LogSistema() {
        super(); // Chamada explícita ao construtor da superclasse
    }
    
    public static LogSistema getInstancia() {
        if (instancia == null) {
            instancia = new LogSistema();
        }
        return instancia;
    }
    
    public void registrar(String mensagem) {
        System.out.println("[LOG] " + mensagem);
    }
}


