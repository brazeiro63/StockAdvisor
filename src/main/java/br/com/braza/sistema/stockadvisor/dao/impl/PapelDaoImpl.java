package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.PapelDao;
import br.com.braza.sistema.stockadvisor.dominio.Papel;

public class PapelDaoImpl implements PapelDao {

	private EntityManager em;

	public PapelDaoImpl() {
		this.em = EM.getLocalEm();
	}

	@Override
	public void inserirAtualizar(Papel x) {
		if (x.getCodPapel() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}

	@Override
	public void excluir(Papel x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Papel buscar(int cod) {
		return em.find(Papel.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Papel> buscarTodos() {
		String jpql = "SELECT x FROM Papel x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Papel> buscarTodosOrdenadosPorNome() {
		String jpql = "SELECT x FROM Papel x ORDER BY x.codigoNegociacao;";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Papel buscarPorNome(String nome) {
		String jpql = "SELECT  x FROM Papel x WHERE TRIM(x.codigoNegociacao) = TRIM(:p1) ORDER BY x.codigoNegociacao";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);

		ArrayList<Papel> r = null;

		if (query.getResultList().size() > 0) {
			r = (ArrayList<Papel>) query.getResultList();
			return r.get(0);
		}
		
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Papel buscarPorCodigoENome(Integer codigo, String nome) {
		String jpql = "SELECT  x FROM Papel x WHERE x.codPapel = :p1 AND TRIM(x.codigoNegociacao) = TRIM(:p2)";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", codigo);
		query.setParameter("p2", nome);

		ArrayList<Papel> r = null;

		if (query.getResultList().size() > 0) {
			r = (ArrayList<Papel>) query.getResultList();
			return r.get(0);
		}
		
		return null;

	}

}
