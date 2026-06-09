package com.fiap.mecatronica.baselunar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Unidade de climatizacao (controle de temperatura, umidade e pressao)
 * de um modulo da base lunar.
 */
@Entity
@Table(name = "climatizacoes")
public class Climatizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modulo e obrigatorio")
    @Column(nullable = false)
    private String modulo;

    @NotNull(message = "A temperatura alvo e obrigatoria")
    @Column(name = "temperatura_alvo", nullable = false)
    private Double temperaturaAlvo;

    @Column(name = "temperatura_atual")
    private Double temperaturaAtual;

    @Column(name = "umidade_relativa")
    private Double umidadeRelativa;

    @Column(name = "pressao_kpa")
    private Double pressaoKpa;

    @NotBlank(message = "O modo de operacao e obrigatorio")
    @Column(nullable = false)
    private String modo;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    public Climatizacao() {
        this.ativo = true;
        this.modo = "automatico";
    }

    public Climatizacao(String modulo, Double temperaturaAlvo, Double temperaturaAtual,
                        Double umidadeRelativa, Double pressaoKpa, String modo, Boolean ativo) {
        this.modulo = modulo;
        this.temperaturaAlvo = temperaturaAlvo;
        this.temperaturaAtual = temperaturaAtual;
        this.umidadeRelativa = umidadeRelativa;
        this.pressaoKpa = pressaoKpa;
        this.modo = modo != null ? modo : "automatico";
        this.ativo = ativo != null ? ativo : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Double getTemperaturaAlvo() {
        return temperaturaAlvo;
    }

    public void setTemperaturaAlvo(Double temperaturaAlvo) {
        this.temperaturaAlvo = temperaturaAlvo;
    }

    public Double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(Double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public Double getUmidadeRelativa() {
        return umidadeRelativa;
    }

    public void setUmidadeRelativa(Double umidadeRelativa) {
        this.umidadeRelativa = umidadeRelativa;
    }

    public Double getPressaoKpa() {
        return pressaoKpa;
    }

    public void setPressaoKpa(Double pressaoKpa) {
        this.pressaoKpa = pressaoKpa;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
