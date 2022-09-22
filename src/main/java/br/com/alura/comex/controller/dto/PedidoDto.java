package br.com.alura.comex.controller.dto;

import java.util.List;

import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;

public class PedidoDto {
	private Long idCliente;
	private List<ItemDePedido> itensPedido;

	public PedidoDto() {}
	
	public PedidoDto(Pedido pedido) {
		this.idCliente = pedido.getCliente().getId();
		this.itensPedido = pedido.getItens();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemDePedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemDePedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
}