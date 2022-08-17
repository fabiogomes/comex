package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;

public class PedidoList {
	private List<Pedido> pedidos;

	public PedidoList() {
		pedidos = new ArrayList<Pedido>();
	}

	public void add(Pedido p) {
		pedidos.add(p);
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}