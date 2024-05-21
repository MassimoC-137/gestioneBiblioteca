package it.prova.gestionebiblioteca.repository.libro;

import java.util.List;

import it.prova.gestionebiblioteca.model.Libro;

public interface CustomLibroRepository {
	
    List<Libro> findByExample(Libro example);
}
