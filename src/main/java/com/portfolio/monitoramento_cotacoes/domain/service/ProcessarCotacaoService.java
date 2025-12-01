package com.portfolio.monitoramento_cotacoes.domain.service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.monitoramento_cotacoes.domain.port.out.ClienteApiCotacaoPort;
import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;
import com.portfolio.monitoramento_cotacoes.domain.port.in.ProcessarCotacaoUseCase;
import com.portfolio.monitoramento_cotacoes.domain.port.out.CotacaoRepositoryPort;



@Service
public class ProcessarCotacaoService implements ProcessarCotacaoUseCase {

    private final ClienteApiCotacaoPort clienteApiCotacao;
    private final CotacaoRepositoryPort cotacaoRepository;

    public ProcessarCotacaoService(ClienteApiCotacaoPort clienteApiCotacao,
            CotacaoRepositoryPort cotacaoRepository) {
        this.clienteApiCotacao = clienteApiCotacao;
        this.cotacaoRepository = cotacaoRepository;
    }

    @Override
    public void processarCotacao(String moedaOrigem, String moedaDestino) {
        // 1. Regra de Negócio: Buscar a cotação na fonte externa
        Optional<Cotacao> cotacaoOpt = clienteApiCotacao.buscarCotacaoExterna(moedaOrigem, moedaDestino);

        if (cotacaoOpt.isPresent()) {
            Cotacao novaCotacao = cotacaoOpt.get();

            // Definir o timestamp
            novaCotacao.setDataRegistro(LocalDateTime.now());

            // 2. Regra de Negócio: Persistir o dado no repositório
            cotacaoRepository.salvar(novaCotacao);
            System.out.println("Cotação processada e salva com sucesso: " + novaCotacao.getValor());

        } else {
            // 3. Regra de Negócio: Lidar com falha na integração
            System.err.println("Falha ao buscar cotação externa para " + moedaOrigem + "/" + moedaDestino);
        }
        // AGORA NÃO HÁ MAIS NENHUM THROW AQUI, O MÉTODO TERMINA NORMALMENTE.
    }
}
