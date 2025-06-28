package com.clinicavet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.clinicavet.core.facade.ClinicaFacade;
import com.clinicavet.core.model.Pet;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller para gerenciamento de Consultas
 * Integra com o sistema existente via Facade
 */
@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    private final ClinicaFacade clinicaFacade = ClinicaFacade.getInstancia();
    @GetMapping
    public String listarConsultas(Model model) {
        try {
            List<Pet> pets = clinicaFacade.getPetDAO().buscarTodos();
            List<ConsultaDTO> consultas = new ArrayList<>();
            
            // Simula consultas baseadas nos pets existentes
            for (Pet pet : pets) {
                consultas.add(new ConsultaDTO(pet.getNome(), "15/04/2025", "Dr. Silva", "Consulta de rotina"));
            }
            model.addAttribute("consultas", consultas);
            model.addAttribute("titulo", "Gerenciamento de Consultas");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar consultas: " + e.getMessage());
        }
        return "consultas/lista";
    }
    @GetMapping("/nova")
    public String novaConsulta(Model model) {
        try {
            // Buscar pets disponíveis para consulta
            List<Pet> pets = clinicaFacade.getPetDAO().buscarTodos();
            model.addAttribute("pets", pets);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pets: " + e.getMessage());
        }
        
        model.addAttribute("consulta", new ConsultaDTO());
        model.addAttribute("veterinarios", new String[]{"Dr. Silva", "Dr. Santos", "Dr. Oliveira", "Dra. Costa"});
        model.addAttribute("titulo", "Agendar Nova Consulta");
        return "consultas/form";
    }
    @PostMapping
    public String agendarConsulta(@ModelAttribute ConsultaDTO consulta, RedirectAttributes redirectAttributes) {
        try {
            // Integrar com ClinicaFacade.agendarConsulta()
            clinicaFacade.agendarConsulta(consulta.getNomePet(), consulta.getData(), 
                                        consulta.getVeterinario(), consulta.getMotivo());
            redirectAttributes.addFlashAttribute("mensagem", "Consulta agendada com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao agendar consulta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/consultas";
    }
    @GetMapping("/{id}")
    public String visualizarConsulta(@PathVariable String id, Model model) {
        try {
            ConsultaDTO consulta = new ConsultaDTO("Rex", "15/04/2025", "Dr. Silva", "Consulta de rotina");
            model.addAttribute("consulta", consulta);
            model.addAttribute("titulo", "Detalhes da Consulta");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar consulta: " + e.getMessage());
        }
        return "consultas/detalhes";
    }
    @GetMapping("/{id}/reagendar")
    public String reagendarConsulta(@PathVariable String id, Model model) {
        model.addAttribute("titulo", "Reagendar Consulta");
        return "consultas/reagendar";
    }
    @PostMapping("/{id}/reagendar")
    public String salvarReagendamento(@PathVariable String id, @ModelAttribute ConsultaDTO consulta, 
                                     RedirectAttributes redirectAttributes) {
        try {
            // Integrar com ClinicaFacade.reagendarConsulta()
            clinicaFacade.reagendarConsulta(consulta.getNomePet(), consulta.getData(), consulta.getVeterinario());
            redirectAttributes.addFlashAttribute("mensagem", "Consulta reagendada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao reagendar consulta: " + e.getMessage());
        }
        return "redirect:/consultas";
    }
    // DTO interno para transferência de dados
    public static class ConsultaDTO {
        private String nomePet;
        private String data;
        private String veterinario;
        private String motivo;
        
        public ConsultaDTO() {
            super(); // Chamada explícita ao construtor da superclasse
        }
        
        public ConsultaDTO(String nomePet, String data, String veterinario, String motivo) {
            super(); // Chamada explícita ao construtor da superclasse
            this.nomePet = nomePet;
            this.data = data;
            this.veterinario = veterinario;
            this.motivo = motivo;
        }
        
        // Getters e Setters
        public String getNomePet() { return nomePet; }
        public void setNomePet(String nomePet) { this.nomePet = nomePet; }
        public String getData() { return data; }
        public void setData(String data) { this.data = data; }
        public String getVeterinario() { return veterinario; }
        public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }
    }
}


