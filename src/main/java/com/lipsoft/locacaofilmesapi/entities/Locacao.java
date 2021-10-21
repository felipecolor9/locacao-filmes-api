package com.lipsoft.locacaofilmesapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_LOCACOES")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_filme")
    private Filme filme;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column @NotNull
    private LocalDateTime dataInicioLocacao;
    @Column @NotNull
    private LocalDateTime dataFimLocacao;
    @Column @NotNull
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