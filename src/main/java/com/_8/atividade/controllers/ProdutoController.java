package com._8.atividade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com._8.atividade.model.Produto;
import com._8.atividade.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/cadastro")
    public String produto() {
        return "produto";
    }

    @PostMapping("/cadastro")
    public String cadastroProduto(Produto produto) {

        produtoRepository.save(produto);

        return "redirect:/inicio/opcoes";
    }

}
