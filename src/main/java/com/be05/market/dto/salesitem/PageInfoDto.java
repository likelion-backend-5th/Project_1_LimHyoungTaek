package com.be05.market.dto.salesitem;

import com.be05.market.entity.ItemEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageInfoDto {
    private Long id;
    private String title;
    private String description;
    private Long minPriceWanted;
    private String imageUrl;
    private String status;

    public static PageInfoDto fromEntity(ItemEntity entity) {
        PageInfoDto infoDto = new PageInfoDto();
        infoDto.setId(entity.getId());
        infoDto.setTitle(entity.getTitle());
        infoDto.setDescription(entity.getDescription());
        infoDto.setMinPriceWanted(entity.getMinPriceWanted());
        infoDto.setImageUrl(entity.getImageURL());
        infoDto.setStatus(entity.getStatus());
        return infoDto;
    }
}
