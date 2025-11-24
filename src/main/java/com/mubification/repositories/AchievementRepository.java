package com.mubification.repositories;

import com.mubification.models.Achievement;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AchievementRepository {

    private final EntityManager em;

    public AchievementRepository(EntityManager em) {
        this.em = em;
    }

    public List<Achievement> findAll() {
        TypedQuery<Achievement> query = em.createQuery("SELECT a FROM Achievement a", Achievement.class);
        return query.getResultList();
    }

    public Achievement findById(int id) {
        return em.find(Achievement.class, id);
    }

    public Achievement save(Achievement achievement) {
        try {
            em.getTransaction().begin();
            em.persist(achievement);
            em.getTransaction().commit();
            return achievement;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Achievement update(Achievement achievement) {
        try {
            em.getTransaction().begin();
            Achievement updated = em.merge(achievement);
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
            Achievement achievement = em.find(Achievement.class, id);

            if (achievement != null) em.remove(achievement);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
