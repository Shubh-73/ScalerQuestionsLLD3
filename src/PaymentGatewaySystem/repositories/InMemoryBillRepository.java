package PaymentGatewaySystem.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import PaymentGatewaySystem.models.Bill;


public class InMemoryBillRepository implements BillRepository{

    private Map<Long, Bill> billMap;
    private static int idCount = 0;

    public InMemoryBillRepository(){
        this.billMap = new HashMap<>();
    }

    @Override
    public Bill save(Bill bill){
        if(bill.getId() == 0){
            bill.setId(++idCount);
        }
        billMap.put(bill.getId(), bill);
        return bill;
    }

    @Override
    public Optional<Bill> findBillById(long id){
        return Optional.ofNullable(billMap.get(id));
    }


}
