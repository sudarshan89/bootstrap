package org.nthdimenzion.common.service;

import org.nthdimenzion.common.model.Enumeration;
import org.nthdimenzion.ddd.infrastructure.IDaoOperations;
import org.nthdimenzion.object.utils.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 3/8/13
 * Time: 3:04 PM
 */
@Service
public class EnumerationService {

    private IDaoOperations hibernateDaoOperations;

    @Autowired
    public EnumerationService(IDaoOperations hibernateDaoOperations) {
        this.hibernateDaoOperations = hibernateDaoOperations;
    }

    public List<Enumeration> getEnumerationByEnumType(String enumType){
        Enumeration.EnumerationType enumerationTypeSelected = Enumeration.EnumerationType.valueOf(enumType);
        EntityManager entityManager = hibernateDaoOperations.getEntityManager();
        return entityManager.createNamedQuery("getEnumerationByType",Enumeration.class).setParameter("enumType",
                enumerationTypeSelected).getResultList();

    }

    public Enumeration getEnumerationByEnumCode(String enumCode){
        if(UtilValidator.isEmpty(enumCode))
            return null;
        EntityManager entityManager = hibernateDaoOperations.getEntityManager();
        return entityManager.createNamedQuery("getEnumerationByEnumCode",Enumeration.class).setParameter("enumCode",
                enumCode).getSingleResult();

    }
}
