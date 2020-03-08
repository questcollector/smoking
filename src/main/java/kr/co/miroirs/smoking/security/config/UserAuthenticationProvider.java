package kr.co.miroirs.smoking.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import kr.co.miroirs.smoking.security.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        String password = (String) authToken.getCredentials();
        
        UserDetails userInfo = userService.loadUserByUsername(username);
                        
        if (!matchPassword(userInfo.getPassword(), password)) {
            throw new BadCredentialsException("password do not match with username");
        }
        
        if (!userInfo.isEnabled()) {
            throw new BadCredentialsException("this user is not enabled");
        }
                
        return new UsernamePasswordAuthenticationToken(authToken.getPrincipal(), 
                authToken.getCredentials(), userInfo.getAuthorities());
    }
    
    private boolean matchPassword(String password, String credentials) {
        return password.equals(credentials);
    }

    @Override
    public boolean supports(Class<?> authentication) {        
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
