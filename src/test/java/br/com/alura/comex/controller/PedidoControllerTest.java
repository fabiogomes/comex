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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.comex.controller.dto.PedidoDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PedidoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaCadastrarUmNovoPedidoEDevolver200() throws Exception {
		URI uri = new URI("/api/pedidos");

		String json = "{\r\n"
				+ "    \"idCliente\":\"1\",\r\n"
				+ "    \"itensPedido\": [\r\n"
				+ "        {\r\n"
				+ "            \"id\":\"1\",\r\n"
				+ "            \"quantidade\":\"2\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\":\"2\",\r\n"
				+ "            \"quantidade\":\"2\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\":\"3\",\r\n"
				+ "            \"quantidade\":\"2\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\":\"4\",\r\n"
				+ "            \"quantidade\":\"12\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\":\"5\",\r\n"
				+ "            \"quantidade\":\"8\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaRetornarCincoItensDePedidoEDevolver200() throws Exception {
		URI uri = new URI("/api/pedidos");

		MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		// isso usa um TypeReference para informar API Jackson sobre o tipo genérico das
		// listas
		List<PedidoDto> actual = mapper.readValue(resultado.getResponse().getContentAsString(),
				new TypeReference<List<PedidoDto>>() {
				});
		Assertions.assertEquals(15, actual.size());
	}
}