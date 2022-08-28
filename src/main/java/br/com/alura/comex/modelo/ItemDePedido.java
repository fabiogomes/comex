package br.com.alura.comex.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemDePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal precoUnitario;
	private Integer quantidade;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private PedidoComex pedido;

	private BigDecimal desconto;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeDesconto")
	private TipoDeDescontoItem tipoDeDesconto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public PedidoComex getPedido() {
		return pedido;
	}

	public void setPedido(PedidoComex pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDeDescontoItem getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(TipoDeDescontoItem tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Itens - [Preço Unitário: " + this.precoUnitario + "| Produto - {Nome: " + this.produto.getNome() + "}]";
	}
}