package br.com.alura.comex;

public class Main {

	public static void main(String[] args) {
		Pedido[] pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		mostraRelatorioComNomeDosClientesComArray(pedidos);
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
