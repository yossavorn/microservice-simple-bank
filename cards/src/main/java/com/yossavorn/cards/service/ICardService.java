package com.yossavorn.cards.service;

import com.yossavorn.cards.dto.CardDto;

public interface ICardService {

     void createCard(CardDto cardDto);

     CardDto getOneCard(String mobileNumber);

     void updateCard(String mobileNumber, CardDto cardDto);

     void deleteCard(String mobileNumber);
}
