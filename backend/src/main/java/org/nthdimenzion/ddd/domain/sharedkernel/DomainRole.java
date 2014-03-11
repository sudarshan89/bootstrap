package org.nthdimenzion.ddd.domain.sharedkernel;

/**
 * Created with IntelliJ IDEA.
 * User: Samir
 * Date: 9/10/13
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public enum DomainRole {

    ADMIN("org.nthdimenzion.ddd.domain.model.Admin");

    private String clazz;

    private DomainRole(String clazz) {
        this.clazz = clazz;
    }

    public String getDomainClass() {
        return clazz;
    }

}
