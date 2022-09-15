package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query("SELECT c FROM Categoria c WHERE c.status='ATIVA'")
	List<Categoria> buscaTodasAsCategoriasAtivas();

	@Query("SELECT c FROM Categoria c WHERE c.status='INATIVA'")
	List<Categoria> buscaTodasAsCategoriasInativas();

	@Query("SELECT c FROM Categoria c ORDER BY c.nome ASC")
	List<Categoria> listaTodas();
}
