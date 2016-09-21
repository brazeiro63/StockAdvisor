package br.com.braza.sistema.stockadvisor.servico;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dao.CotacaoDao;
import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.Cotacao;

public class CotacaoServico {

	private CotacaoDao dao;

	public CotacaoServico() {
		dao = DaoFactory.criarCotacaoDao();
	}

	public void Inserir(Cotacao x) throws ServicoException {
		try {
			Cotacao aux = dao.buscarPorNomeEData(x.getPapel().getCodigoNegociacao(), x.getDataCotacao());

			if (aux != null) {
				throw new ServicoException("Já existe cotacao para o mesmo papel na mesma data.", 1);
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

	public void atualizar(Cotacao x) throws ServicoException {
		try {
			Cotacao aux = dao.buscarPorCodigoNomeEData(x.getCodCotacao(), x.getPapel().getCodigoNegociacao(), x.getDataCotacao());

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

	public void excluir(Cotacao x) throws ServicoException {
		try {
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
	
	public Cotacao buscar(int cod) {
		return dao.buscar(cod);
	}
	
	public List<Cotacao> buscarTodos() {
		return dao.buscarTodos();
	}
}
