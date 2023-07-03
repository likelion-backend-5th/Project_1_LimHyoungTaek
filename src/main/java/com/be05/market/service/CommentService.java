package com.be05.market.service;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.SalesItemDto;
import com.be05.market.entity.CommentEntity;
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
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    // Post Comment, Reply?
    public void postComment(Long itemId, CommentDto comments) {
        if (!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity newComment = new CommentEntity();
        newComment.setItemId(itemId);
        newComment.setWriter(comments.getWriter());
        newComment.setPassword(comments.getPassword());
        newComment.setContent(comments.getContent());
        CommentDto.fromEntity(commentRepository.save(newComment));
        log.info(String.valueOf(newComment));
    }

    // View All Comments
    public Page<CommentDto> getCommentsPaged(Long itemId) {
        if (!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Pageable pageable =
                PageRequest.of(0, 25, Sort.by("id"));
        Page<CommentEntity> commentEntities = commentRepository.findAll(pageable);
        return commentEntities.map(CommentDto::fromEntity);
    }

    // Modifying Comment
    public void modifiedComment(Long id, SalesItemDto item) {
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
