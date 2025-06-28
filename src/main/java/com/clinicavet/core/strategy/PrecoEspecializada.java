package com.clinicavet.core.strategy;

import com.clinicavet.core.model.Pet;
// Estratégia para cálculo de preço de consulta especializada
public class PrecoEspecializada implements EstrategiaCalculoPreco {
    
    @Override
    public double calcularPreco(Pet pet, String tipoConsulta) {
        double precoBase = 150.0; // Preço base de especialista
        
        // Ajuste por especialidade
        if (tipoConsulta.contains("cardiologia")) {
            precoBase += 100.0;
        } else if (tipoConsulta.contains("dermatologia")) {
            precoBase += 80.0;
        } else if (tipoConsulta.contains("ortopedia")) {
            precoBase += 120.0;
        }
        
        // Ajuste por complexidade do caso
        if (tipoConsulta.contains("exame")) {
            precoBase += 50.0;
        }
        
        return precoBase;
    }
    
    @Override
    public String getNome() {
        return "Consulta Especializada";
    }
}


