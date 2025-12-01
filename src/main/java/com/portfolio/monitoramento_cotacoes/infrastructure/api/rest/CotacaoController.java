package com.portfolio.monitoramento_cotacoes.infrastructure.api.rest;

import com.portfolio.monitoramento_cotacoes.domain.port.in.BuscarCotacaoUseCase;
import com.portfolio.monitoramento_cotacoes.infrastructure.api.dto.CotacaoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    private final BuscarCotacaoUseCase buscarCotacaoUseCase;

    public CotacaoController(BuscarCotacaoUseCase buscarCotacaoUseCase) {
        this.buscarCotacaoUseCase = buscarCotacaoUseCase;
    }

    /**
     * Endpoint para buscar a cotação mais recente (Ex: /api/cotacoes/latest?origem=USD&destino=BRL)
     */
    @GetMapping("/latest")
    public ResponseEntity<CotacaoResponseDTO> getUltimaCotacao(
            @RequestParam String origem,
            @RequestParam String destino) {

        return buscarCotacaoUseCase.getUltimaCotacao(origem, destino)
                .map(CotacaoResponseDTO::new) // Converte a Entidade para o DTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para buscar o histórico em um período.
     * Ex: /api/cotacoes/historico?origem=USD&destino=BRL&inicio=2025-01-01T00:00:00&fim=2025-01-31T23:59:59
     */
    @GetMapping("/historico")
    public List<CotacaoResponseDTO> getHistorico(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {

        return buscarCotacaoUseCase.getHistorico(origem, destino).stream()
                .map(CotacaoResponseDTO::new)
                .collect(Collectors.toList());
    }
}