package com.lipsoft.locacaofilmesapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_ATORES")
public class Ator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private int idade;
    @Column
    private String nomeDoPersonagem;

    public Ator() {}

    public Ator(String nome, int idade, String nomeDoPersonagem) {
        this.nome = nome;
        this.idade = idade;
        this.nomeDoPersonagem = nomeDoPersonagem;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNomeDoPersonagem() {
        return nomeDoPersonagem;
    }

    public void setNomeDoPersonagem(String nomeDoPersonagem) {
        this.nomeDoPersonagem = nomeDoPersonagem;
    }

}
