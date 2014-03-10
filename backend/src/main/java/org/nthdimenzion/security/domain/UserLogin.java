package org.nthdimenzion.security.domain;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.nthdimenzion.crud.ICrudEntity;
import org.nthdimenzion.ddd.domain.model.PersonRole;
import org.nthdimenzion.ddd.domain.model.PersonalDetails;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.HomePageViews;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@ToString(of = "username")
@EqualsAndHashCode(of = "username")
@Getter(AccessLevel.PACKAGE)
@NamedQuery(name = "findUserLoginByUserName",query = "from UserLogin where username = :username ")
public class UserLogin implements ICrudEntity {

    public static Long ACTIVATOR_ID = 2L;

    @Getter(AccessLevel.NONE)
    private transient Integer MAX_INCORRECT_LOGIN_ATTEMPTS = Integer.valueOf(2);

    @Id
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SecurityGroup> securityGroups = Sets.newHashSet();

    private Boolean isEnabled = Boolean.FALSE;

    private Boolean isAccountNonLocked = Boolean.TRUE;

    @OneToOne
    @Getter
    private PersonRole personRole;

    @Enumerated(value = EnumType.STRING)
    @Getter
    private HomePageViews homePageView;

    private Integer numberOfFailedLoginAttempts = Integer.valueOf(0);

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate validUptoDate;

    @Getter
    @Setter
    private Boolean isEmailVerified = Boolean.FALSE;

    protected UserLogin() {
        numberOfFailedLoginAttempts = 0;
    }

    public UserLogin(String userName, String password, String id) {
        this.username = userName;
        this.password = password;
        this.id = id;
    }

    public UserLogin add(SecurityGroup securityGroup) {
        this.securityGroups.add(securityGroup);
        return this;
    }

    public UserLogin updateSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroups.clear();
        this.securityGroups.add(securityGroup);
        return this;
    }

    public UserLogin addAll(Set<SecurityGroup> securityGroups) {
        if (UtilValidator.isNotEmpty(securityGroups)) {
            for (SecurityGroup securityGroup : securityGroups) {
                add(securityGroup);
            }
        }
        return this;
    }

    public void disableLogin() {
        this.isEnabled = Boolean.FALSE;
    }

    public UserLogin attachRole(PersonRole role) {
        this.personRole = role;
        return this;
    }

    public UserLogin homePage(HomePageViews homePageView) {
        this.homePageView = homePageView;
        return this;
    }

    public UserLogin changePassword(String password) {
        this.password = password;
        return this;
    }

    public void failedLoginAttempt() {
        numberOfFailedLoginAttempts = numberOfFailedLoginAttempts == null ? 0 : numberOfFailedLoginAttempts;
        numberOfFailedLoginAttempts = numberOfFailedLoginAttempts + 1;
        if (numberOfFailedLoginAttempts > MAX_INCORRECT_LOGIN_ATTEMPTS) {
            isAccountNonLocked = Boolean.FALSE;
        }
    }

    public void successfullLogin() {
        enableUserLogin();
    }

    public void enableUserLogin() {
        numberOfFailedLoginAttempts = 0;
        isEnabled = Boolean.TRUE;
        isAccountNonLocked = Boolean.TRUE;
    }

    public void unLockUserAccount() {
        enableUserLogin();

    }

    public PersonalDetails getUserInfo() {
        return personRole.getPersonalDetails();
    }

    public boolean isPasswordTheSame(String newPassword) {
        return password.equals(newPassword);
    }

    public boolean isSameUserNameAndPassword(String username, String password){
        return this.username.equals(username) && isPasswordTheSame(password);
    }

    public Optional<PersonRole> getRolePlayed(){
        return Optional.fromNullable(personRole);
    }

    /**
     * @return
     */
    public Optional<String> getRole(){
        if(personRole==null)
            return Optional.absent();
        else
            return Optional.of(personRole.getDomainRole());
    }


    public boolean isValid(){
        return isValid(LocalDate.now());
    }

    boolean isValid(LocalDate date){
        if(validUptoDate== null || validUptoDate.isAfter(date)){
            return true;
        }else {
            return false;
        }
    }

    @Transient
    public boolean isEnabled() {
        return isEnabled;
    }

    @Transient
    public boolean  isEmailNotVerified(){
        return !isEmailVerified;
    }

    public List<String> getAllPermissions(){
        final Set<String> permissions = Sets.newHashSet();
        for(SecurityGroup securityGroup : securityGroups){
            permissions.addAll(securityGroup.getAllPermissions());
        }
        return Lists.newArrayList(permissions);
    }
}



