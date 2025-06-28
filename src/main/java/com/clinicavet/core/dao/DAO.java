package com.clinicavet.core.dao;

import java.util.List;
// Interface genérica DAO para operações básicas
public interface DAO<T> {
    void salvar(T objeto);
    T buscarPorId(String id);
    List<T> buscarTodos();
    void atualizar(T objeto);
    void deletar(String id);
} 


