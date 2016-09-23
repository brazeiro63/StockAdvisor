package br.com.braza.sistema.stockadvisor.carga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ordenacao {

	private static final String INPUT_FILENAME = "C:\\Users\\braze\\workspace\\sistema.stockadvisor\\resources\\COTAHIST_A2016.TXT";

	public ArrayList<String> ordenar() {

		FileInputStream fileIn = null;
		BufferedReader fileReader = null;
		String linha;
		List<String> linhas = null;

		try {
			fileIn = new FileInputStream(INPUT_FILENAME);
			fileReader = new BufferedReader(new InputStreamReader(fileIn));

			linhas = new ArrayList<>();

			while (fileReader.ready()) {
				linha = fileReader.readLine();
				if (linha == null) {
					break;
				}

				if (linha.length() != 0 && linha.substring(0, 2).equals("01")) {
					linhas.add(linha);
				}
			}

			Collections.sort(linhas, new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.substring(12, 24).compareTo(s2.substring(12, 24));
				}});

			fileReader.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado." + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro ao acessar o arquivo." + e.getMessage());
		}

		return (ArrayList<String>) linhas;

	}

}
