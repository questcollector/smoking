package kr.co.miroirs.smoking.security.service;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.miroirs.smoking.security.dao.UserAuthDao;
import kr.co.miroirs.smoking.security.dto.UserDomain;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserAuthDao userAuthDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> userParamMap = Collections.singletonMap("username", username);
        UserDomain user = userAuthDao.selectUserDomain(userParamMap);
        logger.debug("{}", user);
        
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        return user;
    }
}
