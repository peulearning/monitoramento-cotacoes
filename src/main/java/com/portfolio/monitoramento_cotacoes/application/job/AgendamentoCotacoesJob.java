package com.portfolio.monitoramento_cotacoes.application.job;

import com.portfolio.monitoramento_cotacoes.domain.port.in.ProcessarCotacaoUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoCotacoesJob {

    private final ProcessarCotacaoUseCase processarCotacaoUseCase;

    // O Spring injeta o Caso de Uso do Domínio
    public AgendamentoCotacoesJob(ProcessarCotacaoUseCase processarCotacaoUseCase) {
        this.processarCotacaoUseCase = processarCotacaoUseCase;
    }

    /**
     * Agenda a execução a cada 30 minutos (1800000 milissegundos).
     * O JOB chama a lógica de negócio (ProcessarCotacaoService)
     */
    @Scheduled(fixedRate = 1800000)
    public void buscarESalvarCotacaoJob() {

        System.out.println("--- JOB INICIADO: Buscando e salvando cotação USD/BRL. ---");

        // Chamada ao Domínio: O job não sabe como o dado é buscado, apenas pede o processamento.
        processarCotacaoUseCase.processarCotacao("USD", "BRL");

        System.out.println("--- JOB FINALIZADO ---");
    }
}