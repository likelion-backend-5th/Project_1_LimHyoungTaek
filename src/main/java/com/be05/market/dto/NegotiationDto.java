package com.be05.market.dto;

import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.ProposalEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NegotiationDto {
    private Long id;
    @NotNull(message = "제안 가격을 작성해주세요.")
    private Long suggestedPrice;
    private String status;
    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;
    private String password;

    public static NegotiationDto fromEntity(ProposalEntity entity) {
        NegotiationDto negotiationDto = new NegotiationDto();
        negotiationDto.setId(entity.getId());
        negotiationDto.setSuggestedPrice(entity.getSuggestedPrice());
        negotiationDto.setStatus(entity.getStatus());
        negotiationDto.setWriter(entity.getWriter());
        negotiationDto.setPassword(entity.getPassword());
        return negotiationDto;
    }

    public ProposalEntity newEntity(ItemEntity item) {
        ProposalEntity entity = new ProposalEntity();
        entity.setItem(item);
        entity.setSuggestedPrice(suggestedPrice);
        entity.setWriter(writer);
        entity.setPassword(password);
        entity.setStatus("제안");
        return entity;
    }
}
