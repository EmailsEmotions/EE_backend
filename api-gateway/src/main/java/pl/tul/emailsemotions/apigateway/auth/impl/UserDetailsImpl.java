package pl.tul.emailsemotions.apigateway.auth.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.tul.emailsemotions.api.AccountType;
import pl.tul.emailsemotions.apigateway.auth.models.UserData;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    @Getter@Setter
    private Long id;
    @Getter@Setter
    private String username;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private String email;
    @Getter@Setter
    private Boolean active;
    @Getter@Setter
    private Boolean confirmed;
    @Getter@Setter
    private AccountType accountType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(accountType.toString()));
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public UserDetailsImpl(UserData user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        active = user.isActive();
        confirmed = user.isConfirmed();
        accountType = user.getAccountType();
    }
}
