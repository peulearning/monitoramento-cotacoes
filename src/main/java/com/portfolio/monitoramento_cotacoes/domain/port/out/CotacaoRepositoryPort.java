package com.portfolio.monitoramento_cotacoes.domain.port.out;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



public interface CotacaoRepositoryPort {

  Cotacao salvar(Cotacao cotacao);

  Optional<Cotacao> buscarUltimaCotacao(String moedaOrigem, String moedaDestino);

  List<Cotacao> buscarHistorico(String moedaOrigem, String moedaDestino, LocalDateTime dataRegistro);
}
