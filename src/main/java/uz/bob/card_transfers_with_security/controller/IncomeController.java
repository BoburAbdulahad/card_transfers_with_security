package uz.bob.card_transfers_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.bob.card_transfers_with_security.entity.Income;
import uz.bob.card_transfers_with_security.service.IncomeService;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping("/{username}")
    public HttpEntity<?> getIncomes(@RequestParam int page, @RequestParam int size, @PathVariable(value = "username") String name){
        List<Income> incomes = incomeService.getIncomesByUsername(page, size, name);
        return new HttpEntity<>(incomes);
    }
}
