package com.be05.market.service;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.mapping.CommentPageInfoDto;
import com.be05.market.entity.CommentEntity;
import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.Role;
import com.be05.market.entity.UserEntity;
import com.be05.market.repository.CommentRepository;
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
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ItemService itemService;


    // Post Comment
    public void postComment(Long itemId, CommentDto comments, Authentication authentication) {
        // 해당 게시글이 존재하는지
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserEntity userEntity = getUserEntity(authentication);
        commentRepository.save(comments.newEntity(itemEntity, userEntity));
    }

    // View All Comments
    public Page<CommentPageInfoDto> getCommentsPaged(Integer page, Long itemId) {
        existById(itemId);
        Pageable pageable =
                PageRequest.of(page, 10, Sort.by("id"));
        Page<CommentEntity> commentEntities = commentRepository.findAll(pageable);
        return commentEntities.map(CommentPageInfoDto::fromEntity);
    }

    // Modifying Comment
    public void modifiedComment(Long commentId, Long itemId,
                                CommentDto comments, Authentication authentication) {
        CommentEntity commentEntity = validateCommentByItemId(commentId, itemId);
        UserEntity userEntity = getUserEntity(authentication);
        if (userEntity.getRole() == Role.ROLE_USER)
            commentEntity.validatePassword(userEntity.getPassword());
        commentEntity.setContent(comments.getContent());
        commentRepository.save(commentEntity);
    }

    // Post, Modifying Reply
    public void modifiedReply(Long commentId, Long itemId,
                              CommentDto comments, Authentication authentication)
    {
        CommentEntity commentEntity = validateCommentByItemId(commentId, itemId);
        ItemEntity itemEntity = itemService.getItemById(itemId);
        UserEntity userEntity = getUserEntity(authentication);

        if (userEntity.getRole() == Role.ROLE_USER) {
            // 1. 답글 작성자 != 물품 등록 작성자 -> 예외 처리
            // 댓글에 답글을 달 수 있는 사용자는 물품 정보를 등록한 사용자 뿐
            if(!itemEntity.getUser().getUserId().equals(authentication.getName()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            // 2. 물품 등록 작성자 == 답글 작성자 라는건 위의 예외에서 증명
            // 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우
            // -> 물품 등록 == 댓글 == 답글 다 같은 작성자이다.
            if (commentEntity.getUser().getUserId().equals(authentication.getName())){
                // 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
                // 물품 등록 비밀번호 != 답글 비밀번호 -> 예외 처리
                itemEntity.validatePassword(userEntity.getPassword());
            }
        }

        commentEntity.setReply(comments.getReply());
        commentRepository.save(commentEntity);
    }

    // Delete Comment
    public void deleteComment(Long commentId, Long itemId, Authentication authentication) {
        CommentEntity commentEntity = validateCommentByItemId(commentId, itemId);
        UserEntity userEntity = getUserEntity(authentication);
        if (userEntity.getRole() == Role.ROLE_USER)
            commentEntity.validatePassword(userEntity.getPassword());
        commentRepository.deleteById(commentId);
    }

    // Find User
    private UserEntity getUserEntity(Authentication authentication) {
        return userRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // 해당 게시글이 존재하는지
    public void existById(Long itemId) {
        if (!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // 요청 댓글 유무, 대상 댓글이 대상 게시글의 댓글인지 확인
    public CommentEntity validateCommentByItemId(Long commentId, Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!itemId.equals(itemEntity.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return commentEntity;
    }
}
