package com.sej.firstboard.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Getter;

//UserDetails 상속을 위한 VO 
/* UserDetails를 상속 하면 필수 오버라이드 메소드를 구현해야하는데, 
DTO는 비지니스 로직을 가질 수 없다. 
따라서 비지니스 로직을 가질 수 있는 VO를 사용한다. 
*/
@Getter
public class UserVO implements UserDetails {
    private String id;
    private String password;
    private String userName;
    private String auth;
    private Date regDate;
    private Date passChange;  
    private boolean activateYN; 

    
    //사용자의 권한이 , 로 구분되어있는 auth를 콜렉션 형태로 반환시킴 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>(); 
        for(String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role)); 
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password; 
    }

    //계정 만료 여부 반환 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부 반환 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        //3개월 이상 비밀번호 변경 안하면 false로 바꾸기
        return true;
    }

    //계정 활성화 여부 
    @Override
    public boolean isEnabled() {
        return true;
    } 
    
}


