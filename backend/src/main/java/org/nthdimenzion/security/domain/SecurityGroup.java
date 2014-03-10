package org.nthdimenzion.security.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.Getter;
import org.nthdimenzion.crud.ICrudEntity;
import org.nthdimenzion.object.utils.UtilValidator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "findSecurityGroupByName",query = "from  SecurityGroup where name = :name")
public class SecurityGroup implements ICrudEntity{

    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SecurityPermission> securityPermissions = Sets.newHashSet();

    @Column(unique = true)
    @Getter(AccessLevel.PACKAGE)
    private String name;


    protected SecurityGroup() {
    }


    public SecurityGroup add(SecurityPermission securityPermission) {
        this.securityPermissions.add(securityPermission);
        return this;
    }

    public SecurityGroup addAll(Set<SecurityPermission> securityPermissions) {
        if (UtilValidator.isNotEmpty(securityPermissions)) {
            for (SecurityPermission securityPermission : securityPermissions) {
                add(securityPermission);
            }
        }
        return this;
    }

    public List<String> getAllPermissions(){
        final List<String> permissions = Lists.newArrayList();
        for(SecurityPermission securityPermission : securityPermissions){
            permissions.add(securityPermission.getPermissionId());
        }
        return permissions;

    }


    @Override
    public String toString() {
        return name;
    }
}
