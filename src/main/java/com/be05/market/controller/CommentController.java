package com.be05.market.controller;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.ResponseDto;
import com.be05.market.dto.mapping.CommentPageInfoDto;
import com.be05.market.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ResponseDto responseDto = new ResponseDto();

    // TODO: POST /items/{itemId}/comments
    @PostMapping("/comments")
    public ResponseDto createComment(@PathVariable("itemId") Long itemId,
                                     @RequestBody CommentDto commentDto,
                                     Authentication authentication) {
        commentService.postComment(itemId, commentDto, authentication);
        responseDto.setMessage("댓글이 등록되었습니다.");
        return responseDto;
    }
    // TODO: GET /items/{itemId}/comments
    @GetMapping("/comments")
    public Page<CommentPageInfoDto> readAllComment(@PathVariable("itemId") Long itemId) {
        return commentService.getCommentsPaged(itemId);
    }

    // TODO: PUT /items/{itemId}/comments/{commentId}
    @PutMapping("/comments/{commentId}")
    public ResponseDto updateComment(@PathVariable("commentId") Long commentId,
                                     @PathVariable("itemId") Long itemId,
                                     @RequestBody CommentDto commentDto,
                                     Authentication authentication) {
        commentService.modifiedComment(commentId, itemId, commentDto, authentication);
        responseDto.setMessage("댓글이 수정되었습니다.");
        return responseDto;
    }

    // TODO: PUT /items/{itemId}/comments/{commentId}/reply
    @PutMapping("/comments/{commentId}/reply")
    public ResponseDto updateReply(@PathVariable("commentId") Long commentId,
                                   @PathVariable("itemId") Long itemId,
                                   @RequestBody CommentDto commentDto,
                                   Authentication authentication) {
        commentService.modifiedReply(commentId, itemId, commentDto, authentication);
        responseDto.setMessage("댓글에 답변이 추가되었습니다.");
        return responseDto;
    }

    // TODO: DELETE /items/{itemId}/comments/{commentId}
    @DeleteMapping("/comments/{commentId}")
    public ResponseDto delete(@PathVariable("commentId") Long commentId,
                              @PathVariable("itemId") Long itemId,
                              Authentication authentication) {
        commentService.deleteComment(commentId, itemId, authentication);
        responseDto.setMessage("댓글을 삭제했습니다.");
        return responseDto;
    }
}
