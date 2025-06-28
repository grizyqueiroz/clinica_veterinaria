package com.clinicavet.core.strategy;

import com.clinicavet.core.model.Pet;
// Estratégia para cálculo de preço de emergência
public class PrecoEmergencia implements EstrategiaCalculoPreco {
    @Override
    public double calcularPreco(Pet pet, String servico) {
        double precoBase = 200.0; // Preço base de emergência
        
        // Ajuste por urgência (horário noturno, fim de semana)
        if (servico.contains("noturno") || servico.contains("fim de semana")) {
            precoBase += 100.0;
        }
        // Ajuste por tipo de emergência
        if (servico.contains("cirurgia")) {
            precoBase += 300.0;
        } else if (servico.contains("fratura")) {
            precoBase += 200.0;
        } else if (servico.contains("intoxicação")) {
            precoBase += 150.0;
        }
        // Ajuste por idade do pet
        if (pet.getIdade() > 10) {
            precoBase += 50.0; // Pets idosos precisam de mais cuidados
        }
        return precoBase;
    }
    
    @Override
    public String getNome() {
        return "Emergência";
    }
}


