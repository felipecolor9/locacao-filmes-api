package com.lipsoft.locacaofilmesapi.repositorios;

import com.lipsoft.locacaofilmesapi.entidades.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Long, Filme> {
}
