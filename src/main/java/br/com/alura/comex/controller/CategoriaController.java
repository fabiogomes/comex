package br.com.alura.comex.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.CategoriaDto;
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
	
	@PostMapping
	public ResponseEntity<CategoriaForm> cadastrar(@RequestBody @Valid CategoriaForm form) {
		Categoria categoria = form.converter();
		this.repositorio.save(categoria);
		
		return ResponseEntity.ok(form);
	}
}