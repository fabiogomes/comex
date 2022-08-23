package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.Cliente;
import br.com.alura.comex.Status;

public class ClienteDao {
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public List<Cliente> buscarTodos() {
		String jpql = "SELECT c FROM Cliente c";
		return this.em.createQuery(jpql, Cliente.class).getResultList();
	}

	public Cliente buscaPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = this.em.merge(cliente);
		this.em.remove(cliente);
	}

	public List<Cliente> buscaTodoPorStatus(Status status) {
		String jpql = "SELECT c FROM Cliente c WHERE c.status= :status";
		return this.em.createQuery(jpql, Cliente.class).setParameter("status", status).getResultList();
	}

	public Cliente buscaClientePorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome= :nome";
		return this.em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getSingleResult();
	}
}
