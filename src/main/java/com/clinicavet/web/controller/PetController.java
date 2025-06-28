package com.clinicavet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.clinicavet.core.facade.ClinicaFacade;
import com.clinicavet.core.model.Pet;
import java.util.List;
import java.util.ArrayList;

/**
 * Controller para gerenciamento de Pets
 * Integra com o sistema existente via Facade
 */
@Controller
@RequestMapping("/pets")
public class PetController {
    private final ClinicaFacade clinicaFacade = ClinicaFacade.getInstancia();
    
    @GetMapping
    public String listarPets(Model model) {
        try {
            // Integrar com ClinicaFacade para buscar pets
            List<Pet> petsReais = clinicaFacade.getPetDAO().buscarTodos();
            List<PetDTO> pets = new ArrayList<>();
            
            for (Pet pet : petsReais) {
                pets.add(new PetDTO(pet.getNome(), pet.getEspecie(), pet.getIdade()));
            }
            model.addAttribute("pets", pets);
            model.addAttribute("titulo", "Gerenciamento de Pets");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pets: " + e.getMessage());
        }
        return "pets/lista";
    }
    
    @GetMapping("/novo")
    public String novoPet(Model model) {
        model.addAttribute("pet", new PetDTO());
        model.addAttribute("especies", new String[]{"Cachorro", "Gato", "Ave", "Réptil", "Peixe"});
        model.addAttribute("titulo", "Cadastrar Novo Pet");
        return "pets/form";
    }
    
    @PostMapping
    public String salvarPet(@ModelAttribute PetDTO pet, RedirectAttributes redirectAttributes) {
        try {
            // Integrar com ClinicaFacade.cadastrarPet()
            clinicaFacade.cadastrarPet(pet.getNome(), pet.getEspecie(), pet.getIdade());
            redirectAttributes.addFlashAttribute("mensagem", "Pet cadastrado com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar pet: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/pets";
    }
    
    @GetMapping("/{id}")
    public String visualizarPet(@PathVariable String id, Model model) {
        try {
            // Integrar com ClinicaFacade para buscar pet por ID
            Pet petReal = clinicaFacade.getPetDAO().buscarPorId(id);
            if (petReal != null) {
                PetDTO pet = new PetDTO(petReal.getNome(), petReal.getEspecie(), petReal.getIdade());
                model.addAttribute("pet", pet);
            } else {
                model.addAttribute("erro", "Pet não encontrado");
            }
            model.addAttribute("titulo", "Detalhes do Pet");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pet: " + e.getMessage());
        }
        return "pets/detalhes";
    }
    
    // DTO interno para transferência de dados
    public static class PetDTO {
        private String nome;
        private String especie;
        private int idade;
        
        public PetDTO() {
            super(); // Chamada explícita ao construtor da superclasse
        }
        
        public PetDTO(String nome, String especie, int idade) {
            super(); // Chamada explícita ao construtor da superclasse
            this.nome = nome;
            this.especie = especie;
            this.idade = idade;
        }
        
        // Getters e Setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        
        public String getEspecie() { return especie; }
        public void setEspecie(String especie) { this.especie = especie; }
        
        public int getIdade() { return idade; }
        public void setIdade(int idade) { this.idade = idade; }
    }
}


