package com.fiap.mecatronica.baselunar.model;

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
 * Reservatorio de recursos vitais da base lunar.
 * Ex.: agua, oxigenio, combustivel e nitrogenio.
 */
@Entity
@Table(name = "reservatorios")
public class Reservatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do reservatorio e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O tipo de recurso e obrigatorio")
    @Column(name = "tipo_recurso", nullable = false)
    private String tipoRecurso;

    @NotNull(message = "A capacidade maxima e obrigatoria")
    @PositiveOrZero(message = "A capacidade maxima nao pode ser negativa")
    @Column(name = "capacidade_maxima", nullable = false)
    private Double capacidadeMaxima;

    @NotNull(message = "O nivel atual e obrigatorio")
    @PositiveOrZero(message = "O nivel atual nao pode ser negativo")
    @Column(name = "nivel_atual", nullable = false)
    private Double nivelAtual;

    @NotBlank(message = "A unidade de medida e obrigatoria")
    @Column(nullable = false)
    private String unidade;

    @NotBlank(message = "O modulo (localizacao) e obrigatorio")
    @Column(nullable = false)
    private String modulo;

    public Reservatorio() {
    }

    public Reservatorio(String nome, String tipoRecurso, Double capacidadeMaxima,
                        Double nivelAtual, String unidade, String modulo) {
        this.nome = nome;
        this.tipoRecurso = tipoRecurso;
        this.capacidadeMaxima = capacidadeMaxima;
        this.nivelAtual = nivelAtual;
        this.unidade = unidade;
        this.modulo = modulo;
    }

    /**
     * Percentual de preenchimento do reservatorio (0 a 100).
     * Campo calculado, nao persistido.
     */
    public Double getPercentualOcupacao() {
        if (capacidadeMaxima == null || capacidadeMaxima == 0 || nivelAtual == null) {
            return 0.0;
        }
        return Math.round((nivelAtual / capacidadeMaxima) * 1000.0) / 10.0;
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

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Double getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(Double nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}
