package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

public class AtualizacaoCategoriaForm {
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 250)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 7)
	private String status;

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

	public Categoria atualizar(Long id, CategoriaRepository repositorio) {
		Categoria categoria = repositorio.findById(id).orElse(null);
		categoria.setNome(this.nome);
		categoria.setStatus(StatusCategoria.valueOf(this.status));
		
		return categoria;
	}
}