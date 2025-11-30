package com.portfolio.monitoramento_cotacoes.infrastructure.client.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoApiDto {

  private String base;
  private String target;


  @JsonProperty("value")
  private BigDecimal valor;

    public String getBase() { return base; }
    public void setBase(String base) { this.base = base; }
    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }


}
