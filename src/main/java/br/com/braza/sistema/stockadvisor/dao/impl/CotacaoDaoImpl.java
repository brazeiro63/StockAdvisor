package br.com.braza.sistema.stockadvisor.dao.impl;

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
	
}
