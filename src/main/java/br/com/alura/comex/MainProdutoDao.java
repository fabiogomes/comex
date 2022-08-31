package br.com.alura.comex;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.JPAUtil;

public class MainProdutoDao {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
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

		Produto teclado = new Produto();
		teclado.setCategoria(informatica);
		teclado.setNome("Teclado");
		teclado.setPrecoUnitario(new BigDecimal("79.00"));
		teclado.setQuantidade(0);
		
		Produto cleanCode = new Produto();
		cleanCode.setCategoria(livro);
		cleanCode.setNome("Clean Code");
		cleanCode.setPrecoUnitario(new BigDecimal("89.00"));
		cleanCode.setQuantidade(20);
		
		Produto junitEmAcao = new Produto();
		junitEmAcao.setCategoria(livro);
		junitEmAcao.setNome("JUnit em ação");
		junitEmAcao.setPrecoUnitario(new BigDecimal("69.00"));
		junitEmAcao.setQuantidade(0);
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		produtoDao.cadastrar(mouse);
		produtoDao.cadastrar(teclado);
		produtoDao.cadastrar(cleanCode);
		produtoDao.cadastrar(junitEmAcao);
		em.getTransaction().commit();
		
		em.clear(); //Necessário para utilizar o select do find para simular a estratégia EAGER
		
		System.out.println("==============================================================");
		//produtoDao.listaTodos().forEach(System.out::println);
		System.out.println("==============================================================");
		//produtoDao.listaIndisponiveis().forEach(System.out::println);
		
		//Produto buscaProduto = produtoDao.buscaPorId(1L);
		Produto buscaProduto = produtoDao.buscaProdutoComCategoria(1L);
		
		em.close();
		System.out.println(buscaProduto.getCategoria().getNome());
		
		//System.out.println(buscaProduto.getCategoria().getNome());
	}
}