package br.com.alura.comex.controller.form;

import java.time.LocalDate;
import java.util.List;

import br.com.alura.comex.modelo.Produto;

public class PedidoForm {
	private Long idCliente;
	private List<Produto> itensPedido;
	private LocalDate dataPedido = LocalDate.now();

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Produto> getItensPedido() {
		return itensPedido;
	}

	public void setItens(List<Produto> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
}