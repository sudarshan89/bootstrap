package org.nthdimenzion.crud;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public interface ICrud {

    <E> E  save(E e);
	/**
	 * The remove method. DELETE (DESTROY)
	 * @param e the entity you want to remove from a repository.
	 */
	<E> void remove(E e);

	/**
	 * The find method. READ (RETRIEVE)
	 * @param pk the identification to find the specific entity.
	 * @return the entity that corresponds the informed id.
	 */
	<E> E find(Class clazz, Serializable pk);


    <T extends ICrudEntity> List<T> unify(List<T> list);

    EntityManager getEntityManager();

    public Query createNamedQuery(String name);

}
