package br.com.braza.sistema.stockadvisor.carga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import br.com.braza.sistema.stockadvisor.dao.impl.BdiDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.IndiceCorrecaoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.MercadoDaoImpl;
import br.com.braza.sistema.stockadvisor.dao.impl.PapelDaoImpl;
import br.com.braza.sistema.stockadvisor.dominio.Acao;
import br.com.braza.sistema.stockadvisor.dominio.Opcao;
import br.com.braza.sistema.stockadvisor.dominio.Papel;
import br.com.braza.sistema.stockadvisor.dominio.RegistroCotacao;
import br.com.braza.sistema.stockadvisor.dominio.Termo;

public class LeitorRegistroCotacaoHistorica {

	public static void main(String[] args) throws IOException, SecurityException, IllegalAccessException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		
		long tempoInicial = System.currentTimeMillis();
		
		IndiceCorrecaoDaoImpl icdi = new IndiceCorrecaoDaoImpl();
		BdiDaoImpl bdii = new BdiDaoImpl();
		MercadoDaoImpl mdi = new MercadoDaoImpl();
		PapelDaoImpl papelDao = new PapelDaoImpl();

		ArrayList<DescritorDeLeiaute> descriptors = new LeitorDescritorDeLeiaute().readLayout();
		Class<RegistroCotacao> cotacaoClass = RegistroCotacao.class;

//		br.com.braza.sistema.stockadvisor.dao.Transaction.begin();
		try {
			String linha = null;
			String fileHystory = "C:\\Users\\braze\\workspace\\sistema.stockadvisor\\resources\\COTAHIST_A2016.TXT";
			FileInputStream fileIn = new FileInputStream(fileHystory);
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileIn));
			RegistroCotacao registroCotacao;
			Papel sPapel = new Papel();

			int qtdReg = 0;
			while (fileReader.ready()) {
				linha = fileReader.readLine();

				if (linha != null) {

				switch (linha.substring(0, 2)) {
				case "00":
					// LeHeader(linha);
					break;

				case "01":
					registroCotacao = LeRegistro(linha, descriptors, cotacaoClass);
					
					sPapel = papelDao.buscarPorNome(registroCotacao.getCodneg());
					Integer codigoPapel = null;
					if (sPapel == null){
						sPapel = new Papel();
					} else{
						codigoPapel = sPapel.getCodPapel();
					}
					
					if (registroCotacao.getPreexe() > Double.parseDouble("0.0")){
						Opcao papel = new Opcao();
						papel.setPrecoExercicio(registroCotacao.getPreexe());
						papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
						papel.setDataVencimento(registroCotacao.getDatven());
						papel.setIndiceCorrecao(icdi.buscar(registroCotacao.getIndopc().intValue()));
						sPapel = papel;
					} else if (!registroCotacao.getPrazot().equals(0)) {
						Termo papel = new Termo();
						papel.setPrazo(registroCotacao.getPrazot());
						papel.setPrecoExercicioEmPontos(registroCotacao.getPtoexe());
						papel.setDataVencimento(registroCotacao.getDatven());
						papel.setIndiceCorrecao(icdi.buscar(registroCotacao.getIndopc().intValue()));
						sPapel = papel;
					} else {
						Acao papel = new Acao();
						papel.setFatorCotacao(registroCotacao.getFatcot().intValue());
						sPapel = papel;
					}
					
					sPapel.setEspecificacao(registroCotacao.getEspeci());
					sPapel.setCodigoIsin(registroCotacao.getCodisi());
					sPapel.setCodigoNegociacao(registroCotacao.getCodneg());
					sPapel.setEmpresa(registroCotacao.getNomres());

					sPapel.setBdi(bdii.buscar(Integer.parseInt(registroCotacao.getCodbdi())));
					sPapel.setMercado(mdi.buscar(Integer.parseInt(registroCotacao.getCodbdi())));

					if (codigoPapel != null){
						sPapel.setCodPapel(codigoPapel);
					}
					
					papelDao.inserirAtualizar(sPapel);
					
					break;

				case "99":
					// LeTrailler(linha);
					break;

				default:
					break;
				}

				qtdReg++;
				}
			}

			fileReader.close();

			System.out.println("Registros lidos: " + qtdReg);

			System.out.println("o programa executou em " + (System.currentTimeMillis() - tempoInicial));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		br.com.braza.sistema.stockadvisor.dao.Transaction.commit();
	}

	private static RegistroCotacao LeRegistro(String linha, ArrayList<DescritorDeLeiaute>  descriptors, Class<RegistroCotacao> cotacaoClass)
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
