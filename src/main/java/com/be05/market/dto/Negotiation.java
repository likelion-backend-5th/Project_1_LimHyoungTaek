package com.be05.market.dto;

import lombok.Data;

@Data
public class Negotiation {
    private Long id;
    private Long itemId;
    private Long suggestedPrice;
    private String status;
    private String writer;
    private String password;
}
