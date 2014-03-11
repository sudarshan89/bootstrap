package org.nthdimenzion.ddd.domain.model;

import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.Entity;

/**
 * Sample Entity can be deleted, required to test the DomainRole Functionality
 * Author: Nthdimenzion
 */

@Entity
@Role
public class Admin extends PersonRole {
}
