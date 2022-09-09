package br.com.alura.comex.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.comex.modelo.Cliente;

public class SpecificationCliente {
	public static Specification<Cliente> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
	}
}
