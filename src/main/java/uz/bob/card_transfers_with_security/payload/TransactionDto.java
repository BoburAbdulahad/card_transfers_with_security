package uz.bob.card_transfers_with_security.payload;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

@Data
public class TransactionDto {

    private Integer fromCardId;
    private Integer toCardId;
    private double amount;

}
