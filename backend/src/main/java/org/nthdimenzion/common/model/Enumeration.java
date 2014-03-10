package org.nthdimenzion.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.nthdimenzion.crud.ICrudEntity;

import javax.persistence.*;

@Entity
@Immutable
@Getter

@EqualsAndHashCode(of = "enumCode")
@NamedQueries({@NamedQuery(name = "getEnumerationByType",query = "select enumeration from Enumeration  enumeration " +
                                                                 "where enumType = :enumType"),
               @NamedQuery(name = "getEnumerationByEnumCode",query = "select enumeration from Enumeration  enumeration " +
                       "                                              where enumCode = :enumCode")})
public class Enumeration implements ICrudEntity {

    public static enum EnumerationType {

        STATE;
    }

    @Id
    @GeneratedValue
    private Long id;

    public String description;

    @Column(unique = true)
    private String enumCode;

    @Enumerated(EnumType.STRING)
    private EnumerationType enumType;


    Enumeration() {
    }


    public Enumeration(String description, String enumCode, EnumerationType enumType) {
        this.description = description;
        this.enumCode = enumCode;
        this.enumType = enumType;
    }

    public String toString(){
        return description;
    }
}
