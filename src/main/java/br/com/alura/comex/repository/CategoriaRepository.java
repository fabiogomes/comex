package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>{

	@Query("SELECT c FROM Categoria c WHERE c.status='ATIVA'")
	List<Categoria> buscaTodasAsCategoriasAtivas();

	@Query("SELECT c FROM Categoria c WHERE c.status='INATIVA'")
	List<Categoria> buscaTodasAsCategoriasInativas();
}