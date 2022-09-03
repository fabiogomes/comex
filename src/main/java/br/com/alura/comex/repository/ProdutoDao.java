package br.com.alura.comex.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public List<Produto> listaTodos() {
		String jpql = "SELECT p FROM Produto p";
		return this.em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> listaIndisponiveis() {
		String jpql = "SELECT p FROM Produto p WHERE p.quantidade= :quantidade";
		return this.em.createQuery(jpql, Produto.class).setParameter("quantidade", 0).getResultList();
	}

	public Produto buscaPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public Produto buscaProdutoComCategoria(Long id) {
		String jpql = "SELECT p FROM Produto p JOIN FETCH p.categoria WHERE p.id= :id";
		return this.em.createQuery(jpql, Produto.class).setParameter("id", id).getSingleResult();
	}
}