package com.clinicavet.core.adapter;

// Interface para sistema de pagamento externo
public interface SistemaPagamento {
    boolean processarPagamento(double valor, String descricao);
    String gerarRecibo(String transacaoId);
} 


