package com.fiap.mecatronica.baselunar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.AlertaOperacional;
import com.fiap.mecatronica.baselunar.repository.AlertaOperacionalRepository;

@Service
public class AlertaOperacionalService {

    @Autowired
    private AlertaOperacionalRepository alertaRepository;

    public List<AlertaOperacional> listarTodos() {
        return alertaRepository.findAll();
    }

    public AlertaOperacional buscarPorId(Long id) {
        return alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta nao encontrado com id: " + id));
    }

    public List<AlertaOperacional> listarPorNivel(String nivel) {
        return alertaRepository.findByNivelIgnoreCase(nivel);
    }

    public List<AlertaOperacional> listarPendentes() {
        return alertaRepository.findByResolvidoFalse();
    }

    public AlertaOperacional criar(AlertaOperacional alerta) {
        if (alerta.getResolvido() == null) {
            alerta.setResolvido(false);
        }
        if (alerta.getDataHora() == null) {
            alerta.setDataHora(LocalDateTime.now());
        }
        return alertaRepository.save(alerta);
    }

    public AlertaOperacional atualizar(Long id, AlertaOperacional dados) {
        AlertaOperacional alerta = buscarPorId(id);
        alerta.setTitulo(dados.getTitulo());
        alerta.setDescricao(dados.getDescricao());
        alerta.setNivel(dados.getNivel());
        alerta.setOrigem(dados.getOrigem());
        if (dados.getResolvido() != null) {
            alerta.setResolvido(dados.getResolvido());
        }
        return alertaRepository.save(alerta);
    }

    /**
     * Marca um alerta como resolvido sem precisar reenviar todos os dados.
     */
    public AlertaOperacional resolver(Long id) {
        AlertaOperacional alerta = buscarPorId(id);
        alerta.setResolvido(true);
        return alertaRepository.save(alerta);
    }

    public void deletar(Long id) {
        AlertaOperacional alerta = buscarPorId(id);
        alertaRepository.delete(alerta);
    }
}
