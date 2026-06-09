package com.fiap.mecatronica.baselunar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mecatronica.baselunar.model.AlertaOperacional;
import com.fiap.mecatronica.baselunar.service.AlertaOperacionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaOperacionalController {

    @Autowired
    private AlertaOperacionalService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaOperacional>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaOperacional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<AlertaOperacional>> listarPorNivel(@PathVariable String nivel) {
        return ResponseEntity.ok(alertaService.listarPorNivel(nivel));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<AlertaOperacional>> listarPendentes() {
        return ResponseEntity.ok(alertaService.listarPendentes());
    }

    @PostMapping
    public ResponseEntity<AlertaOperacional> criar(@Valid @RequestBody AlertaOperacional alerta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.criar(alerta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaOperacional> atualizar(@PathVariable Long id, @Valid @RequestBody AlertaOperacional alerta) {
        return ResponseEntity.ok(alertaService.atualizar(id, alerta));
    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<AlertaOperacional> resolver(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.resolver(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
