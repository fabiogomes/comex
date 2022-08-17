package br.com.alura.comex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PedidoServiceTest {
	private PedidoService pedidoService;
	
	@BeforeEach
	private void inicializar() {
		this.pedidoService = new PedidoService();
	}
	
	@Test
	void testTotalRegistrosDaListaComNomeDosClientes() {
		List<String> listaNomeDosClientes = pedidoService.mostraListaComSomenteNomeDosClientes();
		assertEquals(16, listaNomeDosClientes.size());
	}

	@Test
	void testPrimeiroRegistroDaListaComNomeDosClientes() {
		List<String> listaNomeDosClientes = pedidoService.mostraListaComSomenteNomeDosClientes();
		assertEquals("ANA", listaNomeDosClientes.get(0));
	}

	@Test
	void testUltimoRegistroDaListaComNomeDosClientes() {
		List<String> listaNomeDosClientes = pedidoService.mostraListaComSomenteNomeDosClientes();
		assertEquals("BIA", listaNomeDosClientes.get(listaNomeDosClientes.size() - 1));
	}
}
