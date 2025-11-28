package com.portfolio.monitoramento_cotacoes.domain.port.out;

// Importando a classe Cotacao
import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;

//Importando o optional
import java.util.Optional;

//Interface para acessar API externa de cotações
public interface ClienteApiCotacaoPort {

  //Metódo para ser utilizada para buscar em API's externas
  Optional<Cotacao> buscarCotacaoExterna(String moedaOrigem, String moedaDestino);
}
