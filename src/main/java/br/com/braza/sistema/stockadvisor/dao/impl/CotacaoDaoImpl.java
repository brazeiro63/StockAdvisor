package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.CotacaoDao;
import br.com.braza.sistema.stockadvisor.dominio.Cotacao;


public class CotacaoDaoImpl implements CotacaoDao {

	private EntityManager em;

	public CotacaoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Cotacao x) {
		if (x.getCodCotacao() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Cotacao x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Cotacao buscar(int cod) {
		return em.find(Cotacao.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cotacao> buscarTodos() {
		String jpql = "SELECT x FROM Cotacao x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cotacao> buscarPorData(Date data) {
		String jpql = "SELECT x FROM Cotacao x WHERE x.dataCotacao = :p1";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", data);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cotacao buscarPorNomeEData(String codigoNegociacao, Date dataCotacao) {
		String jpql = "SELECT  x FROM Cotacao x WHERE x.papel.codigoNegociacao = :p1 AND dataCotacao = :p2";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", codigoNegociacao);
		query.setParameter("p2", dataCotacao);

		ArrayList<Cotacao> r = null;

		if (query.getResultList().size() > 0) {
			r = (ArrayList<Cotacao>) query.getResultList();
			return r.get(0);
		}
		
		return null;

	}


	@SuppressWarnings("unchecked")
	@Override
	public Cotacao buscarPorCodigoNomeEData(Integer codCotacao, String codigoNegociacao, Date dataCotacao) {
		String jpql = "SELECT  x FROM Cotacao x WHERE x.codCotacao = :p1 AND TRIM(x.codigoNegociacao) = TRIM(:p2) AND x.dataCotacao = :p3";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", codCotacao);
		query.setParameter("p2", codigoNegociacao);
		query.setParameter("p3", dataCotacao);

		ArrayList<Cotacao> r = null;

		if (query.getResultList().size() > 0) {
			r = (ArrayList<Cotacao>) query.getResultList();
			return r.get(0);
		}
		
		return null;

	}
	
}
