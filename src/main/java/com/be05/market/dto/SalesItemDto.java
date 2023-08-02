package com.be05.market.dto;

import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesItemDto {
    private Long id;
    @NotNull(message = "제목을 작성해주세요.")
    private String title;
    @NotNull(message = "등록한 물품에 대한 설명을 작성해주세요.")
    private String description;
    private String imageURL;
    @NotNull(message = "등록한 물품에 대한 최소 가격을 작성해주세요.")
    private Long minPriceWanted;
    private String status;

    public ItemEntity newEntity(UserEntity user) {
        ItemEntity entity = new ItemEntity();
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setMinPriceWanted(minPriceWanted);
        entity.setStatus("판매중");
        entity.setUser(user);
        return entity;
    }

    public ItemEntity updateEntity(ItemEntity itemEntity) {
        itemEntity.setTitle(title);
        itemEntity.setDescription(description);
        itemEntity.setMinPriceWanted(minPriceWanted);
        return itemEntity;
    }
}
