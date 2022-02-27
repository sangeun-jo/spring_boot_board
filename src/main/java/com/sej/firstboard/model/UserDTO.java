package com.sej.firstboard.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//Mapper <-> Service <-> Controller <-> View 간 User 객체 정보 전달용
/*
비밀번호 변경, 유저이름 변경을 하려면 Setter를 사용해야하는데, 
VO는 Setter를 가질 수 없다. 
*/
@Getter
@Setter
public class UserDTO {
    private String id;
    private String password;
    private String userName;
    private String auth;
    private Date regDate;
    private Date passChange;  
    private boolean activateYN; 
}
