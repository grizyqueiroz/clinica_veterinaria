package com.clinicavet.core.strategy;

import com.clinicavet.core.model.Pet;

// Calculadora de preços usando Strategy Pattern
public class CalculadoraPreco {
    private EstrategiaCalculoPreco estrategia;
    
    // Estratégia padrão
    public CalculadoraPreco() {
        super(); // Chamada explícita ao construtor da superclasse
        this.estrategia = new PrecoConsultaSimples();
    }
    
    public void setEstrategia(EstrategiaCalculoPreco estrategia) {
        this.estrategia = estrategia;
    }
    
    public double calcularPreco(Pet pet, String servico) {
        return estrategia.calcularPreco(pet, servico);
    }
    
    public String getEstrategiaAtual() {
        return estrategia.getNome();
    }
} 


