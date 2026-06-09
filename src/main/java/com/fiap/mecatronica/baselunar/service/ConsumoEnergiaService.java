package com.fiap.mecatronica.baselunar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.ConsumoEnergia;
import com.fiap.mecatronica.baselunar.repository.ConsumoEnergiaRepository;

@Service
public class ConsumoEnergiaService {

    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    public List<ConsumoEnergia> listarTodos() {
        return consumoEnergiaRepository.findAll();
    }

    public ConsumoEnergia buscarPorId(Long id) {
        return consumoEnergiaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de consumo nao encontrado com id: " + id));
    }

    public List<ConsumoEnergia> listarPorSetor(String setor) {
        return consumoEnergiaRepository.findBySetorContainingIgnoreCase(setor);
    }

    public List<ConsumoEnergia> listarPorFonte(String fonte) {
        return consumoEnergiaRepository.findByFonteIgnoreCase(fonte);
    }

    public ConsumoEnergia criar(ConsumoEnergia consumo) {
        if (consumo.getDataRegistro() == null) {
            consumo.setDataRegistro(LocalDateTime.now());
        }
        return consumoEnergiaRepository.save(consumo);
    }

    public ConsumoEnergia atualizar(Long id, ConsumoEnergia dados) {
        ConsumoEnergia consumo = buscarPorId(id);
        consumo.setSetor(dados.getSetor());
        consumo.setFonte(dados.getFonte());
        consumo.setPotenciaWatts(dados.getPotenciaWatts());
        consumo.setConsumoKwh(dados.getConsumoKwh());
        if (dados.getDataRegistro() != null) {
            consumo.setDataRegistro(dados.getDataRegistro());
        }
        return consumoEnergiaRepository.save(consumo);
    }

    public void deletar(Long id) {
        ConsumoEnergia consumo = buscarPorId(id);
        consumoEnergiaRepository.delete(consumo);
    }
}
