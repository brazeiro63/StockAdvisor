package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Mercado;

public interface MercadoDao {

	public void inserirAtualizar(Mercado x);
	public void excluir(Mercado x);
	public Mercado buscar(int cod);
	public List<Mercado> buscarTodos();
	
	public List<Mercado> buscarPorNome(String nome);
	public Mercado buscarPorCodigoENome(Integer tipo, String nome);
}
