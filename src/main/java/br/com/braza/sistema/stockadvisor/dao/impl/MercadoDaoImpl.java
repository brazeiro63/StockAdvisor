package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.MercadoDao;
import br.com.braza.sistema.stockadvisor.dominio.Mercado;


public class MercadoDaoImpl implements MercadoDao {

	private EntityManager em;

	public MercadoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Mercado x) {
		if (x.getTipo() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Mercado x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Mercado buscar(int cod) {
		return em.find(Mercado.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mercado> buscarTodos() {
		String jpql = "SELECT x FROM Mercado x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mercado> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM Mercado x WHERE TRIM(x.nome) = TRIM(:p1)";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}
