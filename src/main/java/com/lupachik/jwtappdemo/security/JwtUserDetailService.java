package com.lupachik.jwtappdemo.security;

import com.lupachik.jwtappdemo.model.User;
import com.lupachik.jwtappdemo.security.jwt.JwtUser;
import com.lupachik.jwtappdemo.security.jwt.JwtUserFactory;
import com.lupachik.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);

        if(user == null){
            throw new UsernameNotFoundException("User with login: " + login + " is not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with login {} successfully load", login);
        return jwtUser;
    }
}
