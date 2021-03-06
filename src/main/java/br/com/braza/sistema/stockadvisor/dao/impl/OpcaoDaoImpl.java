package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.OpcaoDao;
import br.com.braza.sistema.stockadvisor.dominio.Opcao;


public class OpcaoDaoImpl implements OpcaoDao {

	private EntityManager em;

	public OpcaoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Opcao x) {
		if (x.getCodPapel() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Opcao x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Opcao buscar(int cod) {
		return em.find(Opcao.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opcao> buscarTodos() {
		String jpql = "SELECT x FROM Opcao x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opcao> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM Opcao x WHERE TRIM(x.codigoNegociacao) = TRIM(:p1) ORDER BY x.codigoNegociacao";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}
