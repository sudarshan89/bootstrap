package org.nthdimenzion.ddd.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 25/8/13
 * Time: 10:01 PM
 */

@AllArgsConstructor
@NoArgsConstructor
public class PersonalDetails implements Serializable{

    public String id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String email;
}
