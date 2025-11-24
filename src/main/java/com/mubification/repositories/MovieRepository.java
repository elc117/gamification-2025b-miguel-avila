package com.mubification.repositories;

import com.mubification.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieRepository {

    private final EntityManager em;

    public MovieRepository(EntityManager em) {
        this.em = em;
    }

    public List<Movie> findAll() {
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    public Movie findById(int id) {
        return em.find(Movie.class, id);
    }

    public Movie save(Movie movie) {
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Movie update(Movie movie) {
        try {
            em.getTransaction().begin();
            Movie updated = em.merge(movie);
            em.getTransaction().commit();
            return updated;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(int id) {
        try {
            em.getTransaction().begin();
            Movie movie = em.find(Movie.class, id);

            if (movie != null) em.remove(movie);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
