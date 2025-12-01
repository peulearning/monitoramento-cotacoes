package com.portfolio.monitoramento_cotacoes.infrastructure.persistence.adapter;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import com.portfolio.monitoramento_cotacoes.domain.port.out.CotacaoRepositoryPort;
import com.portfolio.monitoramento_cotacoes.infrastructure.persistence.model.CotacaoModel;
import com.portfolio.monitoramento_cotacoes.infrastructure.persistence.repository.JpaCotacaoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CotacaoRepositoryAdapter implements CotacaoRepositoryPort {

    private final JpaCotacaoRepository jpaRepository;

    public CotacaoRepositoryAdapter(JpaCotacaoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    // --- Métodos de Conversão ---
    private Cotacao toDomain(CotacaoModel model) {
        // Converte Model (JPA) para Entidade (Domínio)
        return new Cotacao(
            model.getId(),
            model.getMoedaOrigem(),
            model.getMoedaDestino(),
            model.getValor(),
            model.getDataRegistro()
        );
    }

    private CotacaoModel toModel(Cotacao domain) {
        // Converte Entidade (Domínio) para Model (JPA)
        return new CotacaoModel(
            domain.getId(),
            domain.getMoedaOrigem(),
            domain.getMoedaDestino(),
            domain.getValor(),
            domain.getDataRegistro()
        );
    }

    // --- Implementação da Porta ---

    @Override
    public Cotacao salvar(Cotacao cotacao) {
        // O Adapter é responsável pela transação e conversão
        CotacaoModel savedModel = jpaRepository.save(toModel(cotacao));
        return toDomain(savedModel);
    }

    @Override
    public Optional<Cotacao> buscarUltimaCotacao(String moedaOrigem, String moedaDestino) {
        return jpaRepository
                .findTopByMoedaOrigemAndMoedaDestinoOrderByDataRegistroDesc(moedaOrigem, moedaDestino)
                .map(this::toDomain); // Mapeia o Model encontrado de volta para a Entidade de Domínio
    }

    @Override
    public List<Cotacao> buscarHistorico(String moedaOrigem, String moedaDestino) {
        Optional<CotacaoModel> models = jpaRepository.findTopByMoedaOrigemAndMoedaDestinoOrderByDataRegistroDesc(
                moedaOrigem, moedaDestino
        );

        return models.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cotacao> buscarHistorico(String moedaOrigem, String moedaDestino, LocalDateTime dataRegistro) {

      throw new UnsupportedOperationException("Unimplemented method 'buscarHistorico'");
    }
}