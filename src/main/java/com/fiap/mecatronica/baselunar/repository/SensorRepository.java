package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    List<Sensor> findByTipoIgnoreCase(String tipo);

    List<Sensor> findByModuloContainingIgnoreCase(String modulo);

    List<Sensor> findByAtivoTrue();
}
