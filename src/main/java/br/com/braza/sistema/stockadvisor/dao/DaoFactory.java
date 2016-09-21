package br.com.braza.sistema.stockadvisor.dao;

import br.com.braza.sistema.stockadvisor.dao.impl.AcaoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.BdiDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.CotacaoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.IndiceCorrecaoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.MercadoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.OpcaoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.PapelDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.TermoDaoImpl;

public class DaoFactory {

	public static AcaoDao criarAcaoDao() {
		return new AcaoDaoImpl();
	}

	public static OpcaoDao criarOpcaoDao() {
		return new OpcaoDaoImpl();
	}

	public static TermoDao criarTermoDao() {
		return new TermoDaoImpl();
	}

	public static PapelDao criarPapelDao() {
		return new PapelDaoImpl();
	}

	public static BdiDao criarBdiDao() {
		return new BdiDaoImpl();
	}

	public static IndiceCorrecaoDao criarIndiceCorrecaoDao() {
		return new IndiceCorrecaoDaoImpl();
	}

	public static MercadoDao criarMercadoDao() {
		return new MercadoDaoImpl();
	}

	public static CotacaoDao criarCotacaoDao() {
		return new CotacaoDaoImpl();
	}

}
