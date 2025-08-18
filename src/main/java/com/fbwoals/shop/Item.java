package com.fbwoals.shop;

import jakarta.persistence.*;

@Entity // 이 애너테이션만 넣으면 JPA 엔터티 (DB 테이블) 완성
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 값 (Primary Key), 자동 증가 (Auto Increment) 설정
    private Long id; // private 사용하고 Lombok 의 @Getter/@Setter 사용 @Setter는 유효성 검사를 위해 롬복대신 직접 함수 만들어서 검사하는 코드 추가하면 좋음
    // Integer/int 는 대충 +-20억, Long/long 은 대충 +-900경 대신 Long/long 사용 시에는 100L 이런식으로 숫자 뒤에 L 추가
    @Column(length = 200) // 제약사항 입력 (최대 200 문자)
    private String title;
    private Integer price;
}

// @Column(nullable = false) 널값 허용 X
// @Column(unique = true) 중복 허용 X
// @Column(columnDefinition = "TEXT") MySQL 의 TEXT 데이터 타입 같은거 -> 매우 긴 문자 저장 가능
