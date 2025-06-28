package com.clinicavet.core.strategy;

import com.clinicavet.core.model.Pet;
// Interface para estrategias de calculo de preco
public interface EstrategiaCalculoPreco {
    double calcularPreco(Pet pet, String servico);
    String getNome();
} 


