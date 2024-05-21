package it.prova.gestionebiblioteca.service;

import java.util.List;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;

public interface AutoreService {
	
    public List<Autore> listAllElements();
    
    public Autore caricaSingoloElemento(Long id);
    
    public Autore caricaSingoloElementoConLibri(Long id);
    
    public void aggiorna(Autore autoreInstance);
    
    public void inserisciNuovo(Autore autoreInstance);
    
    public void rimuovi(Long id);
    
    public List<Autore> findAllWithLibri();
    
    public List<Autore> findAllByGenereWithLibri(Genere genere);
    
    public List<Autore> findByExample(Autore example); 
    
}
