package com.cms.service.security;


import com.cms.utils.Type;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.ArrayList;
import java.util.List;


public class SimpleAuthenticationManager implements AuthenticationManager {

    public SimpleAuthenticationManager() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authentication.getDetails();

        if(Type.TEACHER != userDetail.getRole() && Type.STUDENT != userDetail.getRole()){
            throw new BadCredentialsException("Bad Credentials");
        }

        return getAuthority(userDetail, authentication, authenticationDetails);
    }

    private UsernamePasswordAuthenticationToken getAuthority(UserDetail userDetail, Authentication authentication, WebAuthenticationDetails authenticationDetails) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDetail.getRole().getText()));
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDetail,
                authentication.getCredentials(), grantedAuthorities);
        result.setDetails(authenticationDetails);
        return result;
    }
}

