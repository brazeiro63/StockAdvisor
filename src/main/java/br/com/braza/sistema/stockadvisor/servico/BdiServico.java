package br.com.braza.sistema.stockadvisor.servico;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dao.BdiDao;
import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.Bdi;

public class BdiServico {

	private BdiDao dao;

	public BdiServico() {
		dao = DaoFactory.criarBdiDao();
	}

	public void Inserir(Bdi x) throws ServicoException {
		try {
			Bdi aux = dao.buscarPorNome(x.getNome()).get(0);
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

	public void atualizar(Bdi x) throws ServicoException {
		try {
			Bdi aux = dao.buscarPorCodigoENome(x.getCodigo(), x.getNome());

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

	public void excluir(Bdi x) throws ServicoException {
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
	
	public Bdi buscar(int cod) {
		return dao.buscar(cod);
	}
	
	public List<Bdi> buscarTodos() {
		return dao.buscarTodos();
	}
	
}
