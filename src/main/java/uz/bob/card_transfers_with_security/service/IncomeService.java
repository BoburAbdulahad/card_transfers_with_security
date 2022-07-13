package uz.bob.card_transfers_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.bob.card_transfers_with_security.entity.Income;
import uz.bob.card_transfers_with_security.entity.Outcome;
import uz.bob.card_transfers_with_security.payload.ApiResponse;
import uz.bob.card_transfers_with_security.payload.TransactionDto;
import uz.bob.card_transfers_with_security.repository.CardRepository;
import uz.bob.card_transfers_with_security.repository.IncomeRepository;
import uz.bob.card_transfers_with_security.repository.OutcomeRepository;

import java.util.Date;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    CardRepository cardRepository;

    public ApiResponse addIncome(TransactionDto transactionDto){
        if (!cardRepository.existsById(transactionDto.getFromCardId())) {
            return new ApiResponse("From Card not found",false);
        }
        if (!cardRepository.existsById(transactionDto.getToCardId())) {
            return new ApiResponse("To Card not found",false);
        }
        Date date=new Date();
        Income income=new Income(
                null,
                cardRepository.getReferenceById(transactionDto.getFromCardId()),
                cardRepository.getReferenceById(transactionDto.getToCardId()),
                transactionDto.getAmount(),
                date
        );
        incomeRepository.save(income);
        return new ApiResponse("Income saved",true);
    }

    public List<Income> getIncomesByUsername(int page,int size,String name) {
        Pageable pageable= PageRequest.of(page,size);
        List<Income> incomeList = incomeRepository.findAllByToCard_Username(pageable,name);
        return incomeList;
    }
}
