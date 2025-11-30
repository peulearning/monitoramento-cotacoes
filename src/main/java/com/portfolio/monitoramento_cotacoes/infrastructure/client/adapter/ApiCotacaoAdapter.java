package com.portfolio.monitoramento_cotacoes.infrastructure.client.adapter;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import com.portfolio.monitoramento_cotacoes.domain.port.out.ClienteApiCotacaoPort;
import com.portfolio.monitoramento_cotacoes.infrastructure.client.dto.CotacaoApiDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ApiCotacaoAdapter implements ClienteApiCotacaoPort {

    private final WebClient webClient;

    public ApiCotacaoAdapter(@Value("${api.cotacao.url}") String apiUrl) {
        // O WebClient é injetado com a URL base definida no application.properties
        this.webClient = WebClient.builder().baseUrl(apiUrl).build();
    }

    private Cotacao toDomain(CotacaoApiDTO dto) {
        return new Cotacao(
            null, // ID nulo, será gerado na persistência
            dto.getBase(),
            dto.getTarget(),
            dto.getValor(),
            LocalDateTime.now()
        );
    }

    @Override
    public Optional<Cotacao> buscarCotacaoExterna(String moedaOrigem, String moedaDestino) {

        String endpoint = String.format("/latest/%s/%s", moedaOrigem, moedaDestino);

        try {
            CotacaoApiDTO dto = webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(CotacaoApiDTO.class)
                .block(); // Usamos .block() pois esta chamada está dentro de um Job agendado (contexto síncrono)

            if (dto != null && dto.getValor() != null) {
                return Optional.of(toDomain(dto));
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Erro ao buscar cotação externa: " + e.getMessage());
            return Optional.empty();
        }
    }
}