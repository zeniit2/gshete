package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    List<Recurso> findByStatusIgnoreCase(String status);

    List<Recurso> findByTipoIgnoreCase(String tipo);
}
