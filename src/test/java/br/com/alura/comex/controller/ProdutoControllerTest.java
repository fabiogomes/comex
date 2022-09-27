package br.com.alura.comex.controller;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
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

import br.com.alura.comex.controller.dto.ProdutoDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void adeveriaCadastrarUmNovoProdutoEDevolver200() throws Exception {
		URI uri = new URI("/api/produtos");
		
		String json = "{\r\n"
				+ "    \"nome\":\"DDD13 - Desenvolvimento Orientando a Dominio\",\r\n"
				+ "    \"descricao\":\"Dicas para melhorar o design da aplicação\",\r\n"
				+ "    \"precoUnitario\":\"169.00\",\r\n"
				+ "    \"quantidadeEmEstoque\":\"140\",\r\n"
				+ "    \"categoriaId\":\"1\"\r\n"
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
	}
	
	@Test
	@Order(2)
	public void deveriaRetornarSeteProdutosEDevolver200() throws Exception {
		URI uri = new URI("/api/produtos");

		MvcResult resultado = mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
				.andExpect(
						MockMvcResultMatchers
				.status().isOk()).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();

		// isso usa um TypeReference para informar API Jackson sobre o tipo genérico das listas
		List<ProdutoDto> actual = mapper.readValue(resultado.getResponse().getContentAsString(), new TypeReference<List<ProdutoDto>>() {});
		Assertions.assertEquals(22, actual.size());
	}
}