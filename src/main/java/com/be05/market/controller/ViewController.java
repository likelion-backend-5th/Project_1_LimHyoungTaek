package com.be05.market.controller;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.SalesItemDto;
import com.be05.market.dto.mapping.CommentPageInfoDto;
import com.be05.market.dto.mapping.ContentInfoDto;
import com.be05.market.dto.mapping.ItemPageInfoDto;
import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.UserEntity;
import com.be05.market.repository.CommentRepository;
import com.be05.market.repository.ItemRepository;
import com.be05.market.repository.UserRepository;
import com.be05.market.service.CommentService;
import com.be05.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ItemService itemService;
    private final CommentService commentService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/items/view";
    }

    @GetMapping("/users/login/view")
    public String login() {
        return "login";
    }

    @GetMapping("/users/register/view")
    public String registerUser() {
        return "register";
    }

    @GetMapping("/items/register/view")
    public String registerItem() {
        return "item_register";
    }

    @GetMapping("/items/view")
    public String itemList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           Model model) {
        Page<ItemPageInfoDto> itemPage = itemService.readItemsPaged(page, limit);
        model.addAttribute("itemEntities", itemPage.getContent());
        model.addAttribute("itemPage", itemPage);
        return "index";
    }

    @GetMapping("/items/view/{itemId}")
    public String readOne(@PathVariable("itemId") Long id,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          Model model) {
        ContentInfoDto itemContent = itemService.read(id);
        Page<CommentPageInfoDto> commentPage = commentService.getCommentsPaged(page, id);
        model.addAttribute("itemEntity", itemContent);
        model.addAttribute("commentEntities", commentPage.getContent());
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("itemId", id);
        return "item_article";
    }

    @PostMapping("/items/view/{itemId}")
    public String postComment(@PathVariable("itemId") Long id,
                              @RequestParam("content") String content,
                              Authentication authentication) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserEntity user = userRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CommentDto dto = new CommentDto();
        dto.setContent(content);
        commentRepository.save(dto.newEntity(item, user));
        return "redirect:/items/view/{itemId}";
    }

    @PostMapping("/items/register/view")
    public String registerItem(@RequestParam("itemTitle") String title,
                               @RequestParam("itemDescription") String description,
                               @RequestParam("itemPrice") Long itemPrice,
                               @RequestParam(value = "itemImage", required = false) MultipartFile itemImage,
                               Authentication authentication) {
        SalesItemDto dto = new SalesItemDto();
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setMinPriceWanted(itemPrice);
        dto.setStatus("판매중");

        if (itemImage != null) {
            UserEntity user = userRepository.findByUserId(authentication.getName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Long itemId = itemRepository.save(dto.newEntity(user)).getId();
            itemService.uploadItemImage(itemId, itemImage, authentication);
        }
        else itemService.createItem(dto, authentication);

        return "redirect:/items/view/";
    }
}
