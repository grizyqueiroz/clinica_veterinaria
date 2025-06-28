package com.clinicavet.core.notification;

import java.util.ArrayList;
import java.util.List;
import com.clinicavet.core.model.Pet;

// Sistema de notificações que implementa Subject
public class SistemaNotificacoes implements Subject {
    private static SistemaNotificacoes instancia;
    private List<Observer> observers;
    
    private SistemaNotificacoes() {
        super(); // Chamada explícita ao construtor da superclasse
        this.observers = new ArrayList<>();
    }
    
    public static SistemaNotificacoes getInstancia() {
        if (instancia == null) {
            instancia = new SistemaNotificacoes();
        }
        return instancia;
    }
    
    @Override
    public void adicionarObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notificarObservers(String mensagem, Object dados) {
        for (Observer observer : observers) {
            observer.atualizar(mensagem, dados);
        }
    }
    
    // Métodos específicos para diferentes tipos de notificação
    public void notificarPetChegou(Pet pet) {
        notificarObservers("PET_CHEGOU", pet);
    }
    
    public void notificarVacinaVencendo(Pet pet, String vacina) {
        notificarObservers("VACINA_VENCENDO", new Object[]{pet, vacina});
    }
    
    public void notificarResultadoExame(Pet pet, String resultado) {
        notificarObservers("RESULTADO_EXAME", new Object[]{pet, resultado});
    }
}


