package com.fbwoals.shop.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> { // JpaRepository<엔티티 명, Id컬럼타입>
    Page<Item> findPageBy(Pageable page); // 페이징 처리를 위한 메소드
}
