package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.Reservatorio;

@Repository
public interface ReservatorioRepository extends JpaRepository<Reservatorio, Long> {

    List<Reservatorio> findByTipoRecursoIgnoreCase(String tipoRecurso);

    List<Reservatorio> findByModuloContainingIgnoreCase(String modulo);
}
