package posmotriKa.security.details;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import posmotriKa.models.User;

import java.util.Collection;
import java.util.Collections;

@Builder
public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = user.getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return user.getHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return true;
    }

    public Long getUserId() {
        return user.getId();
    }
}

