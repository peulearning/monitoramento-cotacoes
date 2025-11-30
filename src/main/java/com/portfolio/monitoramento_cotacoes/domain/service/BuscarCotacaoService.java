package com.portfolio.monitoramento_cotacoes.domain.service;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import com.portfolio.monitoramento_cotacoes.domain.port.in.BuscarCotacaoUseCase;
import com.portfolio.monitoramento_cotacoes.domain.port.out.CotacaoRepositoryPort;
import org.springframework.stereotype.Service; // Anotação para ser gerenciado pelo Spring

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service // Marca como um componente de serviço Spring
public class BuscarCotacaoService implements BuscarCotacaoUseCase {

    private final CotacaoRepositoryPort cotacaoRepository;

    // Injeção de dependência via construtor (DIP - Inversão de Dependência)
    public BuscarCotacaoService(CotacaoRepositoryPort cotacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
    }

    @Override
    public Optional<Cotacao> getUltimaCotacao(String moedaOrigem, String moedaDestino) {
        // Regra de Negócio: Simplesmente busca a última cotação registrada.
        return cotacaoRepository.buscarUltimaCotacao(moedaOrigem, moedaDestino);
    }

    @Override
    public List<Cotacao> getHistorico(String moedaOrigem, String moedaDestino) {
        // Regra de Negócio: Validação básica do período (se necessário).
        LocalDateTime inicio = LocalDateTime.now().minusDays(30); // Exemplo: último mês
        LocalDateTime fim = LocalDateTime.now();
        
        if (inicio.isAfter(fim)) {
            // Poderia lançar uma exceção de domínio aqui
            throw new IllegalArgumentException("Data de início não pode ser posterior à data final.");
        }
        return cotacaoRepository.buscarHistorico(moedaOrigem, moedaDestino, LocalDateTime.now());
    }
}