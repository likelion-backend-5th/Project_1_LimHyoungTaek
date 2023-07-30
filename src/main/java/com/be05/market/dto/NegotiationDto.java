package com.be05.market.dto;

import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.ProposalEntity;
import com.be05.market.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NegotiationDto {
    private Long id;
    @NotNull(message = "제안 가격을 작성해주세요.")
    private Long suggestedPrice;
    private String status;

    public ProposalEntity newEntity(ItemEntity item, UserEntity userEntity) {
        ProposalEntity entity = new ProposalEntity();
        entity.setItem(item);
        entity.setSuggestedPrice(suggestedPrice);
        entity.setStatus("제안");
        entity.setUser(userEntity);
        return entity;
    }
}
