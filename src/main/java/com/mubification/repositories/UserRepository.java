package com.mubification.repositories;

import com.mubification.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public User save(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public User update(User user) {
        try {
            em.getTransaction().begin();
            User updated = em.merge(user);
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
            User user = em.find(User.class, id);

            if (user != null) em.remove(user);
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
