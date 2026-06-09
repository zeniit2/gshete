package com.fiap.mecatronica.baselunar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.Reservatorio;
import com.fiap.mecatronica.baselunar.repository.ReservatorioRepository;

@Service
public class ReservatorioService {

    @Autowired
    private ReservatorioRepository reservatorioRepository;

    public List<Reservatorio> listarTodos() {
        return reservatorioRepository.findAll();
    }

    public Reservatorio buscarPorId(Long id) {
        return reservatorioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reservatorio nao encontrado com id: " + id));
    }

    public List<Reservatorio> listarPorTipoRecurso(String tipoRecurso) {
        return reservatorioRepository.findByTipoRecursoIgnoreCase(tipoRecurso);
    }

    public List<Reservatorio> listarPorModulo(String modulo) {
        return reservatorioRepository.findByModuloContainingIgnoreCase(modulo);
    }

    public Reservatorio criar(Reservatorio reservatorio) {
        return reservatorioRepository.save(reservatorio);
    }

    public Reservatorio atualizar(Long id, Reservatorio dados) {
        Reservatorio reservatorio = buscarPorId(id);
        reservatorio.setNome(dados.getNome());
        reservatorio.setTipoRecurso(dados.getTipoRecurso());
        reservatorio.setCapacidadeMaxima(dados.getCapacidadeMaxima());
        reservatorio.setNivelAtual(dados.getNivelAtual());
        reservatorio.setUnidade(dados.getUnidade());
        reservatorio.setModulo(dados.getModulo());
        return reservatorioRepository.save(reservatorio);
    }

    public void deletar(Long id) {
        Reservatorio reservatorio = buscarPorId(id);
        reservatorioRepository.delete(reservatorio);
    }
}
