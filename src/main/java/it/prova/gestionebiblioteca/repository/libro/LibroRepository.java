package it.prova.gestionebiblioteca.repository.libro;


import org.springframework.data.jpa.repository.JpaRepository;

import it.prova.gestionebiblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>, CustomLibroRepository {

}
