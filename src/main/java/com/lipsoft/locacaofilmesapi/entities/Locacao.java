package com.lipsoft.locacaofilmesapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_LOCACOES")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
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
    @Column
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataFimLocacao;
    @Column
    @NotNull
    private double valorTotal;

    public Locacao(Long id, LocalDateTime dataInicioLocacao, LocalDateTime dataFimLocacao, double valorTotal) {
        this.id = id;
        this.dataInicioLocacao = dataInicioLocacao;
        this.dataFimLocacao = dataFimLocacao;
        this.valorTotal = valorTotal;
    }
}