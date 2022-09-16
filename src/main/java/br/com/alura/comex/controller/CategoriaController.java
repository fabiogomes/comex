package br.com.alura.comex.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.AtualizacaoCategoriaForm;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repositorio;

	@GetMapping
	public List<CategoriaDto> lista() {
		List<Categoria> lista = repositorio.listaTodas();
		return CategoriaDto.converter(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id) {
		Optional<Categoria> categoria = repositorio.findById(id);
		
		if (categoria.isPresent()) {
		    return ResponseEntity.ok(new CategoriaDto(categoria.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaForm> cadastrar(@RequestBody @Valid CategoriaForm form) {
		Categoria categoria = form.converter();
		this.repositorio.save(categoria);
		
		return ResponseEntity.ok(form);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoCategoriaForm form) {
		Categoria categoria = form.atualizar(id, repositorio);
		
		return ResponseEntity.ok(new CategoriaDto(categoria));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		this.repositorio.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
}