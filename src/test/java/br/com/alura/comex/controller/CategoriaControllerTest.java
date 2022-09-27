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

import br.com.alura.comex.controller.dto.CategoriaDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaCadastrarUmaNovaCategoriaEDevolver200() throws Exception {
		URI uri = new URI("/api/categorias");
		
		String json = "{\r\n"
				+ "    \"nome\":\"CELULARES\"\r\n"
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
	public void deveriaRetornarTresCategoriasEDevolver200() throws Exception {
		URI uri = new URI("/api/categorias");

		MvcResult resultado = mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
				.andExpect(
						MockMvcResultMatchers
				.status().isOk()).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();

		// isso usa um TypeReference para informar API Jackson sobre o tipo genérico das listas
		List<CategoriaDto> actual = mapper.readValue(resultado.getResponse().getContentAsString(), new TypeReference<List<CategoriaDto>>() {});
		Assertions.assertEquals(3, actual.size());
	}
}