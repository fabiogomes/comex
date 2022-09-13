package br.com.alura.comex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.CategoriaDto;
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
}