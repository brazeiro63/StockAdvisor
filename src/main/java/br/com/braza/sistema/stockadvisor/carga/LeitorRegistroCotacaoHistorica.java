package br.com.braza.sistema.stockadvisor.carga;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.braza.sistema.stockadvisor.dominio.Acao;
import br.com.braza.sistema.stockadvisor.dominio.Bdi;
import br.com.braza.sistema.stockadvisor.dominio.Cotacao;
import br.com.braza.sistema.stockadvisor.dominio.IndiceCorrecao;
import br.com.braza.sistema.stockadvisor.dominio.Mercado;
import br.com.braza.sistema.stockadvisor.dominio.Opcao;
import br.com.braza.sistema.stockadvisor.dominio.Papel;
import br.com.braza.sistema.stockadvisor.dominio.RegistroCotacao;
import br.com.braza.sistema.stockadvisor.dominio.Termo;
import br.com.braza.sistema.stockadvisor.servico.BdiServico;
import br.com.braza.sistema.stockadvisor.servico.CotacaoServico;
import br.com.braza.sistema.stockadvisor.servico.IndiceCorrecaoServico;
import br.com.braza.sistema.stockadvisor.servico.MercadoServico;
import br.com.braza.sistema.stockadvisor.servico.PapelServico;
import br.com.braza.sistema.stockadvisor.servico.ServicoException;

public class LeitorRegistroCotacaoHistorica {

	public static void main(String[] args) throws IOException, SecurityException, IllegalAccessException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

		long tempoInicial = System.currentTimeMillis();

		carregarTabelaBdi();
		carregarTabelaMercado();
		carregarTabelaIndiceCorrecao();

		IndiceCorrecaoServico correcaoServico = new IndiceCorrecaoServico();
		MercadoServico mercadoServico = new MercadoServico();
		BdiServico bdiServico = new BdiServico();
		PapelServico papelServico = new PapelServico();
		CotacaoServico cotacaoServico = new CotacaoServico();

		Ordenacao ord = new Ordenacao();
		ArrayList<String> arquivoOrdenado = ord.ordenar();
		Integer registros = arquivoOrdenado.size();

		ArrayList<DescritorDeLeiaute> descriptors = new LeitorDescritorDeLeiaute().readLayout();

		Papel rPapel = null;
		RegistroCotacao registroCotacao;
		String papelAux = new String();

		int qtdReg = 0;

		for (String registro : arquivoOrdenado) {

			registroCotacao = LeRegistro(registro, descriptors, RegistroCotacao.class);

			if (!registroCotacao.getCodneg().substring(4, 5).matches("[A-X]")
					&& !registroCotacao.getCodneg().trim().substring(5).contains("T")
					&& registroCotacao.getCodneg().substring(4, 5).matches("[1-9]")) {

				rPapel = extrairPapel(correcaoServico, bdiServico, mercadoServico, papelServico, registroCotacao);

				if (!registroCotacao.getCodneg().equals(papelAux)) {
					try {
						papelServico.atualizar(rPapel);
					} catch (ServicoException e) {
						System.out.println("Erro ao atualizar papel com nova cota��o: \n" + rPapel.toString() + "\n"
								+ e.getMessage());
					}
				}

				Cotacao extrairCotacao = extrairCotacao(registroCotacao);
				extrairCotacao.setPapel(rPapel);
				try {
					cotacaoServico.Inserir(extrairCotacao);
				} catch (ServicoException e) {
					System.out
							.println("Erro ao inserir cotacao: \n" + extrairCotacao.toString() + "\n" + e.getMessage());
				}

				// rPapel.addCotacao(extrairCotacao);
				papelAux = registroCotacao.getCodneg();
				qtdReg++;
			}
		}

		System.out.println("Registros recebidos: " + registros + "\n");
		System.out.println("Registros carregados: " + qtdReg + "\n");
		System.out.println("o programa executou em " + (System.currentTimeMillis() - tempoInicial));
	}

	private static Cotacao extrairCotacao(RegistroCotacao registroCotacao) {
		Cotacao cotacao = new Cotacao();

		cotacao.setDataCotacao(registroCotacao.getDatpre());
		cotacao.setMoedaReferencia(registroCotacao.getModref());
		cotacao.setPrecoAbertura(registroCotacao.getPreabe());
		cotacao.setPrecoMaximo(registroCotacao.getPremax());
		cotacao.setPrecoMedio(registroCotacao.getPremed());
		cotacao.setPrecoMinimo(registroCotacao.getPremin());
		cotacao.setPrecoOfertaCompra(registroCotacao.getPreofc());
		cotacao.setPrecoOfertaVenda(registroCotacao.getPreofv());
		cotacao.setPrecoUltimo(registroCotacao.getPreult());
		cotacao.setQuantidadeNegocios(registroCotacao.getQuatot().intValue());
		cotacao.setQuantidadePapeisNegociados(registroCotacao.getTotneg());
		cotacao.setVolumeNegocios(registroCotacao.getVoltot());

		return cotacao;
	}

	private static void carregarTabelaBdi() {
		BdiServico bdiServico = new BdiServico();
		bdiServico.buscarTodos();

		List<Bdi> bdis = new ArrayList<>();

		bdis.add(new Bdi(02, "LOTE PADR�O"));
		bdis.add(new Bdi(06, "CONCORDAT�RIAS"));
		bdis.add(new Bdi(10, "DIREITOS E RECIBOS"));
		bdis.add(new Bdi(12, "FUNDOS IMOBILI�RIOS"));
		bdis.add(new Bdi(14, "CERTIFIC. INVESTIMENTO / DEB�NTURES / T�TULOS DIVIDA P�BLICA"));
		bdis.add(new Bdi(18, "OBRIGA��ES"));
		bdis.add(new Bdi(22, "B�NUS (PRIVADOS)"));
		bdis.add(new Bdi(26, "AP�LICES / B�NUS / T�TULOS P�BLICOS"));
		bdis.add(new Bdi(32, "EXERC�CIO DE OP��ES DE COMPRA DE �NDICE"));
		bdis.add(new Bdi(33, "EXERC�CIO DE OP��ES DE VENDA DE �NDICE"));
		bdis.add(new Bdi(38, "EXERC�CIO DE OP��ES DE COMPRA"));
		bdis.add(new Bdi(42, "EXERC�CIO DE OP��ES DE VENDA"));
		bdis.add(new Bdi(46, "LEIL�O DE T�TULOS N�O COTADOS"));
		bdis.add(new Bdi(48, "LEIL�O DE PRIVATIZA��O"));
		bdis.add(new Bdi(50, "LEIL�O"));
		bdis.add(new Bdi(51, "LEIL�O FINOR"));
		bdis.add(new Bdi(52, "LEIL�O FINAM"));
		bdis.add(new Bdi(53, "LEIL�O FISET"));
		bdis.add(new Bdi(54, "LEIL�O DE A��ES EM MORA"));
		bdis.add(new Bdi(56, "VENDAS POR ALVAR� JUDICIAL"));
		bdis.add(new Bdi(58, "OUTROS"));
		bdis.add(new Bdi(60, "PERMUTA POR A��ES"));
		bdis.add(new Bdi(61, "META"));
		bdis.add(new Bdi(62, "TERMO"));
		bdis.add(new Bdi(66, "DEB�NTURES COM DATA DE VENCIMENTO, AT� 3 ANOS"));
		bdis.add(new Bdi(68, "DEB�NTURES COM DATA DE VENCIMENTO, MAIOR, QUE 3 ANOS"));
		bdis.add(new Bdi(70, "FUTURO COM MOVIMENTA��O CONT�NUA"));
		bdis.add(new Bdi(71, "FUTURO COM RETEN��O DE GANHO"));
		bdis.add(new Bdi(74, "OP��ES DE COMPRA DE �NDICES"));
		bdis.add(new Bdi(75, "OP��ES DE VENDA DE �NDICES"));
		bdis.add(new Bdi(78, "OP��ES DE COMPRA"));
		bdis.add(new Bdi(82, "OP��ES DE VENDA"));
		bdis.add(new Bdi(83, "DEB�NTURES E NOTAS PROMISS�RIAS"));
		bdis.add(new Bdi(96, "FRACION�RIO"));
		bdis.add(new Bdi(99, "TOTAL GERAL"));

		try {
			for (Bdi bdi : bdis) {
				bdiServico.Inserir(bdi);
			}
		} catch (ServicoException e1) {
			System.out.println(
					"Erro ao carregar tabela BDI.\nRegistros inseridos: " + bdis.size() + "\n" + e1.getMessage());
		}
	}

	private static void carregarTabelaMercado() {
		MercadoServico mercadoServico = new MercadoServico();
		List<Mercado> mercados = new ArrayList<>();

		mercados.add(new Mercado(010, "VISTA"));
		mercados.add(new Mercado(012, "EXERC�CIO DE OP��ES DE COMPRA"));
		mercados.add(new Mercado(013, "EXERC�CIO DE OP��ES DE VENDA"));
		mercados.add(new Mercado(017, "LEIL�O"));
		mercados.add(new Mercado(020, "FRACION�RIO"));
		mercados.add(new Mercado(030, "TERMO"));
		mercados.add(new Mercado(050, "FUTURO COM RETEN��O DE GANHO"));
		mercados.add(new Mercado(060, "FUTURO COM MOVIMENTA��O CONT�NUA"));
		mercados.add(new Mercado(070, "OP��ES DE COMPRA"));
		mercados.add(new Mercado(80, "OP��ES DE VENDA"));
		try {
			for (Mercado mercado : mercados) {
				mercadoServico.Inserir(mercado);
			}
		} catch (ServicoException e1) {
			System.out.println("Erro ao carregar tabela Mercado.\nRegistros inseridos: " + mercados.size() + "\n"
					+ e1.getMessage());
		}
	}

	private static void carregarTabelaIndiceCorrecao() {
		IndiceCorrecaoServico correcaoServico = new IndiceCorrecaoServico();
		List<IndiceCorrecao> ics = new ArrayList<>();

		ics.add(new IndiceCorrecao(1, "US$", "CORRE��O PELA TAXA DO D�LAR"));
		ics.add(new IndiceCorrecao(2, "TJLP", "CORRE��O PELA TJLP"));
		ics.add(new IndiceCorrecao(3, "TR", "CORRE��O PELA TR"));
		ics.add(new IndiceCorrecao(4, "IPCR", "CORRE��O PELO IPCR"));
		ics.add(new IndiceCorrecao(5, "SWA", "OP��ES DE TROCA, - SWOPTIONS"));
		ics.add(new IndiceCorrecao(6, "�NDICES (PONTOS)", "OP��ES  REFERENCIADAS EM PONTOS DE �NDICE"));
		ics.add(new IndiceCorrecao(7, "US$ (PROTEGIDAS)", "CORRE��O PELA TAXA DO D�LAR - OP��ES PROTEGIDAS"));
		ics.add(new IndiceCorrecao(8, "IGPM (PROTEGIDA)", "CORRE��O PELO IGP-M - OP��ES PROTEGIDAS"));
		ics.add(new IndiceCorrecao(9, "URV", "CORRE��O PELA URV"));
		try {
			for (IndiceCorrecao indiceCorrecao : ics) {
				correcaoServico.Inserir(indiceCorrecao);
			}
		} catch (ServicoException e1) {
			System.out.println("Erro ao carregar tabela Indice de correcao. \nRegistros inseridos: " + ics.size() + "\n"
					+ e1.getMessage());
		}
	}

	private static Papel extrairPapel(IndiceCorrecaoServico correcaoServico, BdiServico bdiServico,
			MercadoServico mercadoServico, PapelServico papelServico, RegistroCotacao registroCotacao)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NumberFormatException {

		Integer codigoPapel = null;
		Papel papelAux = new Papel();
		Papel sPapel = new Papel();

		papelAux = papelServico.buscarPorNome(registroCotacao.getCodneg());

		if (papelAux != null) {
			codigoPapel = papelAux.getCodPapel();
		}

		if (registroCotacao.getCodneg().substring(4, 5).matches("[A-X]")) {
			Opcao papel = new Opcao();
			papel.setPrecoExercicio(registroCotacao.getPreexe());
			papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
			papel.setDataVencimento(registroCotacao.getDatven());

			IndiceCorrecao indCorr = correcaoServico.buscar(registroCotacao.getIndopc().intValue());
			papel.setIndiceCorrecao(indCorr);

			sPapel = (Papel) papel;

		} else if (registroCotacao.getCodneg().trim().substring(5).contains("T")) {
			Termo papel = new Termo();

			papel.setPrazo(registroCotacao.getPrazot());
			papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
			papel.setDataVencimento(registroCotacao.getDatven());

			IndiceCorrecao indCorr = correcaoServico.buscar(registroCotacao.getIndopc().intValue());
			papel.setIndiceCorrecao(indCorr);

			sPapel = (Papel) papel;

		} else if (registroCotacao.getCodneg().substring(4, 5).matches("[1-9]")) {
			Acao papel = new Acao();

			sPapel = (Papel) papel;

		} else {
			System.out.println("Papel n�o classificado: \n" + registroCotacao.getCodneg().toString()
					+ "registroCotacao.getCodneg().substring(4, 5)" + registroCotacao.getCodneg().substring(4, 5)
					+ "registroCotacao.getCodneg().trim().substring(registroCotacao.getCodneg().trim().length() - 1)"
					+ registroCotacao.getCodneg().trim().substring(registroCotacao.getCodneg().trim().length() - 1));
		}

		sPapel.setFatorCotacao(registroCotacao.getFatcot().intValue());
		sPapel.setEspecificacao(registroCotacao.getEspeci());
		sPapel.setCodigoIsin(registroCotacao.getCodisi());
		sPapel.setCodigoNegociacao(registroCotacao.getCodneg());
		sPapel.setEmpresa(registroCotacao.getNomres());

		Mercado merc = mercadoServico.buscar(registroCotacao.getTpmerc().intValue());
		sPapel.setMercado(merc);

		Bdi bdi = bdiServico.buscar(Integer.parseInt(registroCotacao.getCodbdi()));
		sPapel.setBdi(bdi);

		if (codigoPapel != null) {
			sPapel.setCodPapel(codigoPapel);
		}

		return sPapel;
	}

	private static RegistroCotacao LeRegistro(String linha, ArrayList<DescritorDeLeiaute> descriptors,
			Class<RegistroCotacao> cotacaoClass)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		RegistroCotacao cotacao = new RegistroCotacao();

		for (DescritorDeLeiaute descriptor : descriptors) {

			String s = new String(descriptor.getName().toLowerCase());
			String methodName = new String("set" + s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
			String dataType = new String(descriptor.type);
			String parameter = new String();
			String sParameter = new String();
			Double dParameter = new Double(0);
			Long lParameter = new Long(0);
			parameter = new String(linha.substring(descriptor.getStartPosition() - 1, descriptor.getEndPosition()));

			switch (dataType.substring(0, 1)) {
			case "X":
				Method xMethod = cotacaoClass.getMethod(methodName, new String().getClass());
				sParameter = parameter;
				xMethod.invoke(cotacao, sParameter);

				break;

			case "N":
				double precision = 0;
				if (dataType.contains("V")) {
					int ind = dataType.indexOf("V");
					if (dataType.substring(ind + 1).equals("99")) {
						precision = 2;
					} else {
						precision = Integer.parseInt(parameter.substring(ind));
					}
					dParameter = Double.parseDouble(parameter);
					dParameter = dParameter / Math.pow(10, precision);
					Method dMethod = cotacaoClass.getMethod(methodName, new Double(0).getClass());
					dMethod.invoke(cotacao, dParameter);
				} else {
					lParameter = Long.parseLong(parameter);
					Method lMethod = cotacaoClass.getMethod(methodName, new Long(0).getClass());
					lMethod.invoke(cotacao, lParameter);
				}

				break;

			default:
				break;
			}

		}
		return cotacao;
	}

}
