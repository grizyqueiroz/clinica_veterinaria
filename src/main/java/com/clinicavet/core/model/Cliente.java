package com.clinicavet.core.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String telefone;
    private List<Pet> pets;
    
    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.pets = new ArrayList<>();
    }
    
    public void adicionarPet(Pet pet) {
        pets.add(pet);
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public List<Pet> getPets() {
        return pets;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + nome + " - " + telefone;
    }
}


