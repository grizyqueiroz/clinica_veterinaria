package com.clinicavet.core.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.clinicavet.core.model.Pet;
// DAO específico para Pet usando MongoDB
public class PetDAO implements DAO<Pet> {
    private static PetDAO instancia;
    private MongoConnection mongoConnection;
    private static final String COLLECTION_NAME = "pets";
    
    private PetDAO() {
        super(); // Chamada explícita ao construtor da superclasse
        this.mongoConnection = MongoConnection.getInstancia();
    }
    
    public static PetDAO getInstancia() {
        if (instancia == null) {
            instancia = new PetDAO();
        }
        return instancia;
    }
    
    @Override
    public void salvar(Pet pet) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        String id = UUID.randomUUID().toString();
        
        // Simula documento MongoDB
        Map<String, Object> petDocument = new HashMap<>();
        petDocument.put("_id", id);
        petDocument.put("nome", pet.getNome());
        petDocument.put("especie", pet.getEspecie());
        petDocument.put("idade", pet.getIdade());
        
        collection.put(id, petDocument);
        System.out.println("Pet salvo no MongoDB com ID: " + id);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Pet buscarPorId(String id) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        Map<String, Object> petDocument = (Map<String, Object>) collection.get(id);
        
        if (petDocument != null) {
            return new Pet.PetBuilder()
                    .setNome((String) petDocument.get("nome"))
                    .setEspecie((String) petDocument.get("especie"))
                    .setIdade((Integer) petDocument.get("idade"))
                    .build();
        }
        return null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Pet> buscarTodos() {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        List<Pet> pets = new ArrayList<>();
        
        for (Object value : collection.values()) {
            Map<String, Object> petDocument = (Map<String, Object>) value;
            Pet pet = new Pet.PetBuilder()
                    .setNome((String) petDocument.get("nome"))
                    .setEspecie((String) petDocument.get("especie"))
                    .setIdade((Integer) petDocument.get("idade"))
                    .build();
            pets.add(pet);
        }
        
        return pets;
    }
    
    @Override
    public void atualizar(Pet pet) {
        // Em um cenário real, você precisaria do ID do pet
        System.out.println("Pet atualizado no MongoDB");
    }
    
    @Override
    public void deletar(String id) {
        Map<String, Object> collection = mongoConnection.getCollection(COLLECTION_NAME);
        collection.remove(id);
        System.out.println("Pet deletado do MongoDB com ID: " + id);
    }
    
    // Método específico para buscar por nome
    public Pet buscarPorNome(String nome) {
        List<Pet> pets = buscarTodos();
        for (Pet pet : pets) {
            if (pet.getNome().equals(nome)) {
                return pet;
            }
        }
        return null;
    }
}


