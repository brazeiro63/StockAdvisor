package br.com.braza.sistema.stockadvisor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.braza.sistema.stockadvisor.dao.AcaoDao;
import br.com.braza.sistema.stockadvisor.dominio.Acao;


public class AcaoDaoImpl implements AcaoDao {

	private EntityManager em;

	public AcaoDaoImpl() {
		this.em = EM.getLocalEm();
	}
	
	@Override
	public void inserirAtualizar(Acao x) {
		if (x.getCodPapel() != null) {
			x = em.merge(x);
		}
		em.persist(x);
	}
	@Override
	public void excluir(Acao x) {
		x = em.merge(x);
		em.remove(x);
	}

	@Override
	public Acao buscar(int cod) {
		return em.find(Acao.class, cod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acao> buscarTodos() {
		String jpql = "SELECT x FROM Acao x";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acao> buscarPorNome(String nome) {
		String jpql = "SELECT x FROM Acao x WHERE TRIM(x.codigoNegociacao) = TRIM(:p1) ORDER BY x.codigoNegociacao";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		return query.getResultList();
	}
	
}
