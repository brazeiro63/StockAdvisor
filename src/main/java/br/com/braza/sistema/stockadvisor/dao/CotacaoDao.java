package br.com.braza.sistema.stockadvisor.dao;

import java.util.Date;
import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Cotacao;

public interface CotacaoDao {

	public void inserirAtualizar(Cotacao x);
	public void excluir(Cotacao x);
	public Cotacao buscar(int cod);
	public List<Cotacao> buscarTodos();
	
	public List<Cotacao> buscarPorData(Date data);
	public Cotacao buscarPorNomeEData(String codigoNegociacao, Date dataCotacao);
	public Cotacao buscarPorCodigoNomeEData(Integer codCotacao, String codigoNegociacao, Date dataCotacao);
}
