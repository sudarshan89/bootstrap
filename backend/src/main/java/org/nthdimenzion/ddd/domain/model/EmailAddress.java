package org.nthdimenzion.ddd.domain.model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Embeddable;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 4/8/13
 * Time: 7:00 PM
 */
@Embeddable
@Immutable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class EmailAddress {

    @Email
    private String email;


}
