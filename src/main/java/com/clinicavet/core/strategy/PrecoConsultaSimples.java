package com.clinicavet.core.strategy;

import com.clinicavet.core.model.Pet;
// Estratégia para cálculo de preço de consulta simples
public class PrecoConsultaSimples implements EstrategiaCalculoPreco {
    @Override
    public double calcularPreco(Pet pet, String servico) {
        double precoBase = 80.0; // Preço base de consulta simples
        
        // Ajuste por idade do pet
        if (pet.getIdade() > 8) {
            precoBase += 20.0; // Pets idosos precisam de mais atenção
        }
        
        // Ajuste por espécie
        if (pet.getEspecie().equalsIgnoreCase("Gato")) {
            precoBase += 10.0; // Gatos geralmente são mais difíceis de examinar
        }
        
        // Ajuste por tipo de serviço
        if (servico.contains("vacina")) {
            precoBase += 15.0; // Vacinas incluem material
        } else if (servico.contains("exame")) {
            precoBase += 30.0; // Exames incluem material e tempo
        }
        
        return precoBase;
    }
    
    @Override
    public String getNome() {
        return "Consulta Simples";
    }
} 