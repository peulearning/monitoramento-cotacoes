package com.portfolio.monitoramento_cotacoes.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;

public interface BuscarCotacaoUseCase {

  //Retonna a ultima cotacao mais recente para o par de moedas informado
  Optional<Cotacao> getUltimaCotacao(String moedaOrigem, String moedaDestino);


  List<Cotacao> getHistorico(String moedaOrigem, String  moedaDestino);

}
