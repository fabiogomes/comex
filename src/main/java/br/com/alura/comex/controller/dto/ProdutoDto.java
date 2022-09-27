package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.comex.modelo.Produto;

public class ProdutoDto {
	private String id;
	private String nome;
	private String descricao;
	private String precoUnitario;
	private String quantidadeEmEstoque;
	private String idCategoria;

	public ProdutoDto() {}
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId().toString();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.precoUnitario = produto.getPrecoUnitario().toString();
		this.quantidadeEmEstoque = produto.getQuantidade().toString();
		this.idCategoria = produto.getCategoria().getId().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(String precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(String quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public static List<ProdutoDto> converter(Page<Produto> lista) {
		return lista.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

	public static List<ProdutoDto> converter(List<Produto> lista) {
		return lista.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}