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
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
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
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Categoria))
			return false;
		Categoria categoria = (Categoria) o;
		return (this.nome == null && categoria.nome == null) || (this.nome != null && this.nome.equals(categoria.nome));
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
}