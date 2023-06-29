package com.be05.market.controller;

import com.be05.market.dto.SalesItem;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j @RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @RequestMapping("/")
    public String home(Model model) {
        List<SalesItem> itemList = this.itemService.readAll();
        model.addAttribute("itemList", itemList);
        return "item";
    }
}
