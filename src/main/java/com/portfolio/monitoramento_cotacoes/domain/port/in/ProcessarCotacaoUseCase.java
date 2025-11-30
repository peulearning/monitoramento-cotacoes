package com.portfolio.monitoramento_cotacoes.domain.port.in;


public interface ProcessarCotacaoUseCase {

  //Executar a busca na API externa e salvar o resultado no BD
  void processarCotacao(String moedaOrigem, String moedaDestino);

}