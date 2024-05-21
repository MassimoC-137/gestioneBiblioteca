package it.prova.gestionebiblioteca.repository.autore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.model.Genere;

@Repository
public class CustomAutoreRepositoryImpl implements CustomAutoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Autore> findAllWithLibri() {
        TypedQuery<Autore> query = entityManager.createQuery(
                "SELECT a FROM Autore a JOIN FETCH a.libri", Autore.class);
        return query.getResultList();
    }

    @Override
    public List<Autore> findAllByGenereWithLibri(Genere genere) {
        TypedQuery<Autore> query = entityManager.createQuery(
                "SELECT DISTINCT a FROM Autore a JOIN FETCH a.libri l WHERE l.genere = :genere", Autore.class);
        query.setParameter("genere", genere);
        return query.getResultList();
    }

    @Override
    public List<Autore> findByExample(Autore example) {
        Map<String, Object> paramaterMap = new HashMap<>();
        List<String> whereClauses = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("select a from Autore a left join fetch a.libri l where a.id = a.id ");

        if (StringUtils.isNotEmpty(example.getNome())) {
            whereClauses.add(" a.nome like :nome ");
            paramaterMap.put("nome", "%" + example.getNome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCognome())) {
            whereClauses.add(" a.cognome like :cognome ");
            paramaterMap.put("cognome", "%" + example.getCognome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getNomeArte())) {
            whereClauses.add(" a.nomeArte like :nomeArte ");
            paramaterMap.put("nomeArte", "%" + example.getNomeArte() + "%");
        }
        if (example.getDataNascita() != null) {
            whereClauses.add(" a.dataNascita = :dataNascita ");
            paramaterMap.put("dataNascita", example.getDataNascita());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Autore> typedQuery = entityManager.createQuery(queryBuilder.toString(), Autore.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
