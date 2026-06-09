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

/**
 * Alerta operacional gerado pela base lunar.
 * Representa eventos que exigem atencao da equipe (ex.: nivel critico de oxigenio,
 * falha de energia, pressao fora da faixa segura).
 */
@Entity
@Table(name = "alertas_operacionais")
public class AlertaOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo do alerta e obrigatorio")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "A descricao do alerta e obrigatoria")
    @Column(nullable = false, length = 500)
    private String descricao;

    @NotBlank(message = "O nivel de severidade e obrigatorio")
    @Column(nullable = false)
    private String nivel;

    @NotBlank(message = "A origem do alerta e obrigatoria")
    @Column(nullable = false)
    private String origem;

    @NotNull
    @Column(nullable = false)
    private Boolean resolvido;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    public AlertaOperacional() {
        this.resolvido = false;
        this.dataHora = LocalDateTime.now();
    }

    public AlertaOperacional(String titulo, String descricao, String nivel,
                             String origem, Boolean resolvido) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.origem = origem;
        this.resolvido = resolvido != null ? resolvido : false;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Boolean getResolvido() {
        return resolvido;
    }

    public void setResolvido(Boolean resolvido) {
        this.resolvido = resolvido;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
