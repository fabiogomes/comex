package br.com.alura.comex.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repositorio;

	@GetMapping
	public List<ProdutoDto> lista(@RequestParam String page) {
		Page<Produto> lista = repositorio.findAll(PageRequest.of(Integer.parseInt(page), 5, Sort.by(Sort.Direction.ASC, "nome")));
		return ProdutoDto.converter(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
		Optional<Produto> produto = repositorio.findById(id);
		
		if (produto.isPresent()) {
		    return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}

		return ResponseEntity.notFound().build();
	}
}