package com.fiap.mecatronica.baselunar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.baselunar.exception.RecursoNaoEncontradoException;
import com.fiap.mecatronica.baselunar.model.Climatizacao;
import com.fiap.mecatronica.baselunar.repository.ClimatizacaoRepository;

@Service
public class ClimatizacaoService {

    @Autowired
    private ClimatizacaoRepository climatizacaoRepository;

    public List<Climatizacao> listarTodos() {
        return climatizacaoRepository.findAll();
    }

    public Climatizacao buscarPorId(Long id) {
        return climatizacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Climatizacao nao encontrada com id: " + id));
    }

    public List<Climatizacao> listarPorModulo(String modulo) {
        return climatizacaoRepository.findByModuloContainingIgnoreCase(modulo);
    }

    public List<Climatizacao> listarAtivas() {
        return climatizacaoRepository.findByAtivoTrue();
    }

    public Climatizacao criar(Climatizacao climatizacao) {
        if (climatizacao.getAtivo() == null) {
            climatizacao.setAtivo(true);
        }
        if (climatizacao.getModo() == null) {
            climatizacao.setModo("automatico");
        }
        return climatizacaoRepository.save(climatizacao);
    }

    public Climatizacao atualizar(Long id, Climatizacao dados) {
        Climatizacao climatizacao = buscarPorId(id);
        climatizacao.setModulo(dados.getModulo());
        climatizacao.setTemperaturaAlvo(dados.getTemperaturaAlvo());
        climatizacao.setTemperaturaAtual(dados.getTemperaturaAtual());
        climatizacao.setUmidadeRelativa(dados.getUmidadeRelativa());
        climatizacao.setPressaoKpa(dados.getPressaoKpa());
        if (dados.getModo() != null) {
            climatizacao.setModo(dados.getModo());
        }
        if (dados.getAtivo() != null) {
            climatizacao.setAtivo(dados.getAtivo());
        }
        return climatizacaoRepository.save(climatizacao);
    }

    public void deletar(Long id) {
        Climatizacao climatizacao = buscarPorId(id);
        climatizacaoRepository.delete(climatizacao);
    }
}
