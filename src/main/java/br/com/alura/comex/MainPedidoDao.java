package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoComex;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.RelatorioClientesMaisLucrativosVo;
import br.com.alura.comex.modelo.TipoDeDesconto;
import br.com.alura.comex.modelo.TipoDeDescontoItem;
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

		Cliente beltrano = new Cliente();
		beltrano.setNome("Beltrano");
		beltrano.setCpf("99999999");
		beltrano.setEmail("beltrano@gmail.com");
		beltrano.setTelefone("55555555");
		beltrano.setProfissao("Gerente de Projetos");
		
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		clienteDao.cadastrar(fulano);
		clienteDao.cadastrar(beltrano);
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
		
		Produto mouse = new Produto("Mouse", new BigDecimal("34.00"), 10, informatica);
		Produto cleanCode = new Produto("Clean Code", new BigDecimal("89.00"), 20, livro);

		ProdutoDao produtoDao = new ProdutoDao(em);

		em.getTransaction().begin();

		produtoDao.cadastrar(mouse);
		produtoDao.cadastrar(cleanCode);

		// Itens de pedido de Fulano ----------------------------------

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

		// Itens de pedido de Beltrano ---------------------------------
		ItemDePedido item3 = new ItemDePedido();
		item3.setPrecoUnitario(mouse.getPrecoUnitario());
		item3.setQuantidade(8);
		item3.setTipoDeDesconto(TipoDeDescontoItem.NENHUM);
		item3.setProduto(mouse);
		
		ItemDePedido item4 = new ItemDePedido();
		item4.setPrecoUnitario(cleanCode.getPrecoUnitario());
		item4.setQuantidade(16);
		item4.setTipoDeDesconto(TipoDeDescontoItem.NENHUM);
		item4.setProduto(cleanCode);
	
		// Pedido de Fulano -------------------------------------------
		PedidoComex pedidoFulano = new PedidoComex();
		pedidoFulano.setData(LocalDate.now());
		pedidoFulano.setCliente(fulano);
		pedidoFulano.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);

		System.out.println("Verifica produtos ====================================");
		produtoDao.listaTodos().forEach(System.out::println);
		
		pedidoFulano.addItemDePedido(item1);
		pedidoFulano.addItemDePedido(item2);

		// Pedido de Beltrano -------------------------------------------
		PedidoComex pedidoBeltrano = new PedidoComex();
		pedidoBeltrano.setData(LocalDate.now());
		pedidoBeltrano.setCliente(beltrano);
		pedidoBeltrano.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);
		
		System.out.println("Verifica produtos ====================================");
		produtoDao.listaTodos().forEach(System.out::println);
		
		pedidoBeltrano.addItemDePedido(item3);
		pedidoBeltrano.addItemDePedido(item4);
		
		// Efetivar transação dos pedidos --------------------------------
		PedidoDao pedidoDao = new PedidoDao(em);

		pedidoDao.cadastrar(pedidoFulano);
		pedidoDao.cadastrar(pedidoBeltrano);
		
		em.getTransaction().commit();
		
		PedidoComex buscaPorId = pedidoDao.buscaPorId(1L);
		System.out.println(buscaPorId.getItens().size());
		
		pedidoDao.buscaTodoDeUmCliente("Fulano de tal").forEach(System.out::println);
		
		System.out.println("Relatório de Cliente mais lucrativos ==================");
		
		List<RelatorioClientesMaisLucrativosVo> buscaRelatorioClientesMaisLucrativos = pedidoDao.buscaRelatorioClientesMaisLucrativos();
		
		buscaRelatorioClientesMaisLucrativos.forEach(System.out::println);
		
		System.out.println("Verifica produtos ====================================");
		
		produtoDao.listaTodos().forEach(System.out::println);
		
	}
}