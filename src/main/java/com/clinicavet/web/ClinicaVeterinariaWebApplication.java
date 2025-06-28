package com.clinicavet.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Classe principal da aplicação web da Clínica Veterinária
 * Utiliza Spring Boot para criar uma aplicação web moderna
 */
@SpringBootApplication
public class ClinicaVeterinariaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicaVeterinariaWebApplication.class, args);
        System.out.println("=========================================");
        System.out.println("CLINICA VETERINARIA WEB INICIADA!");
        System.out.println("Acesse: http://localhost:8080");
    }
} 


