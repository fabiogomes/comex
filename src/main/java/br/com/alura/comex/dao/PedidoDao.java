package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Pedido;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido buscaPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}

	public List<Pedido> buscarTodos() {
		String jpql = "SELECT p FROM PedidoComex p";
		return this.em.createQuery(jpql, Pedido.class).getResultList();
	}

	public List<Pedido> buscaTodoDeUmCliente(String nomeDoCliente) {
		String jpql = "SELECT p FROM PedidoComex p WHERE p.cliente.nome= :nome";
		return this.em.createQuery(jpql, Pedido.class).setParameter("nome", nomeDoCliente).getResultList();
	}
}