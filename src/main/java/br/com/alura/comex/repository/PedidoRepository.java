package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Pedido;
import br.com.alura.comex.modelo.RelatorioClientesMaisLucrativosProjecao;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	@Query("SELECT distinct p FROM Pedido p left JOIN FETCH p.itens left JOIN FETCH p.cliente WHERE p.cliente.nome= :nome")
	List<Pedido> buscaTodoDeUmCliente(String nome);

	@Query("SELECT distinct p FROM Pedido p left JOIN FETCH p.itens left JOIN FETCH p.cliente")
	List<Pedido> listaTodos();

	@Query(value = "select cliente.nome, MAX(item.valor) as valor FROM pedidos as p inner JOIN itens_pedido as item on item.pedido_id=p.id inner join cliente as cliente on cliente.id = p.cliente_id GROUP BY cliente.nome", nativeQuery = true)
	List<RelatorioClientesMaisLucrativosProjecao> buscaRelatorioClientesMaisLucrativos();
}