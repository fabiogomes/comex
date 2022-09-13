package br.com.alura.comex.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria implements Comparable<Categoria> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", unique = true)
	private String nome;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusCategoria status = StatusCategoria.ATIVA;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
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

	public StatusCategoria getStatus() {
		return status;
	}

	public void setStatus(StatusCategoria status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Categoria - [nome: " + this.nome + " | status: " + this.status + "]";
	}

	@Override
	public int compareTo(Categoria categoria) {
		// TODO Auto-generated method stub
		return this.nome.compareTo(categoria.getNome());
	}
}