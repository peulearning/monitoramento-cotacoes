package com.portfolio.monitoramento_cotacoes.domain.port.out;

//Importando a classe Cotacao, que representa uma Cotacao
import com.portfolio.monitoramento_cotacoes.domain.entity.Cotacao;

//Importando classes necessárias para trabalhar com datas e coleções
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


//Interface que define os métodos para interagir com as cotações
public interface CotacaoRepositoryPort {

  // Métdoo para salvar uma nova cotação, Retorna a cotação salva
  Cotacao salvar(Cotacao cotacao);

  //Método para buscar a ultima cotação entre duas moedas. Pode retornar um valor vazio se não houver cotação
  Optional<Cotacao> buscarUltimaCotacao(String moedaOrigem, String moedaDestino);

  //Método para buscar no histórico de cotações entre duas moedas em uma data específica
  List<Cotacao> buscarHistorico(String moedaOrigem, String moedaDestino, LocalDateTime dataRegistro);
}
