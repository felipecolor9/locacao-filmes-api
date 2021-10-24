package com.lipsoft.locacaofilmesapi.repository;

import com.lipsoft.locacaofilmesapi.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
