package com.lipsoft.locacaofilmesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TBL_ATORES")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Ator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private int idade;
    @Column
    private String nomeDoPersonagem;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Filme> filmografia;

}
