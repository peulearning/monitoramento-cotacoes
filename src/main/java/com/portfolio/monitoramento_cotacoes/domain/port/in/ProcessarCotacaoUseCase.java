package com.portfolio.monitoramento_cotacoes.domain.port.in;

import java.util.Optional;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;

public interface ProcessarCotacaoUseCase {

  //Executar a busca na API externa e salvar o resultado no BD
  Optional<Cotacao> processarCotacao(String moedaOrigem, String moedaDestino);

}
