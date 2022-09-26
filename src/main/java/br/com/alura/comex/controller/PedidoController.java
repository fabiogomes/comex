package br.com.alura.comex.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.PedidoForm;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.TipoDeDesconto;
import br.com.alura.comex.modelo.TipoDeDescontoItem;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoForm> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = new Pedido();
		Optional<Cliente> cliente = clienteRepository.findById(form.getIdCliente());

		if (cliente.isPresent()) {
			int quantidadePedidos = 0;
			pedido.setCliente(cliente.get());
			
			List<Produto> produtos = form.getItensPedido();
			
			for (Produto produto : produtos) {
				Optional<Produto> produtoPorId = produtoRepository.findById(produto.getId());
				
				if(produtoPorId.isPresent()) {
					if(produtoPorId.get().getQuantidade() == 0) {
						return ResponseEntity.notFound().build();
					}
					
					ItemDePedido item = new ItemDePedido();
					item.setProduto(produtoPorId.get());
					item.setPrecoUnitario(produtoPorId.get().getPrecoUnitario());
					item.setQuantidade(produto.getQuantidade());
					
					if(produto.getQuantidade() > 10) {
						item.setDesconto(new BigDecimal("0.1"));
						item.setTipoDeDesconto(TipoDeDescontoItem.QUANTIDADE);
					} else {
						item.setDesconto(BigDecimal.ZERO);
					}
					
					pedido.addItemDePedido(item);
					
					produto.setNome(produtoPorId.get().getNome());
					produto.setDescricao(produtoPorId.get().getDescricao());
					produto.setPrecoUnitario(produtoPorId.get().getPrecoUnitario());
					produto.setCategoria(produtoPorId.get().getCategoria());
				} else {
					return ResponseEntity.notFound().build();
				}
				quantidadePedidos++;
			}
			
			if(quantidadePedidos >= 5) {
				pedido.setDesconto(new BigDecimal("0.05"));
				pedido.setTipoDeDesconto(TipoDeDesconto.FIDELIDADE);
			}
			
		    this.pedidoRepository.save(pedido);
			
			URI uri = uriBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(form);
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isPresent()) {
		    return ResponseEntity.ok(new PedidoDto(pedido.get()));
		}

		return ResponseEntity.notFound().build();
	}
}