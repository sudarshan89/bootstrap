package org.nthdimenzion.ddd.infrastructure.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.nthdimenzion.ddd.infrastructure.IDaoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 18/12/12
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DaoOperations implements IDaoOperations {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public <T> T get(final Class<T> entityClass, final Serializable id) {
        return entityManager.find(entityClass,id);
    }


    @Override
    public void refresh(final Object entity) {
        entityManager.refresh(entity);
    }

    @Override
    public boolean contains(final Object entity) {
        return entityManager.contains(entity);
    }

    @Override
    public void initialize(final Object proxy) {
        Hibernate.initialize(proxy);
    }

    @Override
    public Serializable save(final Object entity) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        session.saveOrUpdate(entity);
        return (Serializable) entity;
    }


    @Override
    public <T> T merge(final T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(final Object entity) {
        entityManager.remove(entity);
    }


    @Override
    public void deleteAll(final Collection entities) {
        for (Object entity : entities) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
