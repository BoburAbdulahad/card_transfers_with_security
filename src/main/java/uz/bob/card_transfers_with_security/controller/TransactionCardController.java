package uz.bob.card_transfers_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.payload.TransactionDto;
import uz.bob.card_transfers_with_security.service.TransactionCardService;

@RestController
@RequestMapping("/transaction")
public class TransactionCardController {

    @Autowired
    TransactionCardService transactionCardService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody TransactionDto transactionDto){
        ApiResponse apiResponse = transactionCardService.add(transactionDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
