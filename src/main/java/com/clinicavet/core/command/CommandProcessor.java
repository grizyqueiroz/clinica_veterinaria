package com.clinicavet.core.command;

import java.util.ArrayList;
import java.util.List;
// Processador de comandos usando Command Pattern
public class CommandProcessor {
    private static CommandProcessor instancia;
    private List<Command> historicoComandos;
    private List<Command> comandosDesfeitos;
    
    private CommandProcessor() {
        super(); // Chamada explícita ao construtor da superclasse
        this.historicoComandos = new ArrayList<>();
        this.comandosDesfeitos = new ArrayList<>();
    }
    public static CommandProcessor getInstancia() {
        if (instancia == null) {
            instancia = new CommandProcessor();
        }
        return instancia;
    }
    public void processar(Command comando) {
        comando.executar();
        historicoComandos.add(comando);
        System.out.println("Comando adicionado ao historico: " + comando.getDescricao());
    }
    public void desfazerUltimo() {
        if (!historicoComandos.isEmpty()) {
            Command ultimoComando = historicoComandos.remove(historicoComandos.size() - 1);
            ultimoComando.desfazer();
            comandosDesfeitos.add(ultimoComando);
            System.out.println("Ultimo comando desfeito: " + ultimoComando.getDescricao());
        } else {
            System.out.println("Nenhum comando para desfazer");
        }
    }
    public void refazer() {
        if (!comandosDesfeitos.isEmpty()) {
            Command comandoRefazer = comandosDesfeitos.remove(comandosDesfeitos.size() - 1);
            comandoRefazer.executar();
            historicoComandos.add(comandoRefazer);
            System.out.println("Comando refeito: " + comandoRefazer.getDescricao());
        } else {
            System.out.println("Nenhum comando para refazer");
        }
    }
    public void mostrarHistorico() {
        System.out.println("=== HISTORICO DE COMANDOS ===");
        for (int i = 0; i < historicoComandos.size(); i++) {
            Command comando = historicoComandos.get(i);
            System.out.println((i + 1) + ". " + comando.getDescricao());
        }
    }
    public List<Command> getHistoricoComandos() {
        return new ArrayList<>(historicoComandos);
    }
} 


