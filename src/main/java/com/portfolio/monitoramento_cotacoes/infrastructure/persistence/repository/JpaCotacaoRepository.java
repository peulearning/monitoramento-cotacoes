package com.portfolio.monitoramento_cotacoes.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.monitoramento_cotacoes.infrastructure.persistence.model.CotacaoModel;


/**
 * Interface do Spring Data JPA. É o ponto de contato direto com o banco de dados.
 */
public interface JpaCotacaoRepository extends JpaRepository<CotacaoModel, Long> {
  /**
     * Consulta customizada: busca a cotação mais recente.
     * O Spring Data JPA traduz esta declaração em uma query SQL.
     */
  Optional<CotacaoModel> findTopByMoedaOrigemAndMoedaDestinoOrderByDataRegistroDesc(String moedaOrigem, String moedaDestino);
}
