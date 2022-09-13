package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.RelatorioClientesMaisLucrativosProjecao;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.modelo.TipoDeDesconto;
import br.com.alura.comex.modelo.TipoDeDescontoItem;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner {

	private final ClienteRepository repository;
	private final CategoriaRepository categoriaRepository;
	private final ProdutoRepository produtoRepository;
	private final PedidoRepository pedidoRepository;

	public ComexApplication(ClienteRepository repository, CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
		this.repository = repository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.pedidoRepository = pedidoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//iniciarContextoCliente();
		//iniciarContextoCategoria();
		//iniciarContextoProduto();
		//iniciarContextoPedido();
	}

	private void iniciarContextoPedido() {
		//cadastraPedidoSicrano();
		
		//cadastraPedidoBeltrano();
		
		listaTodosPedidos();
		
		listaTodosDeUmCliente();
		
		listaRelatorioDeClientesMaisLucrativos();
	}

	private void listaRelatorioDeClientesMaisLucrativos() {
		System.out.println("Exibir relatório de clientes mais lucrativos =====================");
		
		List<RelatorioClientesMaisLucrativosProjecao> relatorio = pedidoRepository.buscaRelatorioClientesMaisLucrativos();
		
		relatorio.forEach(f -> System.out.println("Nome do cliente: " + f.getNome() + " | Valor: " + f.getValor()));
	}

	private void listaTodosDeUmCliente() {
		System.out.println("Exibir todos os pedidos de Beltrano ==============================");
		
		pedidoRepository.buscaTodoDeUmCliente("Beltrano de tal").forEach(System.out::println);
	}

	private void listaTodosPedidos() {
		System.out.println("Exibir todos os pedidos ==========================================");

		pedidoRepository.listaTodos().forEach(System.out::println);
	}

	private void cadastraPedidoSicrano() {
		System.out.println("Cadastrando o pedido ===========================================");
		
		Cliente sicrano = repository.findById(2L).orElse(null);
		
		Produto mouse = produtoRepository.findById(1L).orElse(null);
		Produto cleanCode = produtoRepository.findById(3L).orElse(null);

		// Itens de pedido de Sicrano ----------------------------------

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
	
		// Pedido de Sicrano -------------------------------------------
		Pedido pedidoSicrano = new Pedido();
		pedidoSicrano.setData(LocalDate.now());
		pedidoSicrano.setCliente(sicrano);
		pedidoSicrano.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);

		pedidoSicrano.addItemDePedido(item1);
		pedidoSicrano.addItemDePedido(item2);
	
		pedidoRepository.save(pedidoSicrano);
	}

	private void cadastraPedidoBeltrano() {
		System.out.println("Cadastrando o pedido de Beltrano ===========================================");
		
		Cliente sicrano = repository.findById(3L).orElse(null);
		
		Produto mouse = produtoRepository.findById(1L).orElse(null);
		Produto cleanCode = produtoRepository.findById(3L).orElse(null);

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
	
		// Pedido de Sicrano -------------------------------------------
		Pedido pedidoBeltrano = new Pedido();
		pedidoBeltrano.setData(LocalDate.now());
		pedidoBeltrano.setCliente(sicrano);
		pedidoBeltrano.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);

		pedidoBeltrano.addItemDePedido(item3);
		pedidoBeltrano.addItemDePedido(item4);
	
		pedidoRepository.save(pedidoBeltrano);
	}
	
	private void iniciarContextoProduto() {
		cadastraProdutos();
		
		//buscaTodosOsProdutos();
		
		//buscaProdutosIndisponiveis();
	}

	private void buscaProdutosIndisponiveis() {
		System.out.println("Exibir todos os produtos INDISPONÍVEIS ==========================================");

		List<Produto> produtos = produtoRepository.listaProdutosIndisponiveis();
		
		produtos.forEach(System.out::println);
	}

	private void buscaTodosOsProdutos() {
		System.out.println("Exibir todos os produtos ==========================================");

		produtoRepository.findAll().forEach(System.out::println);
	}

	private void cadastraProdutos() {
		System.out.println("Cadastrando os produtos ===========================================");
		
		Categoria informatica = categoriaRepository.findById(1L).orElse(null);
		Categoria livro = categoriaRepository.findById(3L).orElse(null);
		
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

		Produto monitor = new Produto();
		monitor.setCategoria(informatica);
		monitor.setNome("Monitor");
		monitor.setPrecoUnitario(new BigDecimal("1759.00"));
		monitor.setQuantidade(40);
		
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

		Produto jpaPersistence = new Produto();
		jpaPersistence.setCategoria(livro);
		jpaPersistence.setNome("JPA Persistence com Hibernante");
		jpaPersistence.setPrecoUnitario(new BigDecimal("99.00"));
		jpaPersistence.setQuantidade(10);

		Produto programadorPragmatico = new Produto();
		programadorPragmatico.setCategoria(livro);
		programadorPragmatico.setNome("Programador Pragmático");
		programadorPragmatico.setPrecoUnitario(new BigDecimal("79.00"));
		programadorPragmatico.setQuantidade(15);
		
		produtoRepository.save(mouse);
		produtoRepository.save(teclado);
		produtoRepository.save(monitor);
		produtoRepository.save(cleanCode);
		produtoRepository.save(junitEmAcao);
		produtoRepository.save(jpaPersistence);
		produtoRepository.save(programadorPragmatico);
	}

	private void iniciarContextoCategoria() {
		cadastraTresCategoriasAtivas();

		buscaTodasCategorias();

		//alteraCategoriaParaInativa();

		//buscaTodasCategorias();
		
		//listaTodasAsCategoriasAtivas();
		
		buscaTodasCategoriasPaginada(1);
	}

	private void iniciarContextoCliente() {
		// salvarClientes();

		// listaTodosOsClientes();

		// atualizarClienteParaSuspenso();

		// listaTodosOsClientes();

		// deletar();

		// listaTodosOsClientes();

		// buscaClientePorNome();

		// pesquisaTodosOsClientesAtivos();
	}

//	private void listaTodosOsClientes() {
//		System.out.println("Exibir todos os clientes ==========================================");
//
//		repository.findAll().forEach(System.out::println);
//	}
//
//	private void buscaClientePorNome() {
//		System.out.println("Exibir buscar pelo nome do cliente ================================");
//
//		List<Cliente> clientes = repository.findByNome("Beltrano de tal");
//
//		clientes.forEach(System.out::println);
//	}
//
//	private void pesquisaTodosOsClientesAtivos() {
//		System.out.println("Exibir clientes com status ATIVO ==================================");
//
//		List<Cliente> clientes = repository.buscaPorStatus(Status.ATIVO);
//
//		clientes.forEach(System.out::println);
//	}
//
//	private void atualizarClienteParaSuspenso() {
//		System.out.println("Atualizando o cliente para SUSPENSO ===============================");
//
//		Cliente cliente = repository.findById(2L).orElse(null);
//		cliente.setStatus(Status.SUSPENSO);
//
//		repository.save(cliente);
//
//		System.out.println("Atualizado!!! =====================================================");
//	}
//
//	private void salvarClientes() {
//		System.out.println("Cadastrando os clientes ===========================================");
//
//		Cliente fulano = new Cliente();
//		fulano.setNome("Fulano de tal");
//		fulano.setCpf("12345678901");
//		fulano.setEmail("fulano@gmail.com");
//		fulano.setTelefone("99999999");
//		fulano.setProfissao("Analista de Sistemas");
//
//		Endereco enderecoFulano = new Endereco();
//		enderecoFulano.setRua("Rua Bento Lisboa");
//		enderecoFulano.setNumero("Nº 148");
//		enderecoFulano.setComplemento(" APTº 302");
//		enderecoFulano.setBairro("Catete");
//		enderecoFulano.setCidade("Rio de Janeiro");
//		enderecoFulano.setEstado("Rio de Janeiro");
//
//		fulano.setEndereco(enderecoFulano);
//
//		Cliente sicrano = new Cliente();
//		sicrano.setNome("Sicrano de tal");
//		sicrano.setCpf("8901898989");
//		sicrano.setEmail("sicrano@gmail.com");
//		sicrano.setTelefone("6666666");
//		sicrano.setProfissao("Engenheiro Civil");
//
//		Endereco enderecoSicrano = new Endereco();
//		enderecoSicrano.setRua("Rua Jiquiba");
//		enderecoSicrano.setNumero("Nº 60");
//		enderecoSicrano.setComplemento(" APTº 102");
//		enderecoSicrano.setBairro("Maracanã");
//		enderecoSicrano.setCidade("Rio de Janeiro");
//		enderecoSicrano.setEstado("Rio de Janeiro");
//
//		sicrano.setEndereco(enderecoSicrano);
//
//		Cliente beltrano = new Cliente();
//		beltrano.setNome("Beltrano de tal");
//		beltrano.setCpf("12312413123");
//		beltrano.setEmail("beltrano@gmail.com");
//		beltrano.setTelefone("1111111");
//		beltrano.setProfissao("Arquiteto");
//
//		Endereco enderecoBeltrano = new Endereco();
//		enderecoBeltrano.setRua("Rua Tropowsky");
//		enderecoBeltrano.setNumero("Nº 900");
//		enderecoBeltrano.setComplemento(" APTº 801");
//		enderecoBeltrano.setBairro("Gutierrez");
//		enderecoBeltrano.setCidade("Belo Horizonte");
//		enderecoBeltrano.setEstado("Minas Gerais");
//
//		beltrano.setEndereco(enderecoBeltrano);
//
//		repository.save(fulano);
//		repository.save(sicrano);
//		repository.save(beltrano);
//
//		System.out.println("Salvo!!! ===================================================================");
//	}
//
//	private void deletar() {
//		System.out.println("Removendo o cliente ========================================================");
//
//		Long id = 1L;
//		repository.deleteById(id);
//
//		System.out.println("Deletado!!! ================================================================");
//	}

	private void cadastraTresCategoriasAtivas() {
		System.out.println("Cadastrando as categorias ========================================");

		Categoria informatica = new Categoria();
		informatica.setNome("CELULARES");

		Categoria moveis = new Categoria();
		moveis.setNome("MOVÉIS");

		Categoria livros = new Categoria();
		livros.setNome("LIVROS");
		
		//Categoria beleza = new Categoria();
		//beleza.setNome("BELEZA E PERFURAMARIA");

		categoriaRepository.save(livros);
		categoriaRepository.save(informatica);
		categoriaRepository.save(moveis);
		//categoriaRepository.save(beleza);
	}

	private void alteraCategoriaParaInativa() {
		System.out.println("Atualizando categoria para INATIVA ===============================");

		Categoria categoria = categoriaRepository.findById(2L).orElse(null);
		categoria.setStatus(StatusCategoria.INATIVA);

		categoriaRepository.save(categoria);

		System.out.println("Atualizado!!! ====================================================");

	}

	private void buscaTodasCategorias() {
		System.out.println("Exibir todas as categorias =======================================");

		categoriaRepository.findAll().forEach(System.out::println);
	}

	private void buscaTodasCategoriasPaginada(Integer pagina) {
		System.out.println("Exibir todas as categorias paginada ==============================");

		Pageable pageable = PageRequest.of(pagina, 5, Sort.unsorted());
		Page<Categoria> categorias = categoriaRepository.findAll(pageable);
		System.out.println(categorias);
		System.out.println("Pagina atual " + categorias.getNumber());
		System.out.println("Total elemento " + categorias.getTotalElements());
		categorias.forEach(System.out::println);
	}

	
	private void listaTodasAsCategoriasAtivas() {
		System.out.println("Exibir todas as categorias ATIVAS ================================");

		List<Categoria> categorias = categoriaRepository.buscaTodasAsCategoriasAtivas();
		
		categorias.forEach(System.out::println);
	}
}