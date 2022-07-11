package uz.bob.card_transfers_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.card_transfers_with_security.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    boolean existsByUsernameAndNumber(String username, String number);
}
