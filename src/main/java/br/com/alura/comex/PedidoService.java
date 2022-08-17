package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PedidoService {
	private List<Pedido> pedidos;
	
	public PedidoService() {
		this.pedidos = ProcessadorDeCsv.processaArquivoOpenCSV("pedidos.csv");
	}
	
	public List<String> mostraListaComSomenteNomeDosClientes() {
		List<String> nomeDosClientes = new ArrayList<>();
		
		for (Pedido pedido : pedidos) {
			nomeDosClientes.add(pedido.getCliente());
		}
		
		return nomeDosClientes;
	}
	
	public Set<String> mostraListaDeCategoriasSemDuplicacoes() {
		Set<String> conjuntoCategorias  = new HashSet<>();

		for (Pedido pedido : pedidos) {
			conjuntoCategorias.add(pedido.getCategoria());
		}
		
		return conjuntoCategorias;
	}
	
}