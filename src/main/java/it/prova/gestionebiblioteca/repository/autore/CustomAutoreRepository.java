package it.prova.gestionebiblioteca.repository.autore;

import java.util.List;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;

public interface CustomAutoreRepository {

	public List<Autore> findAllWithLibri();
	
    public List<Autore> findAllByGenereWithLibri(Genere genere);
    
	public List<Autore> findByExample(Autore example);
}
