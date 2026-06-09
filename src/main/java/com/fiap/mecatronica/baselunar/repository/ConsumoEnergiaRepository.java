package com.fiap.mecatronica.baselunar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.baselunar.model.ConsumoEnergia;

@Repository
public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {

    List<ConsumoEnergia> findBySetorContainingIgnoreCase(String setor);

    List<ConsumoEnergia> findByFonteIgnoreCase(String fonte);
}
