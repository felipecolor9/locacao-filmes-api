package com.lipsoft.locacaofilmesapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_LOCACOES")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_filme")
    private Filme filme;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataInicioLocacao;
    @Column @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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

    public Long getId() {
        return id;
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
