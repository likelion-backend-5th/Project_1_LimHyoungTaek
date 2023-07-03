package com.be05.market.service;

import com.be05.market.dto.SalesItemDto;
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

    // CREATE
    public void createItem(SalesItemDto items) {
        ItemEntity newItems = new ItemEntity();
        newItems.setTitle(items.getTitle());
        newItems.setDescription(items.getDescription());
        newItems.setMinPriceWanted(items.getMinPriceWanted());
        newItems.setWriter(items.getWriter());
        newItems.setPassword(items.getPassword());
        newItems.setStatus("판매중");
        SalesItemDto.fromEntity(itemRepository.save(newItems));
        log.info(String.valueOf(newItems));
    }

    // FindAll(Pages)
    public Page<SalesItemDto> readItemsPaged(Integer page, Integer limit) {
        Pageable pageable =
                PageRequest.of(page, limit, Sort.by("id"));
        Page<ItemEntity> itemEntities = itemRepository.findAll(pageable);
        return itemEntities.map(SalesItemDto::fromEntity);
    }

    // FindById
    public SalesItemDto read(Long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        log.info(String.valueOf(optionalItem));
        if (optionalItem.isPresent()) return SalesItemDto.fromEntity(optionalItem.get());
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Update
    public void updateItem(Long id, SalesItemDto item) {
        ItemEntity itemEntity = getItemById(id); // Import item entities with ID
        validPW(itemEntity, item.getPassword()); // Check Password

        itemEntity.setTitle(item.getTitle());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setMinPriceWanted(item.getMinPriceWanted());
        itemRepository.save(itemEntity);

    }

    // Upload Image
    public void uploadItemImage(Long id, String password, MultipartFile itemFile) {
        // 0. Handling Exceptions
        ItemEntity itemEntity = getItemById(id);
        validPW(itemEntity, password);

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
        SalesItemDto.fromEntity(itemRepository.save(itemEntity));
    }

    // Delete
    public void deleteItem(Long id, SalesItemDto item) {
        ItemEntity itemEntity = getItemById(id);
        validPW(itemEntity, item.getPassword());

        if (itemRepository.existsById(id)) itemRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    // Import item entities with ID
    public ItemEntity getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Check Password
    private void validPW(ItemEntity itemEntity, String password) {
        if (!itemEntity.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
