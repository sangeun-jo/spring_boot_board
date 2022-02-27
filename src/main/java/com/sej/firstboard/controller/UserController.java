package com.sej.firstboard.controller;

import javax.annotation.Resource;

import com.sej.firstboard.model.UserDTO;
import com.sej.firstboard.model.UserVO;
import com.sej.firstboard.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Resource(name="com.sej.firstboard.service.UserService")
    UserService mUserService; 

    @GetMapping("/signup")
    public String signUpFrom() {
        return "auth/signup"; 
    }

    @PostMapping("/signup")
    public String signupProc(UserDTO user) throws Exception{
        //TODO: 패스워드 확인 필드 가져오기
        //TODO: 패스워드 확인 작업하기
        mUserService.signUp(user);
        return "redirect:/"; 
    }

    @GetMapping("/login")
    public String loginFrom() {
        return "auth/login"; 
    }
}
