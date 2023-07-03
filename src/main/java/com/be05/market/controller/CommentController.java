package com.be05.market.controller;

import com.be05.market.dto.CommentDto;
import com.be05.market.dto.ResponseDto;
import com.be05.market.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ResponseDto responseDto = new ResponseDto();

    // TODO: POST /items/{itemId}/comments
    @PostMapping("/comments")
    public ResponseDto create(@PathVariable("itemId") Long itemId,
                             @RequestBody CommentDto commentDto) {
        commentService.postComment(itemId, commentDto);
        responseDto.setMessage("댓글이 등록되었습니다.");
        return responseDto;
    }
    // TODO: GET /items/{itemId}/comments
    @GetMapping("/comments")
    public Page<CommentDto> readAllReply(@PathVariable("itemId") Long itemId) {
        return commentService.getCommentsPaged(itemId);
    }

    // TODO: PUT /items/{itemId}/comments/{commentId}
    // TODO: PUT /items/{itemId}/comments/{commentId}/reply
    // TODO: DELETE /items/{itemId}/comments/{commentId}
}
