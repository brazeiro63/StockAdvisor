package br.com.braza.sistema.stockadvisor.servico;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.PapelDao;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.Papel;

public class PapelServico {

	private PapelDao dao;

	public PapelServico() {
		dao = DaoFactory.criarPapelDao();
	}

	public void Inserir(Papel x) throws ServicoException {
		try {
			Papel aux = dao.buscarPorNome(x.getCodigoNegociacao());

			if (aux != null) {
				throw new ServicoException("Já existe papel com o mesmo código de negociação cadastrado.", 1);
			}

			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();

		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public void atualizar(Papel x) throws ServicoException {
		try {
			Papel aux = dao.buscarPorCodigoENome(x.getCodPapel(), x.getCodigoNegociacao());

			if (aux != null) {
				throw new ServicoException("Já existe papel com este nome.", 2);
			}

			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();
		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void excluir(Papel x) throws ServicoException {
		try {
			x = dao.buscar(x.getCodPapel());
			if (!x.getCotacoes().isEmpty()) {
				throw new ServicoException("Exclusão não permitida: este papel possui cotacoes!", 3);
			}
			
			Transaction.begin();
			dao.excluir(x);
			Transaction.commit();
		}
		catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public Papel buscar(int cod) {
		return dao.buscar(cod);
	}
	
	public List<Papel> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<Papel> buscarTodosOrdenadosPorNome() {
		return dao.buscarTodosOrdenadosPorNome();
	}
	
	public Papel buscarPorNome(String trecho) {
		return dao.buscarPorNome(trecho);
	}
}
