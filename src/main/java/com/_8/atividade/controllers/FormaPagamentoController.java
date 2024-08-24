package com._8.atividade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com._8.atividade.model.FormaPagamento;
import com._8.atividade.repository.FormaPagamentoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pagamento")
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoRepository pagamentoRepository;

    @GetMapping("/cadastro")
    public String pagamento() {
        return "pagamento";
    }

    @PostMapping("/cadastro")
    public String cadastroPagamento(FormaPagamento pagamento) {

        pagamentoRepository.save(pagamento);

        return "redirect:/inicio/opcoes";

    }

}
