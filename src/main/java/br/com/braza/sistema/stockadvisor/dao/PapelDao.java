package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Papel;

public interface PapelDao {

	public void inserirAtualizar(Papel x);
	public void excluir(Papel x);
	public Papel buscar(int cod);
	public List<Papel> buscarTodos();
	
	public Papel buscarPorNome(String nome);
	public Papel buscarPorCodigoENome(Integer codPapel, String codigoNegociacao);
}
