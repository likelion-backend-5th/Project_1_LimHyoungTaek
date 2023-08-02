package com.be05.market.dto.mapping;

import com.be05.market.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentPageInfoDto {
    private Long id;
    private String content;
    private String reply;
    private String user;

    public static CommentPageInfoDto fromEntity(CommentEntity entity) {
        CommentPageInfoDto infoDto = new CommentPageInfoDto();
        infoDto.setId(entity.getId());
        infoDto.setContent(entity.getContent());
        infoDto.setReply(entity.getReply());
        infoDto.setUser(entity.getUser().getUserId());
        return infoDto;
    }
}
