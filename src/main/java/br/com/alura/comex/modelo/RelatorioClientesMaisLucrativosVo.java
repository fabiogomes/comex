package br.com.alura.comex.modelo;

import java.math.BigDecimal;

public class RelatorioClientesMaisLucrativosVo {
	private String nome;
	private BigDecimal valor;

	public RelatorioClientesMaisLucrativosVo(String nome, BigDecimal valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "RelatorioClientesMaisLucrativosVo [nome=" + nome + ", valor=" + valor + "]";
	}
}