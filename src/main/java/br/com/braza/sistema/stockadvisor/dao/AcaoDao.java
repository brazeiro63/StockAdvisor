package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Acao;

public interface AcaoDao {

	public void inserirAtualizar(Acao x);
	public void excluir(Acao x);
	public Acao buscar(int cod);
	public List<Acao> buscarTodos();
	
	public List<Acao> buscarPorNome(String nome);
}
