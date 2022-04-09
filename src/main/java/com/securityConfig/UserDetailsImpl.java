
package com.securityConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author samuel
 */
public class UserDetailsImpl implements UserDetails {
    private String userId;
    private String firstName;
    private String secondName;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private boolean locked;

    public UserDetailsImpl(String userId, String firstName, String secondName, String password, Collection<? extends GrantedAuthority> authorities, boolean enabled,boolean locked) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.locked=locked;
    }

    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = user.getUserRoles().stream().map(userRole -> new SimpleGrantedAuthority(userRole.getUserRole().toString())).collect(Collectors.toList());
        return new UserDetailsImpl(user.getUserId(), user.getFirstName(), user.getSecondName(), user.getPassword(), authorities, user.isEnabled(),user.isLocked());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return (!this.locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
