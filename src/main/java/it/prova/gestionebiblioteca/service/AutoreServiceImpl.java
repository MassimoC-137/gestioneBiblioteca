package it.prova.gestionebiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;
import it.prova.gestionebiblioteca.repository.autore.AutoreRepository;

@Service
public class AutoreServiceImpl implements AutoreService {

    @Autowired
    private AutoreRepository repository;

    @Transactional(readOnly = true)
    public List<Autore> listAllElements() {
        return (List<Autore>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Autore caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Autore caricaSingoloElementoConLibri(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Autore autoreInstance) {
        repository.save(autoreInstance);
    }

    @Transactional
    public void inserisciNuovo(Autore autoreInstance) {
        repository.save(autoreInstance);
    }

    @Transactional
    public void rimuovi(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Autore> findAllWithLibri() {
        return repository.findAllWithLibri();
    }

    @Transactional(readOnly = true)
    public List<Autore> findAllByGenereWithLibri(Genere genere) {
        return repository.findAllByGenereWithLibri(genere);
    }

}
