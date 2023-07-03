package com.be05.market.service;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.SalesItemDto;
import com.be05.market.dto.salesitem.PageInfoDto;
import com.be05.market.entity.ItemEntity;
import com.be05.market.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;

    // Post Comment
    public void postComment(CommentDto comments) {
    }

    // View All Comments
    public Page<PageInfoDto> getCommentsPaged(Integer page, Integer limit) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Modifying Comment
    public void modifiedComment(Long id, SalesItemDto item) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Post Reply
    public void postReply(Long id, SalesItemDto item) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // Delete Comment
    public void deleteComment(Long id, SalesItemDto item) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO: 따로 정리해서 가져다 쓰기
//    // Import item entities with ID
//    public ItemEntity getItemById(Long id) {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    // Check Password
//    private void validPW(ItemEntity itemEntity, String password) {
//        if (!itemEntity.getPassword().equals(password)) {
//            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
//        }
//    }
}
