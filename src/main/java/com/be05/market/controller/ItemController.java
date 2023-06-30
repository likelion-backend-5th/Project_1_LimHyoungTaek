package com.be05.market.controller;

import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // TODO: POST /items
    // TODO: GET /items?page={page}&limit={limit}
    // TODO: GET /items/{itemId}
    // TODO: PUT /items/{itemId}
    // TODO: PUT /items/{itemId}/image
    // TODO: DELETE /items/{itemId}
}
