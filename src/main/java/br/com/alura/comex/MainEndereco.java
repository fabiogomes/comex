package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.util.JPAUtil;

public class MainEndereco {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente fulano = new Cliente();
		fulano.setNome("Fulano de tal");
		fulano.setCpf("12345678901");
		fulano.setEmail("fulano@gmail.com");
		fulano.setTelefone("99999999");
		fulano.setProfissao("Analista de Sistemas");

		Endereco endereco = new Endereco();
		endereco.setRua("Rua Bento Lisboa");
		endereco.setNumero("Nº 148");
		endereco.setComplemento(" APTº 302");
		endereco.setBairro("Catete");
		endereco.setCidade("Rio de Janeiro");
		endereco.setEstado("Rio de Janeiro");
		
		fulano.setEndereco(endereco);
		ClienteDao dao = new ClienteDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(fulano);
		em.getTransaction().commit();
		
		dao.buscarTodos().forEach(System.out::println);
		em.close();
	}
}