package br.com.braza.sistema.stockadvisor.servico;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dao.IndiceCorrecaoDao;
import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.IndiceCorrecao;

public class IndiceCorrecaoServico {

	private IndiceCorrecaoDao dao;

	public IndiceCorrecaoServico() {
		dao = DaoFactory.criarIndiceCorrecaoDao();
	}

	public void Inserir(IndiceCorrecao x) throws ServicoException {
		try {
			IndiceCorrecao aux = dao.buscarUmPorNome(x.getIndice());
			if (aux != null) {
				throw new ServicoException("Já existe Indice de Correcao com o mesmo indice cadastrado.", 1);
			}

			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();

		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro ao inserir ou atualizar Indice de Correcao: " + e.getMessage());
		}

	}

	public void atualizar(IndiceCorrecao x) throws ServicoException {
		try {
			IndiceCorrecao aux = dao.buscarPorCodigoENome(x.getCodigo(), x.getIndice());

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

	public void excluir(IndiceCorrecao x) throws ServicoException {
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
	
	public IndiceCorrecao buscar(int cod) {
		return dao.buscar(cod);
	}
	
	public List<IndiceCorrecao> buscarTodos() {
		return dao.buscarTodos();
	}
	
}
