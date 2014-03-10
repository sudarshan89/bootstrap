package org.nthdimenzion.ddd.infrastructure.stubs;


import org.nthdimenzion.ddd.infrastructure.IDaoOperations;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 13/8/13
 * Time: 9:35 AM
 */
@Component
public class StubDaoOperations implements IDaoOperations {
    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void refresh(Object entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(Object entity) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void initialize(Object proxy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Serializable save(Object entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T merge(T entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Object entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAll(Collection entities) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void flush() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EntityManager getEntityManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
