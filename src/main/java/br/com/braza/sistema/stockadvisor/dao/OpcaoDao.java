package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Opcao;

public interface OpcaoDao {

	public void inserirAtualizar(Opcao x);
	public void excluir(Opcao x);
	public Opcao buscar(int cod);
	public List<Opcao> buscarTodos();
	
	public List<Opcao> buscarPorNome(String nome);
}
