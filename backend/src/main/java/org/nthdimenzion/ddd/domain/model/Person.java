package org.nthdimenzion.ddd.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.nthdimenzion.common.model.Gender;
import org.nthdimenzion.crud.ICrudEntity;
import org.nthdimenzion.ddd.domain.annotations.PPT;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@PPT
@Embeddable
@Getter
public final class Person implements INamed, ICrudEntity {

    @Embedded
    private PersonName personName;

    private LocalDate dateOfBirth;

    @Embedded
    @Setter
    private EmailAddress emailAddress;

    @Enumerated(EnumType.STRING)
    @Setter
    private Gender gender;

    Person() {
    }

    public Person(PersonName personName, LocalDate dateOfBirth) {
        this.personName = personName;
        this.dateOfBirth = dateOfBirth;
    }


    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @NotNull
    public LocalDate getDateOfBirth() {
        return dateOfBirth != null ? new LocalDate(dateOfBirth) : null;
    }


    @Override
    @Transient
    @JsonIgnore
    public String getName() {
        return personName.toString();
    }

    public Person updateWithPersonName(Person person, PersonName personName) {
        Person newPerson = new Person(personName, person.dateOfBirth);
        newPerson.setEmailAddress(person.getEmailAddress());
        newPerson.setGender(person.gender);
        return newPerson;
    }
}

