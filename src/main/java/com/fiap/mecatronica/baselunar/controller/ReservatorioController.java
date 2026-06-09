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

import com.fiap.mecatronica.baselunar.model.Reservatorio;
import com.fiap.mecatronica.baselunar.service.ReservatorioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservatorios")
@CrossOrigin(origins = "*")
public class ReservatorioController {

    @Autowired
    private ReservatorioService reservatorioService;

    @GetMapping
    public ResponseEntity<List<Reservatorio>> listarTodos() {
        return ResponseEntity.ok(reservatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservatorio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservatorioService.buscarPorId(id));
    }

    @GetMapping("/recurso/{tipoRecurso}")
    public ResponseEntity<List<Reservatorio>> listarPorTipoRecurso(@PathVariable String tipoRecurso) {
        return ResponseEntity.ok(reservatorioService.listarPorTipoRecurso(tipoRecurso));
    }

    @GetMapping("/modulo/{modulo}")
    public ResponseEntity<List<Reservatorio>> listarPorModulo(@PathVariable String modulo) {
        return ResponseEntity.ok(reservatorioService.listarPorModulo(modulo));
    }

    @PostMapping
    public ResponseEntity<Reservatorio> criar(@Valid @RequestBody Reservatorio reservatorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservatorioService.criar(reservatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservatorio> atualizar(@PathVariable Long id, @Valid @RequestBody Reservatorio reservatorio) {
        return ResponseEntity.ok(reservatorioService.atualizar(id, reservatorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        reservatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
