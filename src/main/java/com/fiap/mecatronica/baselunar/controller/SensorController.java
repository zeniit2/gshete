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

import com.fiap.mecatronica.baselunar.model.Sensor;
import com.fiap.mecatronica.baselunar.service.SensorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "*")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<Sensor>> listarTodos() {
        return ResponseEntity.ok(sensorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Sensor>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(sensorService.listarPorTipo(tipo));
    }

    @GetMapping("/modulo/{modulo}")
    public ResponseEntity<List<Sensor>> listarPorModulo(@PathVariable String modulo) {
        return ResponseEntity.ok(sensorService.listarPorModulo(modulo));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Sensor>> listarAtivos() {
        return ResponseEntity.ok(sensorService.listarAtivos());
    }

    @PostMapping
    public ResponseEntity<Sensor> criar(@Valid @RequestBody Sensor sensor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sensorService.criar(sensor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizar(@PathVariable Long id, @Valid @RequestBody Sensor sensor) {
        return ResponseEntity.ok(sensorService.atualizar(id, sensor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sensorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
