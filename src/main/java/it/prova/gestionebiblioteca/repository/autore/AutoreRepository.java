package it.prova.gestionebiblioteca.repository.autore;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;

import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Long>, CustomAutoreRepository {

	@EntityGraph(attributePaths = { "libri" })
	List<Autore> findAllWithLibri();

	@Query("SELECT DISTINCT a FROM Autore a JOIN FETCH a.libri l WHERE l.genere = ?1")
	List<Autore> findAllByGenereWithLibri(Genere genere);
	
}
