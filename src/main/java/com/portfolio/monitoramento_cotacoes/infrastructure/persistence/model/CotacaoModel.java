package com.portfolio.monitoramento_cotacoes.infrastructure.persistence.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotacao")
public class CotacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moedaOrigem;
    private String moedaDestino;
    private BigDecimal valor;
    private LocalDateTime dataRegistro;

    // Construtor vazio (obrigatório para JPA)
    public CotacaoModel() {}

    // Construtor completo
    public CotacaoModel(Long id, String moedaOrigem, String moedaDestino, BigDecimal valor, LocalDateTime dataRegistro) {
        this.id = id;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
    }

    // --- Getters e Setters (Necessários para JPA) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMoedaOrigem() { return moedaOrigem; }
    public void setMoedaOrigem(String moedaOrigem) { this.moedaOrigem = moedaOrigem; }
    public String getMoedaDestino() { return moedaDestino; }
    public void setMoedaDestino(String moedaDestino) { this.moedaDestino = moedaDestino; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }
}
