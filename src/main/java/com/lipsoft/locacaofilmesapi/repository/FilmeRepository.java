package com.lipsoft.locacaofilmesapi.repository;

import com.lipsoft.locacaofilmesapi.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
