package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.dto.UpdateItemDTO;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 변경 감지 (추천)
    // 머지는 자동 변경 감지 정도지만 문제는 모든 값을 대체하기에
    // null값도 넣는 등 선택적 수정이 불가능 함.
    @Transactional
    public void updateItem(Long itemId, UpdateItemDTO dto) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(dto.getPrice());
        findItem.setName(dto.getName());
        findItem.setStockQuantity(dto.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
