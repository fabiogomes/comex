package br.com.alura.comex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository repositorio;
	
	@RequestMapping("/api/clientes")
	public List<ClienteDto> lista() {
		List<Cliente> lista = repositorio.findAll();
		return ClienteDto.converter(lista);
	}
}
