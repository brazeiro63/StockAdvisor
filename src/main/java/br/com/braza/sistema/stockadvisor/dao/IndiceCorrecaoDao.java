package br.com.braza.sistema.stockadvisor.dao;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.IndiceCorrecao;

public interface IndiceCorrecaoDao {

	public void inserirAtualizar(IndiceCorrecao x);
	public void excluir(IndiceCorrecao x);
	public IndiceCorrecao buscar(int cod);
	public List<IndiceCorrecao> buscarTodos();
	
	public List<IndiceCorrecao> buscarPorNome(String nome);
	public IndiceCorrecao buscarPorCodigoENome(Integer codigo, String indice);
	IndiceCorrecao buscarUmPorNome(String nome);
}
