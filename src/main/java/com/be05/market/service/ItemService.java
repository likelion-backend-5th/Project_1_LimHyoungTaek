package com.be05.market.service;

import com.be05.market.dto.mapping.ContentInfoDto;
import com.be05.market.dto.mapping.ItemPageInfoDto;
import com.be05.market.dto.SalesItemDto;
import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.Role;
import com.be05.market.entity.UserEntity;
import com.be05.market.repository.ItemRepository;
import com.be05.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    // CREATE
    public void createItem(SalesItemDto items, Authentication authentication) {
        UserEntity userEntity = getUserEntity(authentication);
        itemRepository.save(items.newEntity(userEntity));
    }

    // FindAll(Pages)
    public Page<ItemPageInfoDto> readItemsPaged(Integer page, Integer limit) {
        Pageable pageable =
                PageRequest.of(page, limit, Sort.by("id"));
        Page<ItemEntity> itemEntities = itemRepository.findAll(pageable);
        return itemEntities.map(ItemPageInfoDto::fromEntity);
    }

    // FindById
    public ContentInfoDto read(Long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        log.info(String.valueOf(optionalItem));
        if (optionalItem.isPresent()) return new ContentInfoDto(optionalItem.get());
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Update
    public void updateItem(Long id, SalesItemDto item, Authentication authentication) {
        ItemEntity itemEntity = getItemById(id); // Import item entities with ID
        UserEntity userEntity = getUserEntity(authentication);
        if (userEntity.getRole() == Role.ROLE_USER)
            itemEntity.validatePassword(userEntity.getPassword()); // Check Password

        itemRepository.save(item.updateEntity(itemEntity));

    }

    // Upload Image
    public void uploadItemImage(Long id, MultipartFile itemFile,
                                Authentication authentication) {
        // 0. Handling Exceptions
        ItemEntity itemEntity = getItemById(id);
        UserEntity userEntity = getUserEntity(authentication);
        if (userEntity.getRole() == Role.ROLE_USER)
            itemEntity.validatePassword(userEntity.getPassword());

        // 1. create folder
        String itemDirPath = String.format("images/%d/", id);
        log.info(itemDirPath);
        try {
            Files.createDirectories(Path.of(itemDirPath));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // pfpName(Img Name + Extension) -> pfpPath(itemDirPath + pfpName)
        String originalFilename = itemFile.getOriginalFilename();
        assert originalFilename != null;
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        String pfpName = "image." + extension;
        log.info(pfpName);

        String pfpPath = itemDirPath + pfpName;
        log.info(pfpPath);

        // 2. Save MultipartFile
        try {
            itemFile.transferTo(Path.of(pfpPath));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 3. Set ImageURL
        log.info(String.format("/static/%d/%s", id, pfpName));
        itemEntity.setImageURL(String.format("/static/%d/%s", id, pfpName));
        itemRepository.save(itemEntity);
    }

    // Delete
    public void deleteItem(Long id, Authentication authentication) {
        ItemEntity itemEntity = getItemById(id);
        UserEntity userEntity = getUserEntity(authentication);
        if (userEntity.getRole() == Role.ROLE_USER)
            itemEntity.validatePassword(userEntity.getPassword());
        if (itemEntity.getStatus().equals("판매 완료")) // 판매 완료인데 지우려고 할 경우
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (itemRepository.existsById(id)) itemRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    // Find User
    private UserEntity getUserEntity(Authentication authentication) {
        return userRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Import item entities with ID
    public ItemEntity getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
