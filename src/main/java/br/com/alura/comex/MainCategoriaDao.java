package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.util.JPAUtil;

public class MainCategoriaDao {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Categoria informatica = new Categoria();
		informatica.setNome("INFORMÁTICA");

		Categoria moveis = new Categoria();
		moveis.setNome("MÓVEIS");

		Categoria livros = new Categoria();
		livros.setNome("LIVROS");
		
		CategoriaDao dao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(informatica);
		dao.cadastrar(moveis);
		dao.cadastrar(livros);
		em.getTransaction().commit();
		
		dao.listaTodas().forEach(System.out::println);
		
		System.out.println("====================================================");

		em.getTransaction().begin();
		livros.setStatus(StatusCategoria.INATIVA);
		dao.atualizar(livros);
		em.getTransaction().commit();
		
		dao.listaTodas().forEach(System.out::println);
		
		System.out.println("====================================================");
		
		dao.listaAtivas().forEach(System.out::println);
	}
}