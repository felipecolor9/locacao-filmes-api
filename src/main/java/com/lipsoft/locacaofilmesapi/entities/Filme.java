package com.lipsoft.locacaofilmesapi.entities;

public class Filme {

    private Long id;
    private String nomeDoFilme;
    private int anoDeLancamento;
    private double notaDosUsuarios;
    private double notaDaCritica;

    public Filme() {
    }

    public Filme(String nomeDoFilme, int anoDeLancamento, double notaDosUsuarios, double notaDaCritica) {
        this.nomeDoFilme = nomeDoFilme;
        this.anoDeLancamento = anoDeLancamento;
        this.notaDosUsuarios = notaDosUsuarios;
        this.notaDaCritica = notaDaCritica;
    }

    public String getNomeDoFilme() {
        return nomeDoFilme;
    }

    public void setNomeDoFilme(String nomeDoFilme) {
        this.nomeDoFilme = nomeDoFilme;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public double getNotaDosUsuarios() {
        return notaDosUsuarios;
    }

    public void setNotaDosUsuarios(double notaDosUsuarios) {
        this.notaDosUsuarios = notaDosUsuarios;
    }

    public double getNotaDaCritica() {
        return notaDaCritica;
    }

    public void setNotaDaCritica(double notaDaCritica) {
        this.notaDaCritica = notaDaCritica;
    }
}
