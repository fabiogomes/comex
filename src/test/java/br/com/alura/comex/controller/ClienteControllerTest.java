package br.com.alura.comex.controller;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.controller.dto.ClienteDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaCadastrarUmNovoClienteEDevolver200() throws Exception {
		URI uri = new URI("/api/clientes");
		
		String json = "{\r\n"
				+ "    \"nome\":\"Fulano\",\r\n"
				+ "    \"cpf\":\"98989898988\",\r\n"
				+ "    \"telefone\":\"25344333\",\r\n"
				+ "    \"email\":\"fulano@gmail.com\",\r\n"
				+ "    \"profissao\":\"Analista de Negócios\",\r\n"
				+ "    \"rua\":\"Rua do Ouvidor\",\r\n"
				+ "    \"numero\":\"50\",\r\n"
				+ "    \"complemento\":\"Sala 101\",\r\n"
				+ "    \"bairro\":\"Centro\",\r\n"
				+ "    \"cidade\":\"Rio de Janeiro\",\r\n"
				+ "    \"estado\":\"Rio de Janeiro\"\r\n"
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}

	@Test
	public void deveriaRetornarCincoClientesEDevolver200() throws Exception {
		URI uri = new URI("/api/clientes");

		MvcResult resultado = mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
				.andExpect(
						MockMvcResultMatchers
				.status().isOk()).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();

		// isso usa um TypeReference para informar API Jackson sobre o tipo genérico das listas
		List<ClienteDto> actual = mapper.readValue(resultado.getResponse().getContentAsString(), new TypeReference<List<ClienteDto>>() {});
		Assertions.assertEquals(5, actual.size());
	}
}