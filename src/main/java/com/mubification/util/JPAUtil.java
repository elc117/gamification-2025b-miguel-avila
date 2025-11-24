package com.mubification.util;

import javax.persistence.*;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mubificationPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
