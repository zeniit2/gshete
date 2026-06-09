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

import com.fiap.mecatronica.baselunar.model.ConsumoEnergia;
import com.fiap.mecatronica.baselunar.service.ConsumoEnergiaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consumos-energia")
@CrossOrigin(origins = "*")
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaService consumoEnergiaService;

    @GetMapping
    public ResponseEntity<List<ConsumoEnergia>> listarTodos() {
        return ResponseEntity.ok(consumoEnergiaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoEnergia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consumoEnergiaService.buscarPorId(id));
    }

    @GetMapping("/setor/{setor}")
    public ResponseEntity<List<ConsumoEnergia>> listarPorSetor(@PathVariable String setor) {
        return ResponseEntity.ok(consumoEnergiaService.listarPorSetor(setor));
    }

    @GetMapping("/fonte/{fonte}")
    public ResponseEntity<List<ConsumoEnergia>> listarPorFonte(@PathVariable String fonte) {
        return ResponseEntity.ok(consumoEnergiaService.listarPorFonte(fonte));
    }

    @PostMapping
    public ResponseEntity<ConsumoEnergia> criar(@Valid @RequestBody ConsumoEnergia consumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consumoEnergiaService.criar(consumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoEnergia> atualizar(@PathVariable Long id, @Valid @RequestBody ConsumoEnergia consumo) {
        return ResponseEntity.ok(consumoEnergiaService.atualizar(id, consumo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consumoEnergiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
