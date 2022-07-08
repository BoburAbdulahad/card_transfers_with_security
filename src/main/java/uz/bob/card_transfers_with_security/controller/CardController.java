package uz.bob.card_transfers_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bob.card_transfers_with_security.entity.Card;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public HttpEntity<?> getAllCards(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "3") int size){
        List<Card> cards = cardService.getCards(page,size);
        return ResponseEntity.ok(cards);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Card card){
        ApiResponse apiResponse = cardService.addCard(card);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


}
