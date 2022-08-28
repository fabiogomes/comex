package br.com.alura.comex.modelo;

public class RelatorioClientesPorEstadoVo {
	private Long numeroDeCliente;
	private String estado;

	public RelatorioClientesPorEstadoVo() {}
	
	public RelatorioClientesPorEstadoVo(String estado, Long numeroDeCliente) {
		this.numeroDeCliente = numeroDeCliente;
		this.estado = estado;
	}


	public Long getNumeroDeCliente() {
		return numeroDeCliente;
	}

	public void setNumeroDeCliente(Long numeroDeCliente) {
		this.numeroDeCliente = numeroDeCliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "RelatorioClientesPorEstadoVo [numeroDeCliente=" + numeroDeCliente + ", estado=" + estado + "]";
	}
}