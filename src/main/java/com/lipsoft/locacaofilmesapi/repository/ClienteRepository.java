package com.lipsoft.locacaofilmesapi.repository;

import com.lipsoft.locacaofilmesapi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
