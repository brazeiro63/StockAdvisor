package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.BdiDao;
import br.com.braza.sistema.stockadvisor.dominio.Bdi;


public class BdiDaoImpl implements BdiDao {

	private EntityManager em;

	public BdiDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Bdi x) {
		if (x.getCodigo() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Bdi x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Bdi buscar(int cod) {
		return em.find(Bdi.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bdi> buscarTodos() {
		String jpql = "SELECT x FROM Bdi x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bdi> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM Bdi x WHERE TRIM(x.codigoNegociacao) = TRIM(:p1) ORDER BY x.codigoNegociacao";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}