package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
	public List<String> mostraListaComSomenteNomeDosClientes() {
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivoOpenCSV("pedidos.csv");
		List<String> nomeDosClientes = new ArrayList<>();
		
		for (Pedido pedido : pedidos) {
			nomeDosClientes.add(pedido.getCliente());
		}
		
		return nomeDosClientes;
	}
}