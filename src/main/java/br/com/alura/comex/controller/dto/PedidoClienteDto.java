package br.com.alura.comex.controller.dto;

import br.com.alura.comex.modelo.Cliente;

public class PedidoClienteDto {
	private Long id;
	private String nome;

	public PedidoClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}