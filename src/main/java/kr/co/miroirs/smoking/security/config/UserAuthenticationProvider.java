package kr.co.miroirs.smoking.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.co.miroirs.smoking.security.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    // private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        String password = (String) authToken.getCredentials();
        
        try {
            UserDetails userInfo = userService.loadUserByUsername(username);
            
            matchPassword(userInfo.getPassword(), password);
            isEnableUser(userInfo);
            
            return new UsernamePasswordAuthenticationToken(authToken.getPrincipal(), 
                    authToken.getCredentials(), userInfo.getAuthorities());
        } catch(UsernameNotFoundException e) {
            throw new BadCredentialsException("there's no user with written username");
        }
    }
    
    private void matchPassword(String password, String credentials) {        
        if (!password.equals(credentials)) {
            throw new BadCredentialsException("password do not match with username");
        }
    }
    
    private void isEnableUser(UserDetails userInfo) {
        if (!userInfo.isEnabled()) {
            throw new BadCredentialsException("this user is not enabled");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
