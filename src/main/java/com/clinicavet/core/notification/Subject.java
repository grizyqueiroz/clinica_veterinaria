package com.clinicavet.core.notification;

// Interface Subject para o padrao Observer
public interface Subject {
    void adicionarObserver(Observer observer);
    void removerObserver(Observer observer);
    void notificarObservers(String mensagem, Object dados);
} 


