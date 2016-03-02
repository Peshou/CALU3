package com.javelin.search;

import com.javelin.domain.Blog;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Stefan on 3/2/2016.
 */
@Repository
@Transactional
public class FTSearch {
    @PersistenceContext
    private EntityManager entityManager;

    public <T> List<?> search(T classForSearch, String text, String... fields){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(classForSearch.getClass()).get();

        Query query =
                queryBuilder
                .keyword()
                .onFields(fields)
                .matching(text)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query,classForSearch.getClass());

        List results = jpaQuery.getResultList();
        return results;
    }

}
