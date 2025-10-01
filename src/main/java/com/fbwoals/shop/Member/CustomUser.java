package com.fbwoals.shop.Member;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class CustomUser extends User {

    public String displayName;

    public CustomUser(String username,
                      String password,
                      List<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}