package com.lipsoft.locacaofilmesapi.entities;

import lombok.*;

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
    private String sinopse;
    @Column
    private int anoDeLancamento;
    @Column
    private double notaDosUsuarios;
    @Column
    private double notaDaCritica;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Ator> elenco;

}
