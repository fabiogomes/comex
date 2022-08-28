package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;
import br.com.alura.comex.util.JPAUtil;

public class MainClienteDao {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente fulano = new Cliente();
		fulano.setNome("Fulano de tal");
		fulano.setCpf("12345678901");
		fulano.setEmail("fulano@gmail.com");
		fulano.setTelefone("99999999");
		fulano.setProfissao("Analista de Sistemas");
		
		Cliente sicrano = new Cliente();
		sicrano.setNome("Sicrano de tal");
		sicrano.setCpf("8901898989");
		sicrano.setEmail("sicrano@gmail.com");
		sicrano.setTelefone("6666666");
		sicrano.setProfissao("Engenheiro Civil");

		Cliente beltrano = new Cliente();
		beltrano.setNome("Beltrano de tal");
		beltrano.setCpf("12312413123");
		beltrano.setEmail("beltrano@gmail.com");
		beltrano.setTelefone("1111111");
		beltrano.setProfissao("Arquiteto");
		
		ClienteDao dao = new ClienteDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(fulano);
		dao.cadastrar(sicrano);
		dao.cadastrar(beltrano);
		em.getTransaction().commit();
		
		
		dao.buscarTodos().forEach(System.out::println);
		
		em.getTransaction().begin();
		beltrano.setStatus(Status.SUSPENSO);
		
		dao.atualizar(beltrano);
		em.getTransaction().commit();
		System.out.println(":: Exibe Clientes apos atualização ===========================================================");
		dao.buscarTodos().forEach(System.out::println);
		
		System.out.println(":: Busca cliente pelo nome ===================================================================");
		
		System.out.println(dao.buscaClientePorNome("Fulano de tal"));

		System.out.println(":: Busca clientes pelo status ATIVO ===========================================================");
		
		dao.buscaTodoPorStatus(Status.ATIVO).forEach(System.out::println);
		
		em.close();
	}
}