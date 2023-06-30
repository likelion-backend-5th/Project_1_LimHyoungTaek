package com.be05.market.controller;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/")
    public String addProduct() {
        return "addproduct";
    }

    @GetMapping("/items")
    public String home(Model model) {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        model.addAttribute("itemList", itemEntityList);
        return "item";
    }

    @PostMapping("/items")
    public String item(@RequestParam("writer") String writer,
                       @RequestParam("password") String password,
                       @RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("price") Long price) {
        itemService.createProduct(writer, password, title, description, price);
        log.info("등록이 완료되었습니다.");
        return "redirect:/items";
    }

//    @GetMapping("/items?page={page}&limit={limit}")
//    @GetMapping("/{itemId}")
//    @PutMapping("/{itemId}")
//    @PutMapping("/{itemId}/image")
//    @DeleteMapping("/{itemId}")
}
