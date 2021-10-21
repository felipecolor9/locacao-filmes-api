package com.lipsoft.locacaofilmesapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_CLIENTES")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column @NotNull
    private String nomeCompleto;
    @Column @NotNull
    private int idade;
    @Column @NotNull
    private String estadoSigla;
    @Column @NotNull
    private String cidade;
    @Column @NotNull
    private String complemento;
    @Column @NotNull
    private String cep;

    public Cliente() {
    }

    public Cliente(String nomeCompleto, int idade, String estadoSigla, String cidade, String complemento, String cep) {
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.estadoSigla = estadoSigla;
        this.cidade = cidade;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
