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

import com.fiap.mecatronica.baselunar.model.Recurso;
import com.fiap.mecatronica.baselunar.service.RecursoService;

import jakarta.validation.Valid;

/**
 * Endpoint consumido diretamente pelo aplicativo mobile (Advanced Programming
 * And Mobile Dev). Expoe os recursos operacionais da base no formato esperado
 * pelo front-end: GET/POST /api/recursos.
 */
@RestController
@RequestMapping("/api/recursos")
@CrossOrigin(origins = "*")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public ResponseEntity<List<Recurso>> listarTodos() {
        return ResponseEntity.ok(recursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recursoService.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Recurso>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(recursoService.listarPorStatus(status));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Recurso>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(recursoService.listarPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<Recurso> criar(@Valid @RequestBody Recurso recurso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoService.criar(recurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> atualizar(@PathVariable Long id, @Valid @RequestBody Recurso recurso) {
        return ResponseEntity.ok(recursoService.atualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        recursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
