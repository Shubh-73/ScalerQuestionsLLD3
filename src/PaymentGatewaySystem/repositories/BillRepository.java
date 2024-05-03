package PaymentGatewaySystem.repositories;

import PaymentGatewaySystem.models.Bill;
import java.util.Optional;

public interface BillRepository {

    Bill save(Bill bill);
    Optional<Bill> findBillById(long id);
}
