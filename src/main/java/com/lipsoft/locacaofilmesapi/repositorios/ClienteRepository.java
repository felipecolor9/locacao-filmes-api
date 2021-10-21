package com.lipsoft.locacaofilmesapi.repositorios;

import com.lipsoft.locacaofilmesapi.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Long, Cliente> {
}
