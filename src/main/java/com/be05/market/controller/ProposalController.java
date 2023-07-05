package com.be05.market.controller;

import com.be05.market.dto.NegotiationDto;
import com.be05.market.dto.ResponseDto;
import com.be05.market.dto.mapping.ProposalPageInfoDto;
import com.be05.market.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}/proposal")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;
    private final ResponseDto responseDto = new ResponseDto();

    // TODO: POST /items/{itemId}/proposal
    @PostMapping
    public ResponseDto createProposal(@PathVariable("itemId") Long itemId,
                                      @RequestBody NegotiationDto negotiationDto) {
        proposalService.postOffer(itemId, negotiationDto);
        responseDto.setMessage("구매 제안이 등록되었습니다.");
        return responseDto;
    }

    // TODO: GET /items/{itemId}/proposal?writer=Lim123&password=qwerty1234&page=1
    @GetMapping
    public Page<ProposalPageInfoDto> readAllProposal(@PathVariable("itemId") Long itemId,
                                                     @RequestParam(value = "writer") String writer,
                                                     @RequestParam(value = "password") String password,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return proposalService.findPagedOffer(itemId, writer, password, page);
    }

    // TODO: PUT /items/{itemId}/proposal/{proposalId}
    @PutMapping("/{proposalId}")
    public ResponseDto updateProposal(@PathVariable("itemId") Long itemId,
                                      @PathVariable("proposalId") Long proposalId,
                                      @RequestBody NegotiationDto negotiationDto) {
        return proposalService.putUpdateOffer(proposalId, itemId, negotiationDto);
    }

    // TODO: DELETE /items/{itemId}/proposal/{proposalId}
    @DeleteMapping("/{proposalId}")
    public ResponseDto delete(@PathVariable("itemId") Long itemId,
                              @PathVariable("proposalId") Long proposalId,
                              @RequestBody NegotiationDto negotiationDto) {
        proposalService.deleteOffer(proposalId, itemId, negotiationDto);
        responseDto.setMessage("제안을 삭제했습니다.");
        return responseDto;
    }
}
