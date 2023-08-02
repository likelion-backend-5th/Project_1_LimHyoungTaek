package com.be05.market.dto.mapping;

import com.be05.market.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContentInfoDto {
    private String title;
    private String description;
    private Long minPriceWanted;
    private String imageUrl;
    private String status;
    private String user;

    public ContentInfoDto(ItemEntity entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageURL();
        this.minPriceWanted = entity.getMinPriceWanted();
        this.status = entity.getStatus();
        this.user = entity.getUser().getUserId();
    }
}
