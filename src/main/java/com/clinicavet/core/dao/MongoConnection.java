package com.clinicavet.core.dao;

import java.util.HashMap;
import java.util.Map;
// Simulação do MongoDB para demonstração do padrão DAO
public class MongoConnection {
    private static MongoConnection instancia;
    private Map<String, Map<String, Object>> collections;
    
    private MongoConnection() {
        super(); // Chamada explícita ao construtor da superclasse
        this.collections = new HashMap<>();
        System.out.println("Conexão com MongoDB (simulada) estabelecida");
    }
    
    public static MongoConnection getInstancia() {
        if (instancia == null) {
            instancia = new MongoConnection();
        }
        return instancia;
    }
    
    public Map<String, Object> getCollection(String collectionName) {
        if (!collections.containsKey(collectionName)) {
            collections.put(collectionName, new HashMap<>());
        }
        return collections.get(collectionName);
    }
    
    public void fecharConexao() {
        System.out.println("Conexão com MongoDB (simulada) fechada");
    }
}


