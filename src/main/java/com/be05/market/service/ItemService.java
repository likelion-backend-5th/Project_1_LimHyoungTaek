package com.be05.market.service;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // CREATE
    public void createItem(SalesItem items) {
        ItemEntity newItems = new ItemEntity();
        newItems.setTitle(items.getTitle());
        newItems.setDescription(items.getDescription());
        newItems.setMinPriceWanted(items.getMinPriceWanted());
        newItems.setWriter(items.getWriter());
        newItems.setPassword(items.getPassword());
        newItems.setStatus("판매중");
        SalesItem.fromEntity(itemRepository.save(newItems));
        log.info(String.valueOf(newItems));
    }

    // FindAll(Pages)
    public Page<SalesItem> readItemsPaged(Integer page, Integer limit) {
        Pageable pageable =
                PageRequest.of(page, limit, Sort.by("id").descending());
        Page<ItemEntity> itemEntities = itemRepository.findAll(pageable);
        return itemEntities.map(SalesItem::fromEntity);
    }

    // FindById
    public SalesItem read(Long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        log.info(String.valueOf(optionalItem));
        if (optionalItem.isPresent()) return SalesItem.fromEntity(optionalItem.get());
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Update
    public void updateItem(Long id, SalesItem item) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemEntity itemEntity = optionalItem.get();

            // Check Password
            if (itemEntity.getPassword().equals(item.getPassword())) {
                itemEntity.setTitle(item.getTitle());
                itemEntity.setDescription(item.getDescription());
                itemEntity.setMinPriceWanted(item.getMinPriceWanted());
                itemRepository.save(itemEntity);
            } else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Delete
    public void deleteItem(Long id, SalesItem item) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            ItemEntity itemEntity = optionalItem.get();

            if (itemEntity.getPassword().equals(item.getPassword())) {
                if (itemRepository.existsById(id)) {
                    itemRepository.deleteById(id);
                }
                else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
