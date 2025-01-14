package com.yossavorn.cards.controller;

import com.yossavorn.cards.constants.CardConstant;
import com.yossavorn.cards.dto.CardContactInfo;
import com.yossavorn.cards.dto.CardDto;
import com.yossavorn.cards.dto.ResponseDto;
import com.yossavorn.cards.service.ICardService;
import com.yossavorn.cards.service.impl.CardServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")

@Validated
public class CardController {

    private CardContactInfo cardContactInfo;
    private ICardService cardService;


    public CardController(CardContactInfo cardContactInfo, ICardService cardService) {
        this.cardContactInfo = cardContactInfo;
        this.cardService = cardService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> createCard(@RequestBody @Valid CardDto cardDto) {
        cardService.createCard(cardDto);
        return ResponseEntity.ok().body(new ResponseDto(CardConstant.STATUS_201, CardConstant.MESSAGE_201));
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CardDto> getOneCard(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        CardDto cardDto = cardService.getOneCard(mobileNumber);
        return ResponseEntity.ok().body(cardDto);

    }

    @PutMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDto> updateCard(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber, @RequestBody @Valid CardDto cardDto) {
        cardService.updateCard(mobileNumber, cardDto);
        return ResponseEntity.ok().body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));

    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteCard(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        cardService.deleteCard(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));

    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfo> getContactInfo() {
        return ResponseEntity.ok().body(cardContactInfo);
    }
}
