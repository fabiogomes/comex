package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.PedidoComex;
import br.com.alura.comex.modelo.RelatorioClientesMaisLucrativosVo;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(PedidoComex pedido) {
		this.em.persist(pedido);
	}

	public PedidoComex buscaPorId(Long id) {
		return this.em.find(PedidoComex.class, id);
	}

	public List<PedidoComex> buscarTodos() {
		String jpql = "SELECT p FROM PedidoComex p";
		return this.em.createQuery(jpql, PedidoComex.class).getResultList();
	}

	public List<PedidoComex> buscaTodoDeUmCliente(String nomeDoCliente) {
		String jpql = "SELECT p FROM PedidoComex p WHERE p.cliente.nome= :nome";
		return this.em.createQuery(jpql, PedidoComex.class).setParameter("nome", nomeDoCliente).getResultList();
	}
	
	public List<RelatorioClientesMaisLucrativosVo> buscaRelatorioClientesMaisLucrativos() {
		String jpql = "SELECT new br.com.alura.comex.modelo.RelatorioClientesMaisLucrativosVo(p.cliente.nome , MAX(item.valor)) FROM PedidoComex p JOIN p.itens as item GROUP BY p.cliente.nome";
		return this.em.createQuery(jpql, RelatorioClientesMaisLucrativosVo.class).getResultList();
		
	}
}