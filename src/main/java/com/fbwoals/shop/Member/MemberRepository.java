package com.fbwoals.shop.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUsername(String username); // 행을 못 찾을 수도 있으니 Optional로 선언 findAllBy는 List에 담는게 좋음
}
