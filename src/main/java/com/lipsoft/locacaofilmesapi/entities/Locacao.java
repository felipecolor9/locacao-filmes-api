package com.lipsoft.locacaofilmesapi.entities;

import java.time.LocalDateTime;

public class Locacao {

    private Long id;
    private Filme filme;
    private Cliente cliente;
    private LocalDateTime dataInicioLocacao;
    private LocalDateTime dataFimLocacao;
    private double valorTotal;

    public Locacao() {
    }

    public Locacao(Filme filme, Cliente cliente, LocalDateTime dataInicioLocacao, LocalDateTime dataFimLocacao, double valorTotal) {
        this.filme = filme;
        this.cliente = cliente;
        this.dataInicioLocacao = dataInicioLocacao;
        this.dataFimLocacao = dataFimLocacao;
        this.valorTotal = valorTotal;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataInicioLocacao() {
        return dataInicioLocacao;
    }

    public void setDataInicioLocacao(LocalDateTime dataInicioLocacao) {
        this.dataInicioLocacao = dataInicioLocacao;
    }

    public LocalDateTime getDataFimLocacao() {
        return dataFimLocacao;
    }

    public void setDataFimLocacao(LocalDateTime dataFimLocacao) {
        this.dataFimLocacao = dataFimLocacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
