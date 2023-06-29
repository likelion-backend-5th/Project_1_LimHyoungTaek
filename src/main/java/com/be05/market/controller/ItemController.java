package com.be05.market.controller;

import com.be05.market.dto.SalesItem;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.ItemRepository;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j @Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String item(Model model) {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        model.addAttribute("itemList", itemEntityList);
        return "item";
    }
}
