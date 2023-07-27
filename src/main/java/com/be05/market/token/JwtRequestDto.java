package com.be05.market.token;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String userId;
    private String password;
}
