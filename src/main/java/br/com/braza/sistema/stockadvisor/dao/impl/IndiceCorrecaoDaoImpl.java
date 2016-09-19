package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.IndiceCorrecaoDao;
import br.com.braza.sistema.stockadvisor.dominio.IndiceCorrecao;


public class IndiceCorrecaoDaoImpl implements IndiceCorrecaoDao {

	private EntityManager em;

	public IndiceCorrecaoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(IndiceCorrecao x) {
		if (x.getCodigo() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(IndiceCorrecao x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public IndiceCorrecao buscar(int cod) {
		return em.find(IndiceCorrecao.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndiceCorrecao> buscarTodos() {
		String jpql = "SELECT x FROM IndiceCorrecao x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndiceCorrecao> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM IndiceCorrecao x WHERE TRIM(x.indice) = TRIM(:p1)";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}
