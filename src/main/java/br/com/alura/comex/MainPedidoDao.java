package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.util.JPAUtil;

public class MainPedidoDao {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente fulano = new Cliente();
		fulano.setNome("Fulano de tal");
		fulano.setCpf("12345678901");
		fulano.setEmail("fulano@gmail.com");
		fulano.setTelefone("99999999");
		fulano.setProfissao("Analista de Sistemas");

		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		clienteDao.cadastrar(fulano);
		em.getTransaction().commit();
		
		Categoria informatica = new Categoria();
		informatica.setNome("INFORMÁTICA");

		Categoria livro = new Categoria();
		livro.setNome("LIVROS");

		CategoriaDao dao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(informatica);
		dao.cadastrar(livro);
		em.getTransaction().commit();
		
		Produto mouse = new Produto();
		mouse.setCategoria(informatica);
		mouse.setNome("Mouse");
		mouse.setPrecoUnitario(new BigDecimal("34.00"));
		mouse.setQuantidade(10);

		Produto cleanCode = new Produto();
		cleanCode.setCategoria(livro);
		cleanCode.setNome("Clean Code");
		cleanCode.setPrecoUnitario(new BigDecimal("89.00"));
		cleanCode.setQuantidade(20);

		ProdutoDao produtoDao = new ProdutoDao(em);

		em.getTransaction().begin();

		produtoDao.cadastrar(mouse);
		produtoDao.cadastrar(cleanCode);

		ItemDePedido item1 = new ItemDePedido();
		item1.setPrecoUnitario(mouse.getPrecoUnitario());
		item1.setQuantidade(2);
		item1.setTipoDeDesconto(TipoDeDescontoItem.NENHUM);
		item1.setProduto(mouse);
		
		ItemDePedido item2 = new ItemDePedido();
		item2.setPrecoUnitario(cleanCode.getPrecoUnitario());
		item2.setQuantidade(4);
		item2.setTipoDeDesconto(TipoDeDescontoItem.NENHUM);
		item2.setProduto(cleanCode);
	
		PedidoComex pedidoFulano = new PedidoComex();
		pedidoFulano.setData(LocalDate.now());
		pedidoFulano.setCliente(fulano);
		pedidoFulano.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);

		pedidoFulano.addItemDePedido(item1);
		pedidoFulano.addItemDePedido(item2);
		
		PedidoDao pedidoDao = new PedidoDao(em);

		pedidoDao.cadastrar(pedidoFulano);
		em.getTransaction().commit();
		
		pedidoDao.buscaTodoDeUmCliente("Fulano de tal").forEach(System.out::println);
	}
}