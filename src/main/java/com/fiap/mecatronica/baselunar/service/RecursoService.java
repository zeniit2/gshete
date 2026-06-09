package com.fiap.mecatronica.baselunar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.Recurso;
import com.fiap.mecatronica.baselunar.repository.RecursoRepository;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> listarTodos() {
        return recursoRepository.findAll();
    }

    public Recurso buscarPorId(Long id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Recurso nao encontrado com id: " + id));
    }

    public List<Recurso> listarPorStatus(String status) {
        return recursoRepository.findByStatusIgnoreCase(status);
    }

    public List<Recurso> listarPorTipo(String tipo) {
        return recursoRepository.findByTipoIgnoreCase(tipo);
    }

    public Recurso criar(Recurso recurso) {
        definirStatusSeAusente(recurso);
        return recursoRepository.save(recurso);
    }

    public Recurso atualizar(Long id, Recurso dados) {
        Recurso recurso = buscarPorId(id);
        recurso.setNome(dados.getNome());
        recurso.setTipo(dados.getTipo());
        recurso.setNivelAtual(dados.getNivelAtual());
        recurso.setStatus(dados.getStatus());
        definirStatusSeAusente(recurso);
        return recursoRepository.save(recurso);
    }

    public void deletar(Long id) {
        Recurso recurso = buscarPorId(id);
        recursoRepository.delete(recurso);
    }

    /**
     * Se o status nao vier preenchido, calcula a partir do nivel atual,
     * seguindo a mesma regra usada pelo aplicativo mobile (abaixo de 20% = CRITICO).
     */
    private void definirStatusSeAusente(Recurso recurso) {
        if (recurso.getStatus() == null || recurso.getStatus().isBlank()) {
            if (recurso.getNivelAtual() != null && recurso.getNivelAtual() < 20) {
                recurso.setStatus("CRITICO");
            } else {
                recurso.setStatus("NORMAL");
            }
        }
    }
}
