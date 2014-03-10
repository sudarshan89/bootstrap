package org.nthdimenzion.security.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ValueObject
@Entity
@Immutable
@EqualsAndHashCode(of = "permissionId")
class SecurityPermission {

    @Id
    private Long id;

    @Column(unique = true)
    @Getter(AccessLevel.PACKAGE)
    private String permissionId;
    private String description;

    SecurityPermission() {

    }

    public SecurityPermission(String permissionId,String description) {
        this.permissionId = permissionId;
        this.description = description;
    }



    @Override
    public String toString() {
        return permissionId;
    }

}
