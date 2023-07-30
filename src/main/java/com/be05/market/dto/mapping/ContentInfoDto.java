package com.be05.market.dto.mapping;

import com.be05.market.dto.SalesItemDto;
import com.be05.market.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContentInfoDto {
    private String title;
    private String description;
    private Long minPriceWanted;
    private String status;

    public ContentInfoDto(ItemEntity entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.minPriceWanted = entity.getMinPriceWanted();
        this.status = entity.getStatus();
    }
}
