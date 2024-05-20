package it.prova.gestionebiblioteca.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;
import it.prova.gestionebiblioteca.model.Libro;

public class LibroDTO {

	private Long id;

	@NotBlank(message = "{libro.titolo.notblank}")
	private String titolo;

	@NotNull(message = "{libro.genere.notnull}")
	private Genere genere;

	@NotNull(message = "{libro.dataPubblicazione.notnull}")
	private LocalDate dataPubblicazione;

	@NotNull(message = "{libro.numeroPagine.notnull}")
	@Min(1)
	private Integer numeroPagine;

	@NotNull(message = "{libro.autore.notnull}")
	private AutoreDTO autore;

	public LibroDTO() {
	}

	public LibroDTO(Long id, String titolo, Genere genere, LocalDate dataPubblicazione, Integer numeroPagine,
			AutoreDTO autore) {
		this.id = id;
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

	public AutoreDTO getAutore() {
		return autore;
	}

	public void setAutore(AutoreDTO autore) {
		this.autore = autore;
	}

	public Libro buildLibroModel() {
		Autore autoreModel = this.autore.buildAutoreModel();
		return new Libro(this.id, this.titolo, this.genere, this.dataPubblicazione, this.numeroPagine, autoreModel);
	}

	public static LibroDTO buildLibroDTOFromModel(Libro libroModel, boolean includeAutori) {
		AutoreDTO autoreDTO = includeAutori ? AutoreDTO.buildAutoreDTOFromModel(libroModel.getAutore()) : null;
		return new LibroDTO(libroModel.getId(), libroModel.getTitolo(), libroModel.getGenere(),
				libroModel.getDataPubblicazione(), libroModel.getNumeroPagine(), autoreDTO);
	}

	public static List<LibroDTO> createLibroDTOListFromModelList(List<Libro> modelListInput, boolean includeAutori) {
		return modelListInput.stream().map(libroEntity -> {
			return LibroDTO.buildLibroDTOFromModel(libroEntity, includeAutori);
		}).collect(Collectors.toList());
	}
}
