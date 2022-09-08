package br.com.alura.comex.controller.form;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.Status;

public class ClienteForm {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private String profissao;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente converter() {
		Endereco endereco = new Endereco();
		endereco.setRua(this.rua);
		endereco.setNumero(this.numero);
		endereco.setComplemento(this.complemento);
		endereco.setBairro(this.bairro);
		endereco.setCidade(this.cidade);
		endereco.setEstado(this.estado);
		
		return new Cliente(this.nome, this.cpf, this.telefone, this.email, this.profissao, endereco , Status.ATIVO);
	}
}