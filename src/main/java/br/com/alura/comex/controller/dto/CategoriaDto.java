package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.Categoria;

public class CategoriaDto {
	private String id;
	private String nome;
	private String status;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId().toString();
		this.nome = categoria.getNome();
		this.status = categoria.getStatus().toString();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static List<CategoriaDto> converter(List<Categoria> lista) {
		return lista.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
}