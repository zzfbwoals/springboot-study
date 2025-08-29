package com.fbwoals.shop.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    private String username;
    private String password;
    private String displayName;
}
