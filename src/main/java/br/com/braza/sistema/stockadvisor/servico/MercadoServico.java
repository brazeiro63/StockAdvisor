package br.com.braza.sistema.stockadvisor.servico;

import java.util.List;

import br.com.braza.sistema.stockadvisor.dao.MercadoDao;
import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.Mercado;

public class MercadoServico {

	private MercadoDao dao;

	public MercadoServico() {
		dao = DaoFactory.criarMercadoDao();
	}

	public void Inserir(Mercado x) throws ServicoException {
		try {
			Mercado aux = dao.buscarUmPorNome(x.getNome());
			if (aux != null) {
				throw new ServicoException("Já existe Mercado com o mesmo nome cadastrado.", 1);
			}

			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();

		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro ao inserir Mercado: "  + x.toString()+ "\n" + e.getMessage());
		}

	}

	public void atualizar(Mercado x) throws ServicoException {
		try {
			Mercado aux = dao.buscarPorCodigoENome(x.getTipo(), x.getNome());

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
			System.out.println("Erro ao atualizar Mercado: "  + x.toString()+ "\n" + e.getMessage());
		}
	}

	public void excluir(Mercado x) throws ServicoException {
		try {
			Transaction.begin();
			dao.excluir(x);
			Transaction.commit();
		}
		catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			System.out.println("Erro ao excluir Mercado: "  + x.toString()+ "\n" + e.getMessage());
		}
	}
	
	public Mercado buscar(int cod) {
		return dao.buscar(cod);
	}
	
	public List<Mercado> buscarTodos() {
		return dao.buscarTodos();
	}
	
}
