package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	@Query("SELECT distinct p FROM Pedido p left JOIN FETCH p.itens left JOIN FETCH p.cliente WHERE p.cliente.nome= :nome")
	List<Pedido> buscaTodoDeUmCliente(String nome);
	
	@Query("SELECT distinct p FROM Pedido p left JOIN FETCH p.itens left JOIN FETCH p.cliente")
	List<Pedido> listaTodos();
}