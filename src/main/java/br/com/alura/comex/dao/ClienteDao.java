package br.com.alura.comex.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;

public class ClienteDao {
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public List<Cliente> buscarTodos() {
		String jpql = "SELECT c FROM Cliente c";
		
		return this.em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
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
		String jpql = "SELECT c.nome as nome, c.email as email FROM Cliente c WHERE c.status= :status";
		return montaProjecaoCustomizadaPorStatus(status, jpql);
	}

	private List<Cliente> montaProjecaoCustomizadaPorStatus(Status status, String jpql) {
		List<Cliente> clientes = new ArrayList<>();
		List<Tuple> resultado = this.em.createQuery(jpql, Tuple.class).setParameter("status", status).getResultList();
		
		for (Tuple tuple : resultado) {
			Cliente cliente = new Cliente();
			cliente.setNome(tuple.get("nome").toString());
			cliente.setEmail(tuple.get("email").toString());
			clientes.add(cliente);
		}
		return clientes;
	}

	public List<Cliente> buscaClientePorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome= :nome";
		return this.em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();
	}
}
