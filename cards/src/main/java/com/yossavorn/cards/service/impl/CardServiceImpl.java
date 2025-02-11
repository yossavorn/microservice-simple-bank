package com.yossavorn.cards.service.impl;

import com.yossavorn.cards.dto.CardDto;
import com.yossavorn.cards.entity.Cards;
import com.yossavorn.cards.exception.DuplicateRecordException;
import com.yossavorn.cards.exception.NotFoundException;
import com.yossavorn.cards.mapper.CardMapper;
import com.yossavorn.cards.repository.CardRepository;
import com.yossavorn.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;

    @Override
    public void createCard(CardDto cardDto) {
        Optional<Cards> existedCard = cardRepository.findByMobileNumber(cardDto.getMobileNumber());

        if(existedCard.isPresent()) {
            throw new DuplicateRecordException("Card with mobile number " + cardDto.getMobileNumber() + " already exists.");
        }

        Cards cardsToSave = CardMapper.mapCardDtoToCard(cardDto, new Cards());


        cardRepository.save(cardsToSave);

    }

    @Override
    public CardDto getOneCard(String mobileNumber) {

        Cards card = findOneCardByMobileNumber(mobileNumber);
        return CardMapper.mapCardToCardDto(card, new CardDto());
    }

    @Override
    public void updateCard(String mobileNumber, CardDto cardDto) {
    Cards card = findOneCardByMobileNumber(mobileNumber);

    Cards cardsToSave = CardMapper.mapCardDtoToCard(cardDto, card);
    cardRepository.save(cardsToSave);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        Cards card = findOneCardByMobileNumber(mobileNumber);
        //Cards[] cards = new Cards[]{card};
       // cardRepository.deleteAll(List.of(cards));
        cardRepository.delete(card);
    }

    private Cards findOneCardByMobileNumber(String mobileNumber) {
        Optional<Cards> cards = cardRepository.findByMobileNumber(mobileNumber);

        if(cards.isEmpty()) {
            throw new NotFoundException("Card", "mobile number", mobileNumber);
        }
        return cards.get();

    }
}
