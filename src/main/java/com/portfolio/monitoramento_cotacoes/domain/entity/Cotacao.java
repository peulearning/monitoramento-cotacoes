package com.portfolio.monitoramento_cotacoes.domain.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade de domínio que representa o registro de uma cotação de moeda.
 * Não contém anotações de persistência (JPA) para manter a independência.
 */
public class Cotacao {

    private Long id;
    private String moedaOrigem; // Ex: USD
    private String moedaDestino; // Ex: BRL
    private BigDecimal valor;
    private LocalDateTime dataRegistro;

    // Construtor completo
    public Cotacao(Long id, String moedaOrigem, String moedaDestino, BigDecimal valor, LocalDateTime dataRegistro) {
        this.id = id;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
    }

    // Construtor para nova cotação (sem ID)
    public Cotacao(String moedaOrigem, String moedaDestino, BigDecimal valor, LocalDateTime dataRegistro) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
    }

    // --- Getters e Setters (Usando Lombok seria mais limpo, mas aqui estão explícitos) ---

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