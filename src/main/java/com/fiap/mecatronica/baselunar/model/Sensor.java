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
 * Sensor ambiental instalado em um modulo da base lunar.
 * Ex.: sensores de temperatura, pressao, radiacao, oxigenio e CO2.
 */
@Entity
@Table(name = "sensores")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do sensor e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O tipo do sensor e obrigatorio")
    @Column(nullable = false)
    private String tipo;

    @NotBlank(message = "O modulo (localizacao) e obrigatorio")
    @Column(nullable = false)
    private String modulo;

    @NotBlank(message = "A unidade de medida e obrigatoria")
    @Column(nullable = false)
    private String unidade;

    @Column(name = "valor_atual")
    private Double valorAtual;

    @Column(name = "limite_minimo")
    private Double limiteMinimo;

    @Column(name = "limite_maximo")
    private Double limiteMaximo;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    public Sensor() {
        this.ativo = true;
    }

    public Sensor(String nome, String tipo, String modulo, String unidade,
                  Double valorAtual, Double limiteMinimo, Double limiteMaximo, Boolean ativo) {
        this.nome = nome;
        this.tipo = tipo;
        this.modulo = modulo;
        this.unidade = unidade;
        this.valorAtual = valorAtual;
        this.limiteMinimo = limiteMinimo;
        this.limiteMaximo = limiteMaximo;
        this.ativo = ativo != null ? ativo : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getLimiteMinimo() {
        return limiteMinimo;
    }

    public void setLimiteMinimo(Double limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }

    public Double getLimiteMaximo() {
        return limiteMaximo;
    }

    public void setLimiteMaximo(Double limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
