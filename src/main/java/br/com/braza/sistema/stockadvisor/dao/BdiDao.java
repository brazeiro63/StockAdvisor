package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Bdi;

public interface BdiDao {

	public void inserirAtualizar(Bdi x);
	public void excluir(Bdi x);
	public Bdi buscar(int cod);
	public List<Bdi> buscarTodos();
	
	public List<Bdi> buscarPorNome(String nome);
}
