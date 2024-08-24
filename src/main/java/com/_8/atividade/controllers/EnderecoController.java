package com._8.atividade.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com._8.atividade.model.Endereco;
import com._8.atividade.repository.EnderecoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoRepository repositoryEndereco;

    @GetMapping("/cadastro")
    public ModelAndView endereco() {

        ModelAndView mv = new ModelAndView("endereco");
        List<Endereco> enderecos = repositoryEndereco.findAll();
        mv.addObject("enderecos", enderecos);

        return mv;
    }

    @PostMapping("/cadastro")
    public String cadastroEndereco(Endereco endereco) {

        repositoryEndereco.save(endereco);

        return "redirect:/inicio/opcoes";
    }

}
