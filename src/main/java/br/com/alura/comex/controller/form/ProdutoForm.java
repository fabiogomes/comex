package br.com.alura.comex.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;

public class ProdutoForm {
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 250)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 250)
	private String descricao;
	
	@DecimalMin(value = "1")
	private BigDecimal precoUnitario;
	
	@DecimalMin(value = "1")
	private Integer quantidadeEmEstoque;
	
	private Long categoriaId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Produto converter() {
		Categoria categoria = new Categoria();
		categoria.setId(this.categoriaId);
		
		return new Produto(this.nome, this.descricao, this.precoUnitario, this.quantidadeEmEstoque, categoria);
	}

}