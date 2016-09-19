package br.com.braza.sistema.stockadvisor.dao;

import br.com.braza.sistema.stockadvisor.dao.impl.EM;

public class Transaction {

	public static void begin () {
		EM.getLocalEm().getTransaction().begin();
	}
	
	public static void commit() {
		EM.getLocalEm().getTransaction().commit();
	}
	
	public static void rollback() {
		EM.getLocalEm().getTransaction().rollback();
	}
	
	public static boolean isActive() {
		return EM.getLocalEm().getTransaction().isActive();
	}
}
