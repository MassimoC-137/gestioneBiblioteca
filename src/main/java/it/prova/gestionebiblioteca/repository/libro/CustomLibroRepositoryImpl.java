package it.prova.gestionebiblioteca.repository.libro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import it.prova.gestionebiblioteca.model.Libro;

@Repository
public class CustomLibroRepositoryImpl implements CustomLibroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Libro> findByExample(Libro example) {
        Map<String, Object> paramaterMap = new HashMap<>();
        List<String> whereClauses = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("select l from Libro l left join fetch l.autore a where l.id = l.id ");

        if (StringUtils.isNotEmpty(example.getTitolo())) {
            whereClauses.add(" l.titolo like :titolo ");
            paramaterMap.put("titolo", "%" + example.getTitolo() + "%");
        }
        if (example.getGenere() != null) {
            whereClauses.add(" l.genere = :genere ");
            paramaterMap.put("genere", example.getGenere());
        }
        if (example.getDataPubblicazione() != null) {
            whereClauses.add(" l.dataPubblicazione = :dataPubblicazione ");
            paramaterMap.put("dataPubblicazione", example.getDataPubblicazione());
        }
        if (example.getNumeroPagine() != null) {
            whereClauses.add(" l.numeroPagine = :numeroPagine ");
            paramaterMap.put("numeroPagine", example.getNumeroPagine());
        }
        if (example.getAutore() != null && example.getAutore().getId() != null) {
            whereClauses.add(" a.id = :autoreId ");
            paramaterMap.put("autoreId", example.getAutore().getId());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Libro> typedQuery = entityManager.createQuery(queryBuilder.toString(), Libro.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
