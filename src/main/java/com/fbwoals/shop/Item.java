package com.fbwoals.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 이 애너테이션만 넣으면 JPA 엔터티 (DB 테이블) 완성
@ToString // Object 를 출력할때 .title 이런거 안붙이면 이상한 값 나오는데 .toString() 으로 모든 속성 값이 나오도록 해줌
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 값 (Primary Key), 자동 증가 (Auto Increment) 설정
    private Long id; // private 사용하고 Lombok 의 @Getter/@Setter 사용 @Setter는 유효성 검사를 위해 롬복대신 직접 함수 만들어서 검사하는 코드 추가하면 좋음
    // Integer/int 는 대충 +-20억, Long/long 은 대충 +-900경 대신 Long/long 사용 시에는 100L 이런식으로 숫자 뒤에 L 추가
    @Column(length = 200) // 제약사항 입력 (최대 200 문자)
    private String title;
    private Integer price;

//    아래 처럼 setter는 롬복 사용대신 직접 함수로 선언해야 안전함. @Setter 는 별도의 검사 로직이 없고 바로 저장해버림.
//    public void setTitle(String title) {
//        if (title == null || title.length() == 0) {this.title = title;}
//    }
}

// @Column(nullable = false) 널값 허용 X
// @Column(unique = true) 중복 허용 X
// @Column(columnDefinition = "TEXT") MySQL 의 TEXT 데이터 타입 같은거 -> 매우 긴 문자 저장 가능

// 테이블에서 데이터 가져오는 법
// 1. repository 생성
// 2. DB 입출력 원하는 클래스에서 repository 빈 생성
// 3. DB 입출력 문법 사용