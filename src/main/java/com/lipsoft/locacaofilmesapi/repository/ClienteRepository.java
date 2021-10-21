package com.lipsoft.locacaofilmesapi.repository;

import com.lipsoft.locacaofilmesapi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
