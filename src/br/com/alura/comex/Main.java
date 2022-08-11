package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Utilizando Array
		/*
		Pedido[] pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		mostraRelatorioComNomeDosClientesComArray(pedidos);
		*/

		// Utilizando List
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		mostraRelatorioComNomeDosClientesComList(pedidos);
		mostraNumeroDePedidos(pedidos);
		mostraPrimeiroEUltimoPedidos(pedidos);
		mostraListaComSomenteNomeDosClientes(pedidos);
	}

	private static void mostraListaComSomenteNomeDosClientes(List<Pedido> pedidos) {
		List<String> nomeDosCientes = new ArrayList<>();
		
		for (Pedido pedido : pedidos) {
			nomeDosCientes.add(pedido.getCliente());
		}

		System.out.println("*************************");
		System.out.println("Somente Nome dos Clientes");
		System.out.println("*************************");
		
		for (String cliente : nomeDosCientes) {
			System.out.println(cliente);
		}
	}

	private static void mostraPrimeiroEUltimoPedidos(List<Pedido> pedidos) {
		System.out.println("==================================");
		System.out.println("Primeiro pedido: " + pedidos.get(0));
		System.out.println("Último pedido: " + pedidos.get(pedidos.size() - 1));
		System.out.println("==================================");
	}

	private static void mostraNumeroDePedidos(List<Pedido> pedidos) {
		System.out.println("Número de pedidos: " + pedidos.size());
	}

	private static void mostraRelatorioComNomeDosClientesComList(List<Pedido> pedidos) {
		System.out.println("=================");
		System.out.println("Nome dos Clientes");
		System.out.println("Estrutura: List");
		System.out.println("=================");

		for (Pedido pedido : pedidos) {
				System.out.println(pedido.getCliente());
		}

		System.out.println("=================");
	}

	private static void mostraRelatorioComNomeDosClientesComArray(Pedido[] pedidos) {
		System.out.println("=================");
		System.out.println("Nome dos Clientes");
		System.out.println("Estrutura: Array");
		System.out.println("=================");

		for (Pedido pedido : pedidos) {
			if(pedido != null)
				System.out.println(pedido.getCliente());
		}

		System.out.println("=================");
	}
}
