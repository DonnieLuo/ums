package com.service;

import com.Entity.User;
import com.repository.LogRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;

/**
 * Created by donnie on 17-2-28.
 */
@Service
public class UserDetailService implements UserDetailsService {
    public UserDetailService () {}
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserDetails userDetails = new UserDetail(user);
        return userDetails;
    }
    private final static class UserDetail extends User implements UserDetails {
        private static final long serialVersionUID = 1L;
        private UserDetail (User user) { super(user); }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
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

        @Override
        public String getUsername() {
            return super.getUsername();
        }

        @Override
        public String getPassword() {
            return super.getPassword();
        }
    }
}
