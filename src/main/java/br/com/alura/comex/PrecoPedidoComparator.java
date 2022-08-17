package br.com.alura.comex;

import java.util.Comparator;

public class PrecoPedidoComparator implements Comparator<Pedido>{

	@Override
	public int compare(Pedido p1, Pedido p2) {
		return p1.getPreco().compareTo(p2.getPreco());
	}
}