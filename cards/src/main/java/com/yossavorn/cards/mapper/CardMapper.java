package com.yossavorn.cards.mapper;

import com.yossavorn.cards.dto.CardDto;
import com.yossavorn.cards.entity.Cards;

public class CardMapper {

    public static Cards mapCardDtoToCard(CardDto cardDto, Cards cards) {

        cards.setMobileNumber(cardDto.getMobileNumber());
        cards.setCardNumber(cardDto.getCardNumber());
        cards.setCardType(cardDto.getCardType());
        cards.setTotalLimit(cardDto.getTotalLimit());
        cards.setAmountUsed(cardDto.getAmountUsed());
        cards.setAvailableAmount(cardDto.getAvailableAmount());
        return cards;
    }

    public static CardDto mapCardToCardDto(Cards cards, CardDto cardDto) {

        cardDto.setMobileNumber(cards.getMobileNumber());
        cardDto.setCardNumber(cards.getCardNumber());
        cardDto.setCardType(cards.getCardType());
        cardDto.setTotalLimit(cards.getTotalLimit());
        cardDto.setAmountUsed(cards.getAmountUsed());
        cardDto.setAvailableAmount(cards.getAvailableAmount());
        return cardDto;
    }

}
