package com.mubification.repositories;

import com.mubification.models.Review;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReviewRepository {

    private final EntityManager em;

    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public List<Review> findAll() {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r", Review.class);
        return query.getResultList();
    }

    public Review findById(int id) {
        return em.find(Review.class, id);
    }

    public Review save(Review review) {
        try {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
            return review;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Review update(Review review) {
        try {
            em.getTransaction().begin();
            Review updated = em.merge(review);
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
            Review review = em.find(Review.class, id);

            if (review != null) em.remove(review);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
