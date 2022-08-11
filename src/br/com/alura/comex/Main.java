package br.com.alura.comex;

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
