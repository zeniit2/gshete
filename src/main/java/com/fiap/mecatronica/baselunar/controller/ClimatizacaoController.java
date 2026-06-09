package com.fiap.mecatronica.baselunar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mecatronica.baselunar.model.Climatizacao;
import com.fiap.mecatronica.baselunar.service.ClimatizacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/climatizacoes")
@CrossOrigin(origins = "*")
public class ClimatizacaoController {

    @Autowired
    private ClimatizacaoService climatizacaoService;

    @GetMapping
    public ResponseEntity<List<Climatizacao>> listarTodos() {
        return ResponseEntity.ok(climatizacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Climatizacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(climatizacaoService.buscarPorId(id));
    }

    @GetMapping("/modulo/{modulo}")
    public ResponseEntity<List<Climatizacao>> listarPorModulo(@PathVariable String modulo) {
        return ResponseEntity.ok(climatizacaoService.listarPorModulo(modulo));
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Climatizacao>> listarAtivas() {
        return ResponseEntity.ok(climatizacaoService.listarAtivas());
    }

    @PostMapping
    public ResponseEntity<Climatizacao> criar(@Valid @RequestBody Climatizacao climatizacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(climatizacaoService.criar(climatizacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Climatizacao> atualizar(@PathVariable Long id, @Valid @RequestBody Climatizacao climatizacao) {
        return ResponseEntity.ok(climatizacaoService.atualizar(id, climatizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        climatizacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
