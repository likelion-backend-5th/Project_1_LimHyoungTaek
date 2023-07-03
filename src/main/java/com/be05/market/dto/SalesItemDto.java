package com.be05.market.dto;

import com.be05.market.entity.ItemEntity;
import lombok.Data;

@Data
public class SalesItemDto {
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private Long minPriceWanted;
    private String status;
    private String writer;
    private String password;

    public static SalesItemDto fromEntity(ItemEntity entity) {
        SalesItemDto salesItem = new SalesItemDto();
        salesItem.setId(entity.getId());
        salesItem.setTitle(entity.getTitle());
        salesItem.setDescription(entity.getDescription());
        salesItem.setImageURL(entity.getImageURL());
        salesItem.setMinPriceWanted(entity.getMinPriceWanted());
        salesItem.setStatus(entity.getStatus());
        salesItem.setWriter(entity.getWriter());
        salesItem.setPassword(entity.getPassword());
        return salesItem;
    }
}
