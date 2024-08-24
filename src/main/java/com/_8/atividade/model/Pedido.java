package com._8.atividade.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    private LocalDate dataPedido = LocalDate.now();

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Produto> produto = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private FormaPagamento formaPagamento;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Endereco endereco;

    public Pedido() {
    }

    public Pedido(int idPedido, LocalDate dataPedido, List<Produto> produto, FormaPagamento formaPagamento,
            Endereco endereco) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.produto = produto;
        this.formaPagamento = formaPagamento;
        this.endereco = endereco;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
