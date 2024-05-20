package it.prova.gestionebiblioteca.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autore")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "nome_arte")
    private String nomeArte;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    public Autore() {
    }

    public Autore(Long id, String nome, String cognome, String nomeArte, LocalDate dataNascita) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.nomeArte = nomeArte;
        this.dataNascita = dataNascita;
    }

    public Autore(String nome, String cognome, String nomeArte, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.nomeArte = nomeArte;
        this.dataNascita = dataNascita;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNomeArte() {
        return nomeArte;
    }

    public void setNomeArte(String nomeArte) {
        this.nomeArte = nomeArte;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
}
