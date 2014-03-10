package org.nthdimenzion.ddd.domain.model;

import com.google.common.base.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Role
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@EqualsAndHashCode(of = "id")
public abstract class PersonRole {

    @Id
    @Setter
    private String id;

    @Embedded
    @Setter
    private Person person;

    @NotNull
    private String domainRole;

    protected PersonRole() {
        // Hibernate
    }

    protected PersonRole(String id, Person person, String domainRole) {
        this.id = id;
        this.person = person;
        this.domainRole = domainRole;
        personalDetails = new PersonalDetails(id,person.getPersonName().getFirstName(),
                person.getPersonName().getMiddleName(), person.getPersonName().getLastName(),
                person.getEmailAddress().getEmail());
    }

    @Transient
    private PersonalDetails personalDetails;

    public PersonalDetails getPersonalDetails() {
        if (personalDetails == null) {
            personalDetails = new PersonalDetails(Objects.firstNonNull(id, StringUtils.EMPTY),person.getPersonName().getFirstName(),
                    person.getPersonName().getMiddleName(), person.getPersonName().getLastName(),
                    person.getEmailAddress().getEmail());
        }
        return personalDetails;
    }


}

