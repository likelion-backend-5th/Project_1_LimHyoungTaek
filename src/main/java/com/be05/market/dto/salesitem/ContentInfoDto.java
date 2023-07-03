package com.be05.market.dto.salesitem;

import com.be05.market.dto.SalesItemDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContentInfoDto {
    private String title;
    private String description;
    private Long minPriceWanted;
    private String status;

    public ContentInfoDto(SalesItemDto items) {
        this.title = items.getTitle();
        this.description = items.getDescription();
        this.minPriceWanted = items.getMinPriceWanted();
        this.status = items.getStatus();
    }
}
