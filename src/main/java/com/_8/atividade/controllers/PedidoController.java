package com._8.atividade.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com._8.atividade.model.Endereco;
import com._8.atividade.model.FormaPagamento;
import com._8.atividade.model.Pedido;
import com._8.atividade.model.Produto;
import com._8.atividade.repository.EnderecoRepository;
import com._8.atividade.repository.FormaPagamentoRepository;
import com._8.atividade.repository.PedidoRepository;
import com._8.atividade.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    FormaPagamentoRepository pagamentoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/novo")
    public String fazerPedido(Model model) {

        List<Endereco> enderecos = enderecoRepository.findAll();
        List<Produto> produtos = produtoRepository.findAll();
        List<FormaPagamento> pagamentos = pagamentoRepository.findAll();

        model.addAttribute("enderecos", enderecos);
        model.addAttribute("produtos", produtos);
        model.addAttribute("pagamentos", pagamentos);

        return "pedido";
    }

    @PostMapping("/novo")
    public String enviarPedido(@RequestParam Integer idEndereco,
            @RequestParam List<Integer> idProduto,
            @RequestParam Integer idPagamento) {

        Endereco endereco = enderecoRepository.findById(idEndereco).get();
        FormaPagamento formaPagamento = pagamentoRepository.findById(idPagamento).get();
        List<Produto> produtos = produtoRepository.findAllById(idProduto);

        Pedido pedido = new Pedido();
        pedido.setEndereco(endereco);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setProduto(produtos);
        pedido.setDataPedido(LocalDate.now());

        pedidoRepository.save(pedido);

        return "redirect:/pedido/lista";
    }

    @GetMapping("/lista")
    public String listarPedido(Model model) {

        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);

        return "lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPedido(@PathVariable("id") int id) {

        pedidoRepository.deleteById(id);

        return "redirect:/pedido/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable("id") int id, Model model) {

        Pedido pedido = pedidoRepository.findById(id).get();
        model.addAttribute("pedido", pedido);
        model.addAttribute("produto", produtoRepository.findAll());
        model.addAttribute("endereco", enderecoRepository.findAll());
        model.addAttribute("pagamento", pagamentoRepository.findAll());
        return "pedido";
    }

}
