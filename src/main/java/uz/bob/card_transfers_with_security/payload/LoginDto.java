package uz.bob.card_transfers_with_security.payload;

import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;
}
