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
    OutcomeService outcomeService;
    @Autowired
    IncomeService incomeService;

    public ApiResponse add(TransactionDto transactionDto) {
        Optional<Card> optionalCard1 = cardRepository.findById(transactionDto.getFromCardId());
        if (!optionalCard1.isPresent()) {
            return new ApiResponse("User1 card not found",false);
        }
        Optional<Card> optionalCard2 = cardRepository.findById(transactionDto.getToCardId());
        if (!optionalCard2.isPresent())
            return new ApiResponse("User2 card not found",false);

        Card card1 = optionalCard1.get();
        Card card2 = optionalCard2.get();
        LoginDto loginDto=new LoginDto();
        if (!card1.getUsername().equals(loginDto.getUsername()))
            return new ApiResponse("This card not allowed for this user",false);
        boolean checkTransfer = checkTransfer(card1.getBalance(), transactionDto.getAmount(), 1);
        if (!checkTransfer)
            return new ApiResponse("Card1 balance not available",false);

        double transferMoney = transferMoney(transactionDto.getAmount(), 1);
        double balance1 = card1.getBalance();
        double balance2 = card2.getBalance();

        card1.setBalance(balance1-transferMoney);
        card2.setBalance(balance2+transferMoney);
        cardRepository.save(card1);
        cardRepository.save(card2);

        ApiResponse apiResponse1 = outcomeService.addOutcome(transactionDto);
        ApiResponse apiResponse2 = incomeService.addIncome(transactionDto);

        if (!apiResponse1.isSuccess() || !apiResponse2.isSuccess())
            return new ApiResponse("Outcome or income fail",false);
        return new ApiResponse("Transaction successfully completed",true);
    }

    public double transferMoney(double out,double rate){
        return out*rate/100;
    }

    public boolean checkTransfer(double total,double out,double rate){
        return out+ out*rate/100<=total;
    }
}
