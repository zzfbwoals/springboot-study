package com.fbwoals.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // /add 를 위한 메소드
    public void saveItem(Item item) {
        if (item.getPrice() < 0) {
            throw new RuntimeException("음수 안됩니다");
        }
        else if (item.getTitle() != null && item.getTitle().length() > 50) { // 50자 제한 예시
            throw new RuntimeException("제목이 너무 깁니다 (최대 50자)");
        }
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
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
