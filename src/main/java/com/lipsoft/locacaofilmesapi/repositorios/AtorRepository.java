package com.lipsoft.locacaofilmesapi.repositorios;

import com.lipsoft.locacaofilmesapi.entidades.Ator;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AtorRepository extends JpaRepository<Long, Ator> {
}
