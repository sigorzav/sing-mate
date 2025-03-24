package com.sigorzav.singmate.security;

import com.sigorzav.singmate.user.entity.UserEntity;
import com.sigorzav.singmate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("UserEmailNotFound: " + email));

        return new User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))  // TODO 동적 권한 생성
        );
    }
}
