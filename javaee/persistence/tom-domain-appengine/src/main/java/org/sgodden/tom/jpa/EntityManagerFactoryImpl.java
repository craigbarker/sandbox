package org.sgodden.tom.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactoryImpl implements EntityManagerFactory {

    private String persistenceUnitName;
    private javax.persistence.EntityManagerFactory jpaFactory;
    private static EntityManagerFactoryImpl instance;

    /**
     * Returns the instance for the specified persistance unit name.
     * @param persistenceUnitName the name of the persistence unit.
     * @return the factory instance.
     */
    public static synchronized EntityManagerFactoryImpl getInstance(String persistenceUnitName) {
        if (instance == null) {
            instance = new EntityManagerFactoryImpl(persistenceUnitName);
        }
        else {
            if (!(instance.getPersistenceUnitName().equals(persistenceUnitName))) {
                throw new IllegalArgumentException("Only one persistence unit is currently supported");
            }
        }
        return instance;
    }

    private EntityManagerFactoryImpl(String puName) {
        this.persistenceUnitName = puName;
        jpaFactory = Persistence.createEntityManagerFactory(puName);
    }

    private String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public EntityManager create() {
        return jpaFactory.createEntityManager();
    }

    public javax.persistence.EntityManagerFactory getJpaEntityManagerFactory() {
        return jpaFactory;
    }
}
