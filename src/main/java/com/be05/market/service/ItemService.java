package com.be05.market.service;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    public SalesItem read(Long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) return SalesItem.fromEntity(optionalItem.get());
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public SalesItem updateItem(Long id, SalesItem item) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemEntity itemEntity = optionalItem.get();
            itemEntity.setTitle(item.getTitle());
            itemEntity.setDescription(item.getDescription());
            itemEntity.setMinPriceWanted(item.getMinPriceWanted());
            itemRepository.save(itemEntity);
            return SalesItem.fromEntity(itemEntity);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public void deleteItem(Long id) {
        if (itemRepository.existsById(id))
            itemRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
