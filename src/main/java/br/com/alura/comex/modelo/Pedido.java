package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	
	private BigDecimal desconto = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private TipoDeDesconto tipoDeDesconto = TipoDeDesconto.NENHUM;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ItemDePedido> itens = new ArrayList<>();

	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public Long getId() {
		return id;
	}

	public List<ItemDePedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}
	
	public void addItemDePedido(ItemDePedido item) {
		Integer quantidadeEmEstoqueAtualizada = item.getProduto().getQuantidade() - item.getQuantidade();
		item.getProduto().setQuantidade(quantidadeEmEstoqueAtualizada);
		
		item.setPedido(this); // pedidoFulano
		itens.add(item); // [item1, item2]
		
		this.valorTotal = this.valorTotal.add(item.getValor());
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotal() {
		if(this.tipoDeDesconto.equals(TipoDeDesconto.FIDELIDADE)) {
			BigDecimal valorDesconto = this.valorTotal.multiply(desconto);
			this.valorTotal = this.valorTotal.subtract(valorDesconto);
		}
		return valorTotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDeDesconto getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(TipoDeDesconto tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}
	
	@Override
	public String toString() {
		return "Pedido - [Nome do Cliente: " + this.cliente.getNome() + "| Itens: " + this.itens + "]";
	}
}