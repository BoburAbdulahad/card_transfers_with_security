package uz.bob.card_transfers_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.card_transfers_with_security.entity.Outcome;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.payload.TransactionDto;
import uz.bob.card_transfers_with_security.repository.CardRepository;
import uz.bob.card_transfers_with_security.repository.IncomeRepository;
import uz.bob.card_transfers_with_security.repository.OutcomeRepository;

import java.util.Date;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    CardRepository cardRepository;

    String commission_amount="1%";
    public ApiResponse addOutcome(TransactionDto transactionDto){
        if (!cardRepository.existsById(transactionDto.getFromCardId())) {
            return new ApiResponse("From Card not found",false);
        }
        if (!cardRepository.existsById(transactionDto.getToCardId())) {
            return new ApiResponse("To Card not found",false);
        }
        Date date=new Date();
        Outcome outcome=new Outcome(
                null,
                cardRepository.getReferenceById(transactionDto.getFromCardId()),
                cardRepository.getReferenceById(transactionDto.getToCardId()),
                transactionDto.getAmount(),
                date,
                commission_amount
        );
        outcomeRepository.save(outcome);
        return new ApiResponse("Outcome saved",true);
    }

}
