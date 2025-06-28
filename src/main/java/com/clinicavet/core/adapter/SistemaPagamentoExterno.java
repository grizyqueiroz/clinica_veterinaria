package com.clinicavet.core.adapter;

// Sistema de pagamento externo com interface diferente
public class SistemaPagamentoExterno {
    @SuppressWarnings("unused")
    private String apiKey;
    
    public SistemaPagamentoExterno(String apiKey) {
        super(); // Chamada explícita ao construtor da superclasse
        this.apiKey = apiKey;
    }
    
    // Método com interface diferente da nossa
    public String realizarCobranca(double amount, String description, String customerId) {
        System.out.println("Conectando com sistema externo...");
        System.out.println("Processando pagamento de R$ " + amount + " - " + description);
        System.out.println("Cliente: " + customerId);
        
        // Simula processamento
        try {
            Thread.sleep(1000); // Simula delay de rede
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        String transactionId = "TXN_" + System.currentTimeMillis();
        System.out.println("Pagamento processado. ID: " + transactionId);
        return transactionId;
    }
    
    // Método para obter recibo com interface diferente
    public String obterComprovante(String transactionId) {
        System.out.println("Gerando recibo para transação: " + transactionId);
        return "RECEIPT_" + transactionId + "_" + System.currentTimeMillis();
    }
    
    // Método para verificar status
    public boolean verificarStatus(String transactionId) {
        return true; // Simula sucesso
    }
}


