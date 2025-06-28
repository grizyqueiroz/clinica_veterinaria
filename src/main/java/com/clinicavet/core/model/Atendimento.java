package com.clinicavet.core.model;

public class Atendimento {
    private Pet pet;
    private String data;
    private String descricao;

    private Atendimento(Pet pet, String data, String descricao) {
        super(); // Chamada explícita ao construtor da superclasse
        this.pet = pet;
        this.data = data;
        this.descricao = descricao;
    }

    // Getters para acesso aos dados
    public Pet getPet() {
        return pet;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public static class AtendimentoBuilder {
        private Pet pet;
        private String data;
        private String descricao;

        public AtendimentoBuilder() {
            super(); // Chamada explícita ao construtor da superclasse
        }

        public AtendimentoBuilder setPet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public AtendimentoBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public AtendimentoBuilder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Atendimento build() {
            return new Atendimento(pet, data, descricao);
        }
    }

    @Override
    public String toString() {
        return "Atendimento\n" +
           "   Pet       : " + pet + "\n" +
           "   Data      : " + data + "\n" +
           "   Descricao : " + descricao;
    }
}


