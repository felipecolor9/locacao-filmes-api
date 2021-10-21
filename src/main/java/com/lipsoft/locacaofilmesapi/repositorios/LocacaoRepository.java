package com.lipsoft.locacaofilmesapi.repositorios;

import com.lipsoft.locacaofilmesapi.entidades.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
