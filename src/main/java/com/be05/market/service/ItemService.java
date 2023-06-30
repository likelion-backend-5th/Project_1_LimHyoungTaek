package com.be05.market.service;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class ItemService {
    private Long nextId = 1L;
    private final List<ItemEntity> itemEntityList;
    private final ItemRepository itemRepository;

    public void createProduct(
            String writer, String password, String title, String description, Long price) {
        ItemEntity newProduct = new ItemEntity(
                nextId, title, description, "null",
                price, "판매중", writer, password);
        nextId++;
        itemRepository.save(newProduct);
    }
}
