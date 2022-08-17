package br.com.alura.comex;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ProcessadorDeCsv {

	public static List<Pedido> processaArquivo(String nomeDoArquivo) {
		try {
			URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
			Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

			Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

			leitorDeLinhas.nextLine();

			// Pedido[] pedidos = new Pedido[10];
			List<Pedido> pedidos = new ArrayList<>();

			int quantidadeDeRegistros = 0;
			while (leitorDeLinhas.hasNextLine()) {
				String linha = leitorDeLinhas.nextLine();
				String[] registro = linha.split(",");

				String categoria = registro[0];
				String produto = registro[1];
				BigDecimal preco = new BigDecimal(registro[2]);
				int quantidade = Integer.parseInt(registro[3]);
				LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String cliente = registro[5];

				Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
				pedidos.add(pedido);
				/*
				 * pedidos[quantidadeDeRegistros] = pedido;
				 * 
				 * quantidadeDeRegistros++; if (pedidos[pedidos.length - 1] != null) { pedidos =
				 * Arrays.copyOf(pedidos, pedidos.length * 2); }
				 */
			}

			return pedidos;
		} catch (URISyntaxException e) {
			throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
		}
	}
	
	public static List<Pedido> processaArquivoOpenCSV(String nomeDoArquivo) {
		List<Pedido> pedidos = new ArrayList<>();
		try {
			URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
			Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

			try (Reader reader = Files.newBufferedReader(caminhoDoArquivo)) {
				try (CSVReader csvReader = new CSVReader(reader)) {
					List<String[]> readAll = csvReader.readAll();

					for (int i = 1; i < readAll.size(); i++) {
						String[] colunas = readAll.get(i);

						String categoria = colunas[0];
						String produto = colunas[1];
						BigDecimal preco = new BigDecimal(colunas[2]);
						int quantidade = Integer.parseInt(colunas[3]);
						LocalDate data = LocalDate.parse(colunas[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						String cliente = colunas[5];

						Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
						pedidos.add(pedido);
					}
				}
			}
		} catch (CsvException e) {
			throw new RuntimeException("Erro ao abrir a lista de array de String!");
		} catch (URISyntaxException e) {
			throw new RuntimeException(String.format("Arquivo {} n�o localizado!", nomeDoArquivo));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao abrir BufferedReader para processar arquivo!");
		}
		return pedidos;
	}
	
}