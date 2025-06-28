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
 * Controller para gerenciamento de Pagamentos
 * Integra com o sistema existente via Facade
 */
@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final ClinicaFacade clinicaFacade = ClinicaFacade.getInstancia();
    @GetMapping
    public String listarPagamentos(Model model) {
        try {
            List<Pet> pets = clinicaFacade.getPetDAO().buscarTodos();
            List<PagamentoDTO> pagamentos = new ArrayList<>();
            
            // Simula pagamentos baseados nos pets existentes
            for (Pet pet : pets) {
                pagamentos.add(new PagamentoDTO(pet.getNome(), "Consulta de Rotina", 120.0, "Aprovado"));
            }
            model.addAttribute("pagamentos", pagamentos);
            model.addAttribute("titulo", "Gerenciamento de Pagamentos");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pagamentos: " + e.getMessage());
        }
        return "pagamentos/lista";
    }
    @GetMapping("/novo")
    public String novoPagamento(Model model) {
        try {
            // Buscar pets disponíveis para pagamento
            List<Pet> pets = clinicaFacade.getPetDAO().buscarTodos();
            model.addAttribute("pets", pets);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pets: " + e.getMessage());
        }
        
        model.addAttribute("pagamento", new PagamentoDTO());
        model.addAttribute("servicos", new String[]{
            "Consulta de Rotina", "Vacina V4", "Castração", "Exame de Sangue", 
            "Atendimento de Emergência", "Banho e Tosa"
        });
        model.addAttribute("titulo", "Processar Novo Pagamento");
        return "pagamentos/form";
    }
    @PostMapping
    public String processarPagamento(@ModelAttribute PagamentoDTO pagamento, RedirectAttributes redirectAttributes) {
        try {
            // Integrar com ClinicaFacade.processarPagamentoConsulta()
            boolean sucesso = clinicaFacade.processarPagamentoConsulta(
                pagamento.getValor(), pagamento.getNomePet(), pagamento.getServico());
            if (sucesso) {
                redirectAttributes.addFlashAttribute("mensagem", "Pagamento processado com sucesso!");
                redirectAttributes.addFlashAttribute("tipo", "success");
            } else {
                redirectAttributes.addFlashAttribute("mensagem", "Falha no processamento do pagamento");
                redirectAttributes.addFlashAttribute("tipo", "warning");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao processar pagamento: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/pagamentos";
    }
    @GetMapping("/{id}")
    public String visualizarPagamento(@PathVariable String id, Model model) {
        try {
            PagamentoDTO pagamento = new PagamentoDTO("Rex", "Consulta de Rotina", 120.0, "Aprovado");
            model.addAttribute("pagamento", pagamento);
            model.addAttribute("titulo", "Detalhes do Pagamento");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar pagamento: " + e.getMessage());
        }
        return "pagamentos/detalhes";
    }
    @GetMapping("/calcular-preco")
    @ResponseBody
    public String calcularPreco(@RequestParam String nomePet, @RequestParam String servico, 
                               @RequestParam String estrategia) {
        try {
            // Integrar com ClinicaFacade.calcularPreco()
            double preco = clinicaFacade.calcularPreco(nomePet, servico, estrategia);
            return String.format("R$ %.2f", preco);
        } catch (Exception e) {
            return "Erro ao calcular preço: " + e.getMessage();
        }
    }
    // DTO interno para transferência de dados
    public static class PagamentoDTO {
        private String nomePet;
        private String servico;
        private double valor;
        private String status;
        public PagamentoDTO() {
            super(); // Chamada explícita ao construtor da superclasse
        }
        public PagamentoDTO(String nomePet, String servico, double valor, String status) {
            super(); // Chamada explícita ao construtor da superclasse
            this.nomePet = nomePet;
            this.servico = servico;
            this.valor = valor;
            this.status = status;
        }
        // Getters e Setters
        public String getNomePet() { return nomePet; }
        public void setNomePet(String nomePet) { this.nomePet = nomePet; }
        public String getServico() { return servico; }
        public void setServico(String servico) { this.servico = servico; }
        public double getValor() { return valor; }
        public void setValor(double valor) { this.valor = valor; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}


