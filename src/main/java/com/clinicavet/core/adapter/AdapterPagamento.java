package com.clinicavet.core.adapter;

// Adapter que converte a interface do sistema externo
public class AdapterPagamento implements SistemaPagamento {
    private SistemaPagamentoExterno sistemaExterno;
    private static AdapterPagamento instancia;
    
    private AdapterPagamento() {
        this.sistemaExterno = new SistemaPagamentoExterno("API_KEY_12345");
    }
    
    public static AdapterPagamento getInstancia() {
        if (instancia == null) {
            instancia = new AdapterPagamento();
        }
        return instancia;
    }
    
    @Override
    public boolean processarPagamento(double valor, String descricao) {
        try {
            // Adapta nossa interface para a interface do sistema externo
            String customerId = "CLIENTE_" + System.currentTimeMillis();
            String transactionId = sistemaExterno.realizarCobranca(valor, descricao, customerId);
            
            // Verifica se o pagamento foi bem-sucedido
            return sistemaExterno.verificarStatus(transactionId);
        } catch (Exception e) {
            System.out.println("ERRO: Erro no processamento do pagamento: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String gerarRecibo(String transacaoId) {
        try {
            return sistemaExterno.obterComprovante(transacaoId);
        } catch (Exception e) {
            System.out.println("ERRO: Erro ao gerar recibo: " + e.getMessage());
            return "Erro ao gerar recibo";
        }
    }
}


