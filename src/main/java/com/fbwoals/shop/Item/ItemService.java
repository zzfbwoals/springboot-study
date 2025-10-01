package com.fbwoals.shop.Item;

import com.fbwoals.shop.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    // /add 를 위한 메소드
    public void saveItem(Item item, Authentication auth) {
        if (item.getPrice() < 0) {
            throw new RuntimeException("음수 안됩니다");
        }
        else if (item.getTitle() != null && item.getTitle().length() > 50) { // 50자 제한 예시
            throw new RuntimeException("제목이 너무 깁니다 (최대 50자)");
        }
        // 나쁜 유저인 경우에 대해서 예외 처리
        if(memberRepository.findById(auth.getName()).isEmpty()) {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
        item.setUser(auth.getName()); // 현재 로그인한 유저의 아이디를 저장
        itemRepository.save(item);
    }

    // /list 를 위한 메소드
    public List<Item> getItemFindAll() {

        return itemRepository.findAll();
    }

    // /detail/{id}, /edit/{id} 를 위한 메소드
    public Optional<Item> getItemById(Long id) {

        return itemRepository.findById(id);
    }

    // /delete 를 위한 메소드
    public boolean deleteItemById(Long id, Authentication auth) {
        var result = itemRepository.findById(id);
        if(result.isPresent()) {
            Item item = result.get();
            if(item.getUser() != null && item.getUser().equals(auth.getName())) {
                itemRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}
