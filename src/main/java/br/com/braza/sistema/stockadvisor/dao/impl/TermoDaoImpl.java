package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.TermoDao;
import br.com.braza.sistema.stockadvisor.dominio.Termo;


public class TermoDaoImpl implements TermoDao {

	private EntityManager em;

	public TermoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Termo x) {
		if (x.getCodPapel() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Termo x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Termo buscar(int cod) {
		return em.find(Termo.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Termo> buscarTodos() {
		String jpql = "SELECT x FROM Termo x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Termo> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM Termo x WHERE TRIM(x.codigoNegociacao) = TRIM(:p1) ORDER BY x.codigoNegociacao";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}
