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
 * Recurso operacional generico da base lunar, no formato consumido diretamente
 * pelo aplicativo mobile (tela Home, Cadastro e Alertas).
 *
 * Mantem um modelo simples (nome, tipo, nivelAtual, status) que espelha o
 * contrato esperado pelo front-end em GET/POST /api/recursos.
 */
@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do recurso e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O tipo/categoria do recurso e obrigatorio")
    @Column(nullable = false)
    private String tipo;

    @NotNull(message = "O nivel atual e obrigatorio")
    @Column(name = "nivel_atual", nullable = false)
    private Double nivelAtual;

    @Column(nullable = false)
    private String status;

    public Recurso() {
    }

    public Recurso(String nome, String tipo, Double nivelAtual, String status) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivelAtual = nivelAtual;
        this.status = status;
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

    public Double getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(Double nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
