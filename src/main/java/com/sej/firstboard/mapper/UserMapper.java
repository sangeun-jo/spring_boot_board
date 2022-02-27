package com.sej.firstboard.mapper;

import com.sej.firstboard.model.UserDTO;
import com.sej.firstboard.model.UserVO;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository("com.sej.firstboard.mapper.UserMapper")
public interface UserMapper {
    //회원가입
    public void signUp(UserDTO user) throws Exception;
    //id로 회원 조회 
    public UserVO findUserById(String id) throws UsernameNotFoundException; 
    //유저이름 변경
    //public void changeUserName(String userName); 
    //비밀번호 변경  
    //public void changePassword(String oldPass, String newPass); 
    //회원탈퇴 
    //public void deleteUser(String id);
}
