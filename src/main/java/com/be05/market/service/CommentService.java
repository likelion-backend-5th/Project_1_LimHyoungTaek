package com.be05.market.service;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.SalesItemDto;
import com.be05.market.entity.CommentEntity;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
    public void modifiedComment(Long commentId, Long itemId, CommentDto comments) {
        // 요청 댓글 존재 유무
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        // 존재 X -> exception
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 대상 댓글 == 대상 게시글의 댓글인지
        CommentEntity comment = optionalComment.get();
        if (!itemId.equals(comment.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        comment.setContent(comments.getContent());
        CommentDto.fromEntity(commentRepository.save(comment));
    }

    // Modifying Reply
    public void modifiedReply(Long commentId, Long itemId, CommentDto comments) {
        // 요청 댓글 존재 유무
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        // 존재 X -> exception
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 대상 댓글 == 대상 게시글의 댓글인지
        CommentEntity comment = optionalComment.get();
        if (!itemId.equals(comment.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        comment.setReply(comments.getReply());
        CommentDto.fromEntity(commentRepository.save(comment));
    }

    // Delete Comment
    public void deleteComment(Long commentId, Long itemId) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity comment = optionalComment.get();
        if (!itemId.equals(comment.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(commentId);
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
