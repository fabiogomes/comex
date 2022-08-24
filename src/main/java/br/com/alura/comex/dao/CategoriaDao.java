package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.Categoria;
import br.com.alura.comex.StatusCategoria;

public class CategoriaDao {
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public Categoria buscaPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}

	public List<Categoria> listaTodas() {
		String jpql = "SELECT c FROM Categoria c";
		return this.em.createQuery(jpql, Categoria.class).getResultList();
	}

	public List<Categoria> listaAtivas() {
		String jpql = "SELECT c FROM Categoria c WHERE c.status= :status";
		return this.em.createQuery(jpql, Categoria.class).setParameter("status", StatusCategoria.ATIVA).getResultList();
	}

	public List<Categoria> listaInativas() {
		String jpql = "SELECT c FROM Categoria c WHERE c.status= :status";
		return this.em.createQuery(jpql, Categoria.class).setParameter("status", StatusCategoria.INATIVA)
				.getResultList();
	}
}