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

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repositorio;

	@GetMapping
	public List<ClienteDto> lista() {
		List<Cliente> lista = repositorio.findAll();
		return ClienteDto.converter(lista);
	}
	
	@PostMapping
	public ResponseEntity<ClienteForm> cadastrar(@RequestBody @Valid ClienteForm form) {
		Cliente cliente = form.converter();
		this.repositorio.save(cliente);
		
		return ResponseEntity.ok(form);
	}
}