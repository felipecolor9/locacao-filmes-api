package com.lipsoft.locacaofilmesapi.entidades;

public class Cliente {

    private Long id;
    private String nomeCompleto;
    private int idade;
    private String estadoSigla;
    private String cidade;
    private String complemento;
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
}
