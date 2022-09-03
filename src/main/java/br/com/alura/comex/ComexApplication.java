package br.com.alura.comex;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.Status;
import br.com.alura.comex.repository.ClienteRepository;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner {

	private final ClienteRepository repository;

	public ComexApplication(ClienteRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		iniciarContextoCliente();
	}

	private void iniciarContextoCliente() {
		//salvarClientes();
		
		//listaTodosOsClientes();

		//atualizarClienteParaSuspenso();
		
		//listaTodosOsClientes();

		//deletar();
		
		//listaTodosOsClientes();
		
		//buscaClientePorNome();
		
		//pesquisaTodosOsClientesAtivos();
	}

	private void listaTodosOsClientes() {
		System.out.println("Exibir todos os clientes ==========================================");

		repository.findAll().forEach(System.out::println);
	}

	private void buscaClientePorNome() {
		System.out.println("Exibir buscar pelo nome do cliente ================================");

		List<Cliente> clientes = repository.findByNome("Beltrano de tal");

		clientes.forEach(System.out::println);
	}

	private void pesquisaTodosOsClientesAtivos() {
		System.out.println("Exibir clientes com status ATIVO ==================================");

		List<Cliente> clientes = repository.buscaPorStatus(Status.ATIVO);

		clientes.forEach(System.out::println);
	}

	private void atualizarClienteParaSuspenso() {
		System.out.println("Atualizando o cliente para SUSPENSO ===============================");

		Cliente cliente = repository.findById(2L).orElse(null);
		cliente.setStatus(Status.SUSPENSO);

		repository.save(cliente);

		System.out.println("Atualizado!!! =====================================================");
	}

	private void salvarClientes() {
		System.out.println("Cadastrando os clientes ===========================================");

		Cliente fulano = new Cliente();
		fulano.setNome("Fulano de tal");
		fulano.setCpf("12345678901");
		fulano.setEmail("fulano@gmail.com");
		fulano.setTelefone("99999999");
		fulano.setProfissao("Analista de Sistemas");

		Endereco enderecoFulano = new Endereco();
		enderecoFulano.setRua("Rua Bento Lisboa");
		enderecoFulano.setNumero("Nº 148");
		enderecoFulano.setComplemento(" APTº 302");
		enderecoFulano.setBairro("Catete");
		enderecoFulano.setCidade("Rio de Janeiro");
		enderecoFulano.setEstado("Rio de Janeiro");

		fulano.setEndereco(enderecoFulano);

		Cliente sicrano = new Cliente();
		sicrano.setNome("Sicrano de tal");
		sicrano.setCpf("8901898989");
		sicrano.setEmail("sicrano@gmail.com");
		sicrano.setTelefone("6666666");
		sicrano.setProfissao("Engenheiro Civil");

		Endereco enderecoSicrano = new Endereco();
		enderecoSicrano.setRua("Rua Jiquiba");
		enderecoSicrano.setNumero("Nº 60");
		enderecoSicrano.setComplemento(" APTº 102");
		enderecoSicrano.setBairro("Maracanã");
		enderecoSicrano.setCidade("Rio de Janeiro");
		enderecoSicrano.setEstado("Rio de Janeiro");

		sicrano.setEndereco(enderecoSicrano);

		Cliente beltrano = new Cliente();
		beltrano.setNome("Beltrano de tal");
		beltrano.setCpf("12312413123");
		beltrano.setEmail("beltrano@gmail.com");
		beltrano.setTelefone("1111111");
		beltrano.setProfissao("Arquiteto");

		Endereco enderecoBeltrano = new Endereco();
		enderecoBeltrano.setRua("Rua Tropowsky");
		enderecoBeltrano.setNumero("Nº 900");
		enderecoBeltrano.setComplemento(" APTº 801");
		enderecoBeltrano.setBairro("Gutierrez");
		enderecoBeltrano.setCidade("Belo Horizonte");
		enderecoBeltrano.setEstado("Minas Gerais");

		beltrano.setEndereco(enderecoBeltrano);

		repository.save(fulano);
		repository.save(sicrano);
		repository.save(beltrano);

		System.out.println("Salvo!!! ===================================================================");
	}

	private void deletar() {
		System.out.println("Removendo o cliente ========================================================");

		Long id = 1L;
		repository.deleteById(id);

		System.out.println("Deletado!!! ================================================================");
	}
}