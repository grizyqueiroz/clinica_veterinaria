package com.clinicavet.core.model;

public class Pet {
    private String nome;
    private String especie;
    private int idade;

    private Pet(String nome, String especie, int idade) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
    }

    // Getters para acesso aos dados
    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public int getIdade() {
        return idade;
    }

    public static class PetBuilder {
        private String nome;
        private String especie;
        private int idade;

        public PetBuilder() {
        }

        public PetBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public PetBuilder setEspecie(String especie) {
            this.especie = especie;
            return this;
        }

        public PetBuilder setIdade(int idade) {
            this.idade = idade;
            return this;
        }

        public Pet build() {
            return new Pet(nome, especie, idade);
        }
    }

    @Override
    public String toString() {
        return "Pet\n" +
           "   Nome     : " + nome + "\n" +
           "   Especie  : " + especie + "\n" +
           "   Idade    : " + idade + " anos";
    }
}


