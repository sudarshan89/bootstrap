package org.nthdimenzion.crud;

import org.nthdimenzion.ddd.infrastructure.IDaoOperations;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class CrudDao implements ICrud {

	private final static Logger logger = LoggerFactory.getLogger(CrudDao.class);
    private IDaoOperations hibernateDaoOperations;

    protected CrudDao(){

    }

    @Autowired
    public CrudDao(IDaoOperations hibernateDaoOperations) {
        this.hibernateDaoOperations = hibernateDaoOperations;
    }

    @Override
    public <E> E  save(E e) {
        hibernateDaoOperations.save(e);
        return e;
    }

    @Override
    @Transactional
	public <E> void remove(E e) {
		hibernateDaoOperations.delete(e);
	}

	@Override
	public <E> E find(Class clazz,Serializable pk) {
		return (E) hibernateDaoOperations.get(clazz,pk);
	}

    @Override
    public <T extends ICrudEntity> List<T> unify(List<T> list) {
	if (UtilValidator.isEmpty(list))
		return list;
	Set<T> unifier = new HashSet<T>();
	unifier.addAll(list);
	return new ArrayList<T>(unifier);
    }

    public EntityManager getEntityManager(){
        return hibernateDaoOperations.getEntityManager();
    }

    @Override
    public Query createNamedQuery(String name) {
        return getEntityManager().createNamedQuery(name);
    }


}