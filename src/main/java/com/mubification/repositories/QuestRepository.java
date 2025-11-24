package com.mubification.repositories;

import com.mubification.models.Quest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
 
public class QuestRepository {

    private final EntityManager em;

    public QuestRepository(EntityManager em) {
        this.em = em;
    }

    public List<Quest> findAll() {
        TypedQuery<Quest> query = em.createQuery("SELECT a FROM Quest q", Quest.class);
        return query.getResultList();
    }

    public Quest findById(int id) {
        return em.find(Quest.class, id);
    }

    public Quest save(Quest quest) {
        try {
            em.getTransaction().begin();
            em.persist(quest);
            em.getTransaction().commit();
            return quest;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Quest update(Quest quest) {
        try {
            em.getTransaction().begin();
            Quest updated = em.merge(quest);
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
            Quest quest = em.find(Quest.class, id);

            if (quest != null) em.remove(quest);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
