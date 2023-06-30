package com.be05.market.dto;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long itemId;
    private String writer;
    private String password;
    private String content;
    private String reply;
}
