package uz.bob.card_transfers_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.card_transfers_with_security.entity.Card;
import uz.bob.card_transfers_with_security.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Integer> {

}
