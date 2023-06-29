package com.be05.market.service;

import com.be05.market.dto.SalesItem;
import com.be05.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final List<SalesItem> itemList = new ArrayList<>();

    public List<SalesItem> readAll() {
        return itemList;
    }

}
