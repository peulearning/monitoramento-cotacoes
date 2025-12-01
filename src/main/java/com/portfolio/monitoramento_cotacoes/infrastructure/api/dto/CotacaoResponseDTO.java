package com.portfolio.monitoramento_cotacoes.infrastructure.api.dto;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de Resposta para os clientes da API.
 */
public class CotacaoResponseDTO {
    private String moedaOrigem;
    private String moedaDestino;
    private BigDecimal valor;
    private LocalDateTime dataRegistro;

    // Construtor a partir da Entidade de Domínio
    public CotacaoResponseDTO(Cotacao cotacao) {
        this.moedaOrigem = cotacao.getMoedaOrigem();
        this.moedaDestino = cotacao.getMoedaDestino();
        this.valor = cotacao.getValor();
        this.dataRegistro = cotacao.getDataRegistro();
    }

    // --- Getters (Omitidos aqui por brevidade) ---
    public String getMoedaOrigem() { return moedaOrigem; }
    public String getMoedaDestino() { return moedaDestino; }
    public BigDecimal getValor() { return valor; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    // ---
}