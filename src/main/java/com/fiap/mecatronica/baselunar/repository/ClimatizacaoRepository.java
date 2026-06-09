package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.Climatizacao;

@Repository
public interface ClimatizacaoRepository extends JpaRepository<Climatizacao, Long> {

    List<Climatizacao> findByModuloContainingIgnoreCase(String modulo);

    List<Climatizacao> findByAtivoTrue();
}
