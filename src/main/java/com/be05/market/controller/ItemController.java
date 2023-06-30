package com.be05.market.controller;

import com.be05.market.dto.ResponseDto;
import com.be05.market.dto.SalesItem;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ResponseDto responseDto = new ResponseDto();

    // TODO: POST /items
    @PostMapping("")
    public ResponseDto create(@RequestBody SalesItem items) {
        itemService.createItem(items);
        responseDto.setMessage("등록이 완료되었습니다.");
        return responseDto;
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
    public ResponseDto update(@PathVariable Long itemId,
                             @RequestBody SalesItem items) {
        itemService.updateItem(itemId, items);
        responseDto.setMessage("물품이 수정되었습니다.");
        return responseDto;
    }

    // TODO: PUT /items/{itemId}/image

    // TODO: DELETE /items/{itemId}
    @DeleteMapping("/{itemId}")
    public ResponseDto delete(@PathVariable Long itemId,
                              @RequestBody SalesItem items) {
        itemService.deleteItem(itemId, items);
        responseDto.setMessage("물품을 삭제했습니다.");
        return responseDto;
    }
}
