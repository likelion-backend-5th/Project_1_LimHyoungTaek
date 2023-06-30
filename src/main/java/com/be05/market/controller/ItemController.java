package com.be05.market.controller;

import com.be05.market.dto.SalesItem;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // TODO: POST /items
    @PostMapping("")
    public SalesItem create(@RequestBody SalesItem items) {
        return itemService.createItem(items);
    }

    // TODO: GET /items?page={page}&limit={limit}
    @GetMapping
    public Page<SalesItem> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        return itemService.readItemsPaged(page, limit);
    }

    // TODO: GET /items/{itemId}
    @GetMapping("/{itemId}")
    public SalesItem readOne(@PathVariable("itemId") Long id) {
        return itemService.read(id);
    }

    // TODO: PUT /items/{itemId}
    @PutMapping("/{itemId}")
    public SalesItem update(@PathVariable("itemId") Long id,
                             @RequestBody SalesItem items) {
        return itemService.updateItem(id, items);
    }

    // TODO: PUT /items/{itemId}/image
    // TODO: DELETE /items/{itemId}
}
