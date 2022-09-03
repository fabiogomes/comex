package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	@Query("SELECT p FROM Produto p WHERE p.quantidade=0")
	List<Produto> listaProdutosIndisponiveis();
}