package br.com.braza.sistema.stockadvisor.carga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LeitorDescritorDeLeiaute {

	private static final String INPUT_FILENAME = "C:\\Users\\braze\\workspace\\sistema.stockadvisor\\resources\\cotahistdescriptor.txt";

	public ArrayList<DescritorDeLeiaute> readLayout() {

		FileInputStream fileIn = null;
		BufferedReader fileReader = null;
		String linha = null;
		ArrayList<DescritorDeLeiaute> layout = null;

		try {
			fileIn = new FileInputStream(INPUT_FILENAME);
			fileReader = new BufferedReader(new InputStreamReader(fileIn));

			layout = new ArrayList<>();

			while (fileReader.ready()) {
				linha = fileReader.readLine();
				if (linha == null) {
					break;
				}
				linha = linha.trim();

				if (linha.length() != 0) {
					DescritorDeLeiaute descriptor = new DescritorDeLeiaute();

					descriptor.name = linha.split(",")[0].trim();
					descriptor.description = linha.split(",")[1].trim();
					descriptor.type = linha.split(",")[2].trim();
					descriptor.startPosition = Integer.parseInt(linha.split(",")[3].trim());
					descriptor.endPosition = Integer.parseInt(linha.split(",")[4].trim());

					layout.add((DescritorDeLeiaute) descriptor);
				}
			}

			fileIn.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return layout;

	}

}
