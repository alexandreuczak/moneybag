package br.com.moneybag;import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionFactory {

    private static SessionFactory instance;
    private EntityManagerFactory emf;

    public static SessionFactory getInstance(String persistence) {
        if (instance == null) {
            instance = new SessionFactory(persistence);
        }
        return instance;
    }

    private SessionFactory(String persistence) {    
        emf = Persistence.createEntityManagerFactory(persistence);
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
