package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;

public class PedidoDto {
	private LocalDate dataDoPedido;
	private BigDecimal desconto;
	private BigDecimal valor;
	
	private PedidoClienteDto cliente;
	
	//@JsonManagedReference
	private List<ItemDePedido> itens;

	public PedidoDto() {
	}

	public PedidoDto(Pedido pedido) {
		this.cliente = new PedidoClienteDto(pedido.getCliente());
		this.desconto = pedido.getDesconto();
		this.dataDoPedido = pedido.getData();
		this.valor = pedido.getValorTotal();
		this.itens = pedido.getItens();
	}

	public List<ItemDePedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(LocalDate dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public PedidoClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(PedidoClienteDto cliente) {
		this.cliente = cliente;
	}

	public static List<PedidoDto> converter(List<Pedido> lista) {
		return lista.stream().map(PedidoDto::new).collect(Collectors.toList());
	}
}