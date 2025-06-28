package com.clinicavet.core.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeDados {
    private static RepositorioDeDados instancia;
    private List<Pet> pets = new ArrayList<>();
    private List<Atendimento> atendimentos = new ArrayList<>();
    
    private RepositorioDeDados() {
        super(); // Chamada explícita ao construtor da superclasse
    }
    
    public static RepositorioDeDados getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioDeDados();
        }
        return instancia;
    }
    
    public void adicionarPet(Pet pet) {
        pets.add(pet);
    }
    
    public void adicionarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }
    
    public List<Pet> getTodosOsPets() {
        return pets;
    }
    
    public List<Atendimento> getTodosOsAtendimentos() {
        return atendimentos;
    }
}


