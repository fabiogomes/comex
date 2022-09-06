package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNome(String nome);

	@Query("SELECT c FROM Cliente c WHERE c.status = :status")
	List<Cliente> buscaPorStatus(Status status);
}