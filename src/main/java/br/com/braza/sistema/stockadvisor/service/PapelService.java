package br.com.braza.sistema.stockadvisor.service;

import br.com.braza.sistema.stockadvisor.dao.DaoFactory;
import br.com.braza.sistema.stockadvisor.dao.PapelDao;
import br.com.braza.sistema.stockadvisor.dao.Transaction;
import br.com.braza.sistema.stockadvisor.dominio.Papel;

public class PapelService {

	private PapelDao dao;

	public PapelService() {
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

}
