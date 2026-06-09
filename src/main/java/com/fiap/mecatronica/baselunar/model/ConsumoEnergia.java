package com.fiap.mecatronica.baselunar.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Registro de consumo de energia de um setor da base lunar.
 * A fonte pode ser solar, nuclear ou bateria.
 */
@Entity
@Table(name = "consumos_energia")
public class ConsumoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O setor e obrigatorio")
    @Column(nullable = false)
    private String setor;

    @NotBlank(message = "A fonte de energia e obrigatoria")
    @Column(nullable = false)
    private String fonte;

    @NotNull(message = "A potencia em watts e obrigatoria")
    @PositiveOrZero(message = "A potencia nao pode ser negativa")
    @Column(name = "potencia_watts", nullable = false)
    private Double potenciaWatts;

    @NotNull(message = "O consumo em kWh e obrigatorio")
    @PositiveOrZero(message = "O consumo nao pode ser negativo")
    @Column(name = "consumo_kwh", nullable = false)
    private Double consumoKwh;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    public ConsumoEnergia() {
        this.dataRegistro = LocalDateTime.now();
    }

    public ConsumoEnergia(String setor, String fonte, Double potenciaWatts, Double consumoKwh) {
        this.setor = setor;
        this.fonte = fonte;
        this.potenciaWatts = potenciaWatts;
        this.consumoKwh = consumoKwh;
        this.dataRegistro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Double getPotenciaWatts() {
        return potenciaWatts;
    }

    public void setPotenciaWatts(Double potenciaWatts) {
        this.potenciaWatts = potenciaWatts;
    }

    public Double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
