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
		

		Ordenacao ord = new Ordenacao();
		ArrayList<String> arquivoOrdenado = ord.ordenar();
		Integer registros = arquivoOrdenado.size();

		ArrayList<DescritorDeLeiaute> descriptors = new LeitorDescritorDeLeiaute().readLayout();

		Papel rPapel = null;
		RegistroCotacao registroCotacao;

		int qtdReg = 0;

		for (String registro : arquivoOrdenado) {
			
			String papelAux = new String();
			registroCotacao = LeRegistro(registro, descriptors, RegistroCotacao.class);

			if (!registroCotacao.getCodneg().equals(papelAux)) {
				rPapel = carregarPapel(correcaoServico, bdiServico, mercadoServico, papelServico, registroCotacao);
			}
			carregarCotacao(rPapel, registroCotacao);
			papelAux = registroCotacao.getCodneg();
			qtdReg++;
		}

		System.out.println("Registros recebidos: " + registros + "\n");
		System.out.println("Registros carregados: " + qtdReg + "\n");
		System.out.println("o programa executou em " + (System.currentTimeMillis() - tempoInicial));
	}

	private static void carregarCotacao(Papel papel, RegistroCotacao registroCotacao) {
		Cotacao cotacao = new Cotacao();
		List<Cotacao> cotacoes = papel.getCotacoes();

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
		cotacao.setPapel(papel);

		cotacoes.add(cotacao);
		papel.setCotacoes(cotacoes);

		CotacaoServico cs = new CotacaoServico();
		try {
			cs.Inserir(cotacao);
		} catch (ServicoException e) {
			System.out.println("Erro ao inserir nova cotação: \n" + cotacao.toString() + "\n" + e.getMessage());
		}

		PapelServico ps = new PapelServico();
		try {
			ps.atualizar(papel);
		} catch (ServicoException e) {
			System.out.println("Erro ao atualizar papel com nova cotação: \n" + papel.toString() + "\n" + e.getMessage());
		}
	}

	private static void carregarTabelaBdi() {
		BdiServico bdiServico = new BdiServico();
		bdiServico.buscarTodos();

		List<Bdi> bdis = new ArrayList<>();

		bdis.add(new Bdi(02, "LOTE PADRÃO"));
		bdis.add(new Bdi(06, "CONCORDATÁRIAS"));
		bdis.add(new Bdi(10, "DIREITOS E RECIBOS"));
		bdis.add(new Bdi(12, "FUNDOS IMOBILIÁRIOS"));
		bdis.add(new Bdi(14, "CERTIFIC. INVESTIMENTO / DEBÊNTURES / TÍTULOS DIVIDA PÚBLICA"));
		bdis.add(new Bdi(18, "OBRIGAÇÕES"));
		bdis.add(new Bdi(22, "BÔNUS (PRIVADOS)"));
		bdis.add(new Bdi(26, "APÓLICES / BÔNUS / TÍTULOS PÚBLICOS"));
		bdis.add(new Bdi(32, "EXERCÍCIO DE OPÇÕES DE COMPRA DE ÍNDICE"));
		bdis.add(new Bdi(33, "EXERCÍCIO DE OPÇÕES DE VENDA DE ÍNDICE"));
		bdis.add(new Bdi(38, "EXERCÍCIO DE OPÇÕES DE COMPRA"));
		bdis.add(new Bdi(42, "EXERCÍCIO DE OPÇÕES DE VENDA"));
		bdis.add(new Bdi(46, "LEILÃO DE TÍTULOS NÃO COTADOS"));
		bdis.add(new Bdi(48, "LEILÃO DE PRIVATIZAÇÃO"));
		bdis.add(new Bdi(50, "LEILÃO"));
		bdis.add(new Bdi(51, "LEILÃO FINOR"));
		bdis.add(new Bdi(52, "LEILÃO FINAM"));
		bdis.add(new Bdi(53, "LEILÃO FISET"));
		bdis.add(new Bdi(54, "LEILÃO DE AÇÕES EM MORA"));
		bdis.add(new Bdi(56, "VENDAS POR ALVARÁ JUDICIAL"));
		bdis.add(new Bdi(58, "OUTROS"));
		bdis.add(new Bdi(60, "PERMUTA POR AÇÕES"));
		bdis.add(new Bdi(61, "META"));
		bdis.add(new Bdi(62, "TERMO"));
		bdis.add(new Bdi(66, "DEBÊNTURES COM DATA DE VENCIMENTO, ATÉ 3 ANOS"));
		bdis.add(new Bdi(68, "DEBÊNTURES COM DATA DE VENCIMENTO, MAIOR, QUE 3 ANOS"));
		bdis.add(new Bdi(70, "FUTURO COM MOVIMENTAÇÃO CONTÍNUA"));
		bdis.add(new Bdi(71, "FUTURO COM RETENÇÃO DE GANHO"));
		bdis.add(new Bdi(74, "OPÇÕES DE COMPRA DE ÍNDICES"));
		bdis.add(new Bdi(75, "OPÇÕES DE VENDA DE ÍNDICES"));
		bdis.add(new Bdi(78, "OPÇÕES DE COMPRA"));
		bdis.add(new Bdi(82, "OPÇÕES DE VENDA"));
		bdis.add(new Bdi(83, "DEBÊNTURES E NOTAS PROMISSÓRIAS"));
		bdis.add(new Bdi(96, "FRACIONÁRIO"));
		bdis.add(new Bdi(99, "TOTAL GERAL"));

		try {
			for (Bdi bdi : bdis) {
				bdiServico.Inserir(bdi);
			}
		} catch (ServicoException e1) {
			System.out.println("Erro ao carregar tabela BDI.\nRegistros inseridos: " + bdis.size() + "\n" + e1.getMessage());
		}
	}

	private static void carregarTabelaMercado() {
		MercadoServico mercadoServico = new MercadoServico();
		List<Mercado> mercados = new ArrayList<>();

		mercados.add(new Mercado(010, "VISTA"));
		mercados.add(new Mercado(012, "EXERCÍCIO DE OPÇÕES DE COMPRA"));
		mercados.add(new Mercado(013, "EXERCÍCIO DE OPÇÕES DE VENDA"));
		mercados.add(new Mercado(017, "LEILÃO"));
		mercados.add(new Mercado(020, "FRACIONÁRIO"));
		mercados.add(new Mercado(030, "TERMO"));
		mercados.add(new Mercado(050, "FUTURO COM RETENÇÃO DE GANHO"));
		mercados.add(new Mercado(060, "FUTURO COM MOVIMENTAÇÃO CONTÍNUA"));
		mercados.add(new Mercado(070, "OPÇÕES DE COMPRA"));
		mercados.add(new Mercado(80, "OPÇÕES DE VENDA"));
		try {
			for (Mercado mercado : mercados) {
				mercadoServico.Inserir(mercado);
			}
		} catch (ServicoException e1) {
			System.out.println("Erro ao carregar tabela Mercado.\nRegistros inseridos: " + mercados.size() + "\n" + e1.getMessage());
		}
	}

	private static void carregarTabelaIndiceCorrecao() {
		IndiceCorrecaoServico correcaoServico = new IndiceCorrecaoServico();
		List<IndiceCorrecao> ics = new ArrayList<>();

		ics.add(new IndiceCorrecao(1, "US$", "CORREÇÃO PELA TAXA DO DÓLAR"));
		ics.add(new IndiceCorrecao(2, "TJLP", "CORREÇÃO PELA TJLP"));
		ics.add(new IndiceCorrecao(3, "TR", "CORREÇÃO PELA TR"));
		ics.add(new IndiceCorrecao(4, "IPCR", "CORREÇÃO PELO IPCR"));
		ics.add(new IndiceCorrecao(5, "SWA", "OPÇÕES DE TROCA, - SWOPTIONS"));
		ics.add(new IndiceCorrecao(6, "ÍNDICES (PONTOS)", "OPÇÕES  REFERENCIADAS EM PONTOS DE ÍNDICE"));
		ics.add(new IndiceCorrecao(7, "US$ (PROTEGIDAS)", "CORREÇÃO PELA TAXA DO DÓLAR - OPÇÕES PROTEGIDAS"));
		ics.add(new IndiceCorrecao(8, "IGPM (PROTEGIDA)", "CORREÇÃO PELO IGP-M - OPÇÕES PROTEGIDAS"));
		ics.add(new IndiceCorrecao(9, "URV", "CORREÇÃO PELA URV"));
		try {
			for (IndiceCorrecao indiceCorrecao : ics) {
				correcaoServico.Inserir(indiceCorrecao);				
			}
		} catch (ServicoException e1) {
			System.out.println("Erro ao carregar tabela Indice de correcao. \nRegistros inseridos: " + ics.size() + "\n" + e1.getMessage());
		}
	}

	private static Papel carregarPapel(IndiceCorrecaoServico correcaoServico, BdiServico bdiServico,
			MercadoServico mercadoServico, PapelServico papelServico, RegistroCotacao registroCotacao)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NumberFormatException {

		Integer codigoPapel = null;
		Papel sPapel, papelInserido = null;

		sPapel = papelServico.buscarPorNome(registroCotacao.getCodneg());

		if (sPapel == null) {
			sPapel = new Papel();
		} else {
			codigoPapel = sPapel.getCodPapel();
		}

		if (registroCotacao.getPreexe() > Double.parseDouble("0.0")) {
			Opcao papel = new Opcao();
			papel.setPrecoExercicio(registroCotacao.getPreexe());
			papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
			papel.setDataVencimento(registroCotacao.getDatven());

			IndiceCorrecao indCorr = correcaoServico.buscar(registroCotacao.getIndopc().intValue());
			papel.setIndiceCorrecao(indCorr);
			sPapel = papel;
		} else if (registroCotacao.getCodbdi().equals("06") || registroCotacao.getCodbdi().equals("12")
				|| registroCotacao.getCodbdi().equals("96")) {
			Acao papel = new Acao();
			sPapel = papel;
		} else {
			Termo papel = new Termo();
			papel.setPrazo(registroCotacao.getPrazot());
			papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
			papel.setDataVencimento(registroCotacao.getDatven());

			IndiceCorrecao indCorr = correcaoServico.buscar(registroCotacao.getIndopc().intValue());
			papel.setIndiceCorrecao(indCorr);
			sPapel = papel;
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

		try {
			papelServico.Inserir(sPapel);
			papelInserido = papelServico.buscarPorNome(sPapel.getCodigoNegociacao());
		} catch (ServicoException e) {
			System.out.println("Erro ao inserir papel: " + sPapel.toString() + "\n" + e.getMessage());
		}

		return papelInserido;
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
