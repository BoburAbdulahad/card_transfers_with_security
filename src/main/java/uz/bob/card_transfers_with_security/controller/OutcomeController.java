package uz.bob.card_transfers_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.bob.card_transfers_with_security.entity.Outcome;
import uz.bob.card_transfers_with_security.service.OutcomeService;

import java.util.List;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/{username}")
    public HttpEntity<?> getOutcomes(@RequestParam int page,@RequestParam int size,@PathVariable String username){
        List<Outcome> list = outcomeService.getOutcomesByUsername(page, size, username);
        return new HttpEntity<>(list);
    }

}
