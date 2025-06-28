package com.clinicavet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller principal da aplicação web
 * Gerencia as páginas principais do sistema
 */
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Clínica Veterinária - Sistema de Gerenciamento");
        model.addAttribute("descricao", "Sistema completo para gerenciamento de pets, consultas e pagamentos");
        return "home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("titulo", "Dashboard - Clínica Veterinária");
        return "dashboard";
    }
    
    @GetMapping("/sobre")
    public String sobre(Model model) {
        model.addAttribute("titulo", "Sobre o Sistema");
        model.addAttribute("padroes", new String[]{
            "Singleton", "Factory", "Builder", "DAO", 
            "Observer", "Strategy", "Command", "Adapter", "Facade"
        });
        return "sobre";
    }
}


 