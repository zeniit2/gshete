package com.fiap.mecatronica.baselunar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.Sensor;
import com.fiap.mecatronica.baselunar.repository.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> listarTodos() {
        return sensorRepository.findAll();
    }

    public Sensor buscarPorId(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sensor nao encontrado com id: " + id));
    }

    public List<Sensor> listarPorTipo(String tipo) {
        return sensorRepository.findByTipoIgnoreCase(tipo);
    }

    public List<Sensor> listarPorModulo(String modulo) {
        return sensorRepository.findByModuloContainingIgnoreCase(modulo);
    }

    public List<Sensor> listarAtivos() {
        return sensorRepository.findByAtivoTrue();
    }

    public Sensor criar(Sensor sensor) {
        if (sensor.getAtivo() == null) {
            sensor.setAtivo(true);
        }
        return sensorRepository.save(sensor);
    }

    public Sensor atualizar(Long id, Sensor dados) {
        Sensor sensor = buscarPorId(id);
        sensor.setNome(dados.getNome());
        sensor.setTipo(dados.getTipo());
        sensor.setModulo(dados.getModulo());
        sensor.setUnidade(dados.getUnidade());
        sensor.setValorAtual(dados.getValorAtual());
        sensor.setLimiteMinimo(dados.getLimiteMinimo());
        sensor.setLimiteMaximo(dados.getLimiteMaximo());
        if (dados.getAtivo() != null) {
            sensor.setAtivo(dados.getAtivo());
        }
        return sensorRepository.save(sensor);
    }

    public void deletar(Long id) {
        Sensor sensor = buscarPorId(id);
        sensorRepository.delete(sensor);
    }
}
