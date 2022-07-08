package uz.bob.card_transfers_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.bob.card_transfers_with_security.entity.Card;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.repository.CardRepository;

import java.util.List;
@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;


    public List<Card> getCards(int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Card> cardPage = cardRepository.findAll(pageable);
        return cardPage.getContent();

    }

    public ApiResponse addCard(Card card) {
        Card card1=new Card();
        boolean b = cardRepository.existsByUsernameAndNumber(card.getUsername(), card.getNumber());
        if (b)
            return new ApiResponse("This is card already exist",false);
        card1.setUsername(card.getUsername());
        card1.setNumber(card.getNumber());
        card1.setBalance(card.getBalance());
        card1.setExpireDate(card.getExpireDate());
        cardRepository.save(card1);
        return new ApiResponse("Card saved",true);
    }
}
