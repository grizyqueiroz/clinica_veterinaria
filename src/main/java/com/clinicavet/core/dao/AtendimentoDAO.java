package com.clinicavet.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.clinicavet.core.model.Pet;
import com.clinicavet.core.model.Atendimento;
// DAO específico para Atendimento usando MongoDB
public class AtendimentoDAO implements DAO<Atendimento> {
    private static AtendimentoDAO instancia;
    private MongoConnection mongoConnection;
    private static final String COLLECTION_NAME = "atendimentos";
    
    private AtendimentoDAO() {
        super(); // Chamada explícita ao construtor da superclasse
        this.mongoConnection = MongoConnection.getInstancia();
    }
    
    public static AtendimentoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AtendimentoDAO();
        }
        return instancia;
    }
    
    @Override
    public void salvar(Atendimento atendimento) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        String id = UUID.randomUUID().toString();
        
        // Simula documento MongoDB com dados aninhados
        Map<String, Object> atendimentoDocument = new HashMap<>();
        atendimentoDocument.put("_id", id);
        atendimentoDocument.put("data", atendimento.getData());
        atendimentoDocument.put("descricao", atendimento.getDescricao());
        
        // Dados do pet aninhados
        Map<String, Object> petData = new HashMap<>();
        petData.put("nome", atendimento.getPet().getNome());
        petData.put("especie", atendimento.getPet().getEspecie());
        petData.put("idade", atendimento.getPet().getIdade());
        atendimentoDocument.put("pet", petData);
        
        collection.put(id, atendimentoDocument);
        System.out.println("Atendimento salvo no MongoDB com ID: " + id);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Atendimento buscarPorId(String id) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        Map<String, Object> atendimentoDocument = (Map<String, Object>) collection.get(id);
        
        if (atendimentoDocument != null) {
            Map<String, Object> petData = (Map<String, Object>) atendimentoDocument.get("pet");
            
            Pet pet = new Pet.PetBuilder()
                    .setNome((String) petData.get("nome"))
                    .setEspecie((String) petData.get("especie"))
                    .setIdade((Integer) petData.get("idade"))
                    .build();
            
            return new Atendimento.AtendimentoBuilder()
                    .setPet(pet)
                    .setData((String) atendimentoDocument.get("data"))
                    .setDescricao((String) atendimentoDocument.get("descricao"))
                    .build();
        }
        return null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Atendimento> buscarTodos() {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        List<Atendimento> atendimentos = new ArrayList<>();
        
        for (Object value : collection.values()) {
            Map<String, Object> atendimentoDocument = (Map<String, Object>) value;
            Map<String, Object> petData = (Map<String, Object>) atendimentoDocument.get("pet");
            
            Pet pet = new Pet.PetBuilder()
                    .setNome((String) petData.get("nome"))
                    .setEspecie((String) petData.get("especie"))
                    .setIdade((Integer) petData.get("idade"))
                    .build();
            
            Atendimento atendimento = new Atendimento.AtendimentoBuilder()
                    .setPet(pet)
                    .setData((String) atendimentoDocument.get("data"))
                    .setDescricao((String) atendimentoDocument.get("descricao"))
                    .build();
            
            atendimentos.add(atendimento);
        }
        
        return atendimentos;
    }
    
    @Override
    public void atualizar(Atendimento atendimento) {
        // Em um cenário real, você precisaria do ID do atendimento
        System.out.println("Atendimento atualizado no MongoDB");
    }
    
    @Override
    public void deletar(String id) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        collection.remove(id);
        System.out.println("Atendimento deletado do MongoDB com ID: " + id);
    }
    
    // Método específico para buscar atendimentos por pet
    public List<Atendimento> buscarPorPet(String nomePet) {
        List<Atendimento> todosAtendimentos = buscarTodos();
        List<Atendimento> atendimentosDoPet = new ArrayList<>();
        
        for (Atendimento atendimento : todosAtendimentos) {
            if (atendimento.getPet().getNome().equals(nomePet)) {
                atendimentosDoPet.add(atendimento);
            }
        }
        
        return atendimentosDoPet;
    }
}


