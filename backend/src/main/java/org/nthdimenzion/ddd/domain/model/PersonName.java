package org.nthdimenzion.ddd.domain.model;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Embeddable;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 1/8/13
 * Time: 3:30 PM
 */
@Embeddable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Immutable
@ToString
public class PersonName {

    private String firstName;

    private String middleName;

    private String lastName;


    PersonName() {
    }

    public PersonName(String firstName, String lastName) {
        Preconditions.checkNotNull(firstName);
        Preconditions.checkNotNull(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonName withFirstName(String firstName){
        return new PersonName(firstName,middleName,lastName);
    }


    public PersonName withMiddleName(String middleName){
        return new PersonName(firstName,middleName,lastName);
    }


    public PersonName withLastName(String lastName){
        return new PersonName(firstName,middleName,lastName);
    }
}
