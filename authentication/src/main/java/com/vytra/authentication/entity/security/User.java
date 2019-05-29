package com.vytra.authentication.entity.security;

import com.vytra.authentication.entity.BaseEntity;
import com.vytra.authentication.entity.parametrics.DocumentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_name",unique = true)
    @NotNull
    @Setter
    private String username;


    @Column(name = "password")
    @NotNull
    @Setter
    private String password;
    @Column(name = "name")
    @Setter @Getter
    private String name;

    @Column(name = "last_name")
    @Setter @Getter
    private String lastName;


    @Column(name = "email")
    @Email
    @NotNull
    @Setter @Getter
    private String email;
    @Column(name = "account_locked")
    @Setter
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    @Setter
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    @Setter
    private boolean credentialsNonExpired;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    @Setter @Getter
    private DocumentType documentType;

    @Column(name = "document_number",unique = true)
    @NotNull
    @Setter @Getter
    private String documentNumber;



    @ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "ROLE_USER", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") })
    @Getter
    @Setter
    @OrderBy
    private Set<Roles> rolesSet = new HashSet<>();




   /*
     * Get roles and permissions and add them as a Set of GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        rolesSet.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
            r.getPermissionSet().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName())));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }


    @Override
    public boolean isEnabled(){
        return getEnabled();
    }


}
