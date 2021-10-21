package com.lipsoft.locacaofilmesapi.entities;

public class Ator {

    private Long id;
    private String nome;
    private int idade;
    private String nomeDoPersonagem;

    public Ator() {}

    public Ator(String nome, int idade, String nomeDoPersonagem) {
        this.nome = nome;
        this.idade = idade;
        this.nomeDoPersonagem = nomeDoPersonagem;
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
