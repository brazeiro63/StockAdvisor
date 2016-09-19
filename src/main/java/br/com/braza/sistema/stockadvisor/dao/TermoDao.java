package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Termo;

public interface TermoDao {

	public void inserirAtualizar(Termo x);
	public void excluir(Termo x);
	public Termo buscar(int cod);
	public List<Termo> buscarTodos();
	
	public List<Termo> buscarPorNome(String nome);
}
