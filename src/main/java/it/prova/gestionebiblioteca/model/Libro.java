package it.prova.gestionebiblioteca.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "genere")
    private Genere genere;

    @Column(name = "data_pubblicazione")
    private LocalDate dataPubblicazione;

    @Column(name = "numero_pagine")
    private Integer numeroPagine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autore_id", nullable = false)
    private Autore autore;

    public Libro() {
    }

    public Libro(Long id, String titolo, Genere genere, LocalDate dataPubblicazione, Integer numeroPagine, Autore autore) {
        this.id = id;
        this.titolo = titolo;
        this.genere = genere;
        this.dataPubblicazione = dataPubblicazione;
        this.numeroPagine = numeroPagine;
        this.autore = autore;
    }

    public Libro(String titolo, Genere genere, LocalDate dataPubblicazione, Integer numeroPagine, Autore autore) {
        this.titolo = titolo;
        this.genere = genere;
        this.dataPubblicazione = dataPubblicazione;
        this.numeroPagine = numeroPagine;
        this.autore = autore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }
}
