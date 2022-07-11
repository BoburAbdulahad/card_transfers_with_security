package uz.bob.card_transfers_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.bob.card_transfers_with_security.entity.Card;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.payload.LoginDto;
import uz.bob.card_transfers_with_security.payload.TransactionDto;
import uz.bob.card_transfers_with_security.repository.CardRepository;

import java.util.Optional;

@Service
public class TransactionCardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    MyAuthService myAuthService;


    public ApiResponse add(TransactionDto transactionDto) {
        
    }

    public double transferMoney(double inCard,double rate){

    }

    public boolean checkTransfer(double total,double out,double rate){
        return out+ out*rate/100<=total;
    }
}
