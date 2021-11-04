package com.lipsoft.locacaofilmesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CLIENTES")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomeCompleto;
    @Column
    private int idade;
    @Column
    private String estadoSigla;
    @Column
    private String cidade;
    @Column
    private String complemento;
    @Column
    private String cep;
}
