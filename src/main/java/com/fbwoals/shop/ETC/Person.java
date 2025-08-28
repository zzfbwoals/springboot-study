package com.fbwoals.shop.ETC;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public Person(String name, int age) {this.name = name; this.age = age;}
    public void addAge() {this.age++;}
    public void setAge(int age) {
        if(age>=100 || age<0) System.out.println("나이가 이상한데?");
        else this.age = age;
    }
}
