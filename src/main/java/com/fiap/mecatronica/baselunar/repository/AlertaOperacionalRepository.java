package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.AlertaOperacional;

@Repository
public interface AlertaOperacionalRepository extends JpaRepository<AlertaOperacional, Long> {

    List<AlertaOperacional> findByNivelIgnoreCase(String nivel);

    List<AlertaOperacional> findByResolvidoFalse();

    List<AlertaOperacional> findByResolvido(Boolean resolvido);
}
