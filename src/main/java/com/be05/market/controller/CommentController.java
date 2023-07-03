package com.be05.market.controller;

import com.be05.market.dto.ResponseDto;
import com.be05.market.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ResponseDto responseDto = new ResponseDto();

    // TODO: POST /items/{itemId}/comments
    // TODO: GET /items/{itemId}/comments
    // TODO: PUT /items/{itemId}/comments/{commentId}
    // TODO: PUT /items/{itemId}/comments/{commentId}/reply
    // TODO: DELETE /items/{itemId}/comments/{commentId}
}
