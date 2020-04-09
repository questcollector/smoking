package kr.co.miroirs.smoking.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import kr.co.miroirs.smoking.security.dto.UserDomain;

public interface UserService extends UserDetailsService {

    public Long joinUser(UserDomain user);
    public Long deleteUser(UserDomain user);

}
