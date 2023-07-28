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
    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;
    private String password;
    private UserEntity user;

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
//        salesItem.setUser(UserDto.fromEntity(entity.getUser()));
        return salesItem;
    }

    public ItemEntity newEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setMinPriceWanted(minPriceWanted);
        entity.setWriter(writer);
        entity.setPassword(password);
        entity.setStatus("판매중");
        entity.setUser(user);
        return entity;
    }
}
