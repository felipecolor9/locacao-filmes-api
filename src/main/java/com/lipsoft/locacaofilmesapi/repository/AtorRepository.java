package com.lipsoft.locacaofilmesapi.repository;

import com.lipsoft.locacaofilmesapi.entities.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
}
