package com.sej.firstboard.service;

import javax.annotation.Resource;

import com.sej.firstboard.mapper.UserMapper;
import com.sej.firstboard.model.UserDTO;
import com.sej.firstboard.model.UserVO;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.stereotype.Service;


@Service("com.sej.firstboard.service.UserService")
public class UserService implements UserDetailsService{

    @Resource(name="com.sej.firstboard.mapper.UserMapper")
    UserMapper mUserMapper; 

    public void signUp(UserDTO user) throws Exception {
        //60자리 문자열로 비번 암호화 
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
        user.setPassword(encoder.encode(user.getPassword())); 
        //아이디 중복 확인 
        //아이디 중복일 시 에러 띄우기
        mUserMapper.signUp(user);
    }; 

    
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserVO user = mUserMapper.findUserById(id);
        return user; 
    }
    
}
