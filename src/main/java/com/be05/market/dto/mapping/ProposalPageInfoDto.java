package com.be05.market.dto.mapping;

import com.be05.market.entity.ProposalEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposalPageInfoDto {
    private Long id;
    private Long suggestedPrice;
    private String status;

    public static ProposalPageInfoDto fromEntity(ProposalEntity entity) {
        ProposalPageInfoDto infoDto = new ProposalPageInfoDto();
        infoDto.setId(entity.getId());
        infoDto.setSuggestedPrice(entity.getSuggestedPrice());
        infoDto.setStatus(entity.getStatus());
        return infoDto;
    }
}
