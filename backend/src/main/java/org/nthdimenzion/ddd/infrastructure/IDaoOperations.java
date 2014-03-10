package org.nthdimenzion.ddd.infrastructure;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 2/6/13
 * Time: 4:05 PM
 */
public interface IDaoOperations {
    <T> T get(Class<T> entityClass, Serializable id);

    void refresh(Object entity);

    boolean contains(Object entity);

    void initialize(Object proxy);

    Serializable save(Object entity);

    <T> T merge(T entity);

    void delete(Object entity);

    void deleteAll(Collection entities);

    void flush();

    void clear();

    EntityManager getEntityManager();

}
