package com.lipsoft.locacaofilmesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TBL_FILMES")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomeDoFilme;
    @Column
    private int anoDeLancamento;
    @Column
    private double notaDosUsuarios;
    @Column
    private double notaDaCritica;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Ator> atores;

    public Filme(Long id, String nomeDoFilme, int anoDeLancamento, double notaDosUsuarios, double notaDaCritica) {
        this.id = id;
        this.nomeDoFilme = nomeDoFilme;
        this.anoDeLancamento = anoDeLancamento;
        this.notaDosUsuarios = notaDosUsuarios;
        this.notaDaCritica = notaDaCritica;
    }
}
