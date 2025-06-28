package com.clinicavet.core.command;

// Interface Command para o padrão Command
public interface Command {
    void executar();
    void desfazer();
    String getDescricao();
} 


