package com.clinicavet.core.model;

public class Consulta {
    private Pet pet;
    private String data;
    private String motivo;
    private String veterinario;
    
    private Consulta(ConsultaBuilder builder) {
        super(); // Chamada explícita ao construtor da superclasse
        this.pet = builder.pet;
        this.data = builder.data;
        this.motivo = builder.motivo;
        this.veterinario = builder.veterinario;
    }
    
    // Getters para acesso aos dados
    public Pet getPet() {
        return pet;
    }
    
    public String getData() {
        return data;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public String getVeterinario() {
        return veterinario;
    }
    
    public static class ConsultaBuilder {
        private Pet pet;
        private String data;
        private String motivo;
        private String veterinario;
        
        public ConsultaBuilder() {
            super(); // Chamada explícita ao construtor da superclasse
        }
        
        public ConsultaBuilder setPet(Pet pet) {
            this.pet = pet;
            return this;
        }
        
        public ConsultaBuilder setData(String data) {
            this.data = data;
            return this;
        }
        
        public ConsultaBuilder setMotivo(String motivo) {
            this.motivo = motivo;
            return this;
        }
        
        public ConsultaBuilder setVeterinario(String veterinario) {
            this.veterinario = veterinario;
            return this;
        }
        
        public Consulta build() {
            return new Consulta(this);
        }
    }
    
    @Override
    public String toString() {
        return "Consulta para " + pet.toString() + " com " + veterinario + " em " + data + " devido a " + motivo;
    }
}


