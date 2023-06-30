package com.be05.market.service;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public SalesItem createItem(SalesItem items) {
        ItemEntity newItems = new ItemEntity();
        newItems.setTitle(items.getTitle());
        newItems.setDescription(items.getDescription());
        newItems.setMinPriceWanted(items.getMinPriceWanted());
        newItems.setWriter(items.getWriter());
        newItems.setPassword(items.getPassword());
        newItems.setStatus("판매중");
        return SalesItem.fromEntity(itemRepository.save(newItems));
    }

    public Page<SalesItem> readItemsPaged(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<ItemEntity> itemEntities = itemRepository.findAll(pageable);
        return itemEntities.map(SalesItem::fromEntity);
    }

}
