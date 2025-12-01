package com.portfolio.monitoramento_cotacoes.infrastructure.client.adapter;

import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import com.portfolio.monitoramento_cotacoes.domain.port.out.ClienteApiCotacaoPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ApiCotacaoAdapter implements ClienteApiCotacaoPort {

    private final WebClient webClient;
    private final String apiKey;

    public ApiCotacaoAdapter(
        @Value("${api.cotacao.url}") String apiUrl,
        @Value("${api.cotacao.key}") String apiKey) {

        this.webClient = WebClient.builder().baseUrl(apiUrl).build();
        this.apiKey = apiKey;
    }

    private Cotacao toDomain(BigDecimal valor) {
        // Agora, assumimos que o valor já é o valor de BRL
        return new Cotacao(
            null,
            "USD", // Fixamos a moeda de origem
            "BRL", // Fixamos a moeda de destino
            valor,
            LocalDateTime.now()
        );
    }

   @Override
    public Optional<Cotacao> buscarCotacaoExterna(String moedaOrigem, String moedaDestino) {

        // A API exige que a moeda base (origem) esteja na URL.
        // Vamos forçar o uso de USD como moeda base (Origem)
        String endpoint = String.format("/%s/latest/%s", apiKey, "USD");

        try {
            // A API retorna um JSON mais complexo, contendo um objeto "conversion_rates".
            // Para simplificar, vamos pedir para o WebClient mapear diretamente para um Map
            // ou um DTO que contenha o campo "conversion_rates".

            // Usaremos um Map para mapear a resposta complexa:
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> responseMap = webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(java.util.Map.class)
                .block();

            if (responseMap == null || responseMap.get("conversion_rates") == null) {
                return Optional.empty();
            }

            // Extrai as taxas de conversão
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> rates = (java.util.Map<String, Object>) responseMap.get("conversion_rates");

            // Pega o valor de BRL (a moeda destino)
            Object brlRate = rates.get("BRL");

            if (brlRate instanceof Number) {
                // Converte para BigDecimal
                BigDecimal valorBRL = new BigDecimal(brlRate.toString());
                return Optional.of(toDomain(valorBRL));
            }

            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Erro ao buscar cotação externa: " + e.getMessage());
            // Se a chave for inválida ou limite excedido, o erro cairá aqui.
            return Optional.empty();
        }
    }
}