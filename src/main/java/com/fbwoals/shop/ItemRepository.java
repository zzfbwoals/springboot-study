package com.fbwoals.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> { // JpaRepository<엔티티 명, Id컬럼타입>
}
