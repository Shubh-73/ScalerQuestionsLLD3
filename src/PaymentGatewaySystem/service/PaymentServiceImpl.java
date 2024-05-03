package PaymentGatewaySystem.service;

import PaymentGatewaySystem.adapter.PaymentGatewayAdapter;
import PaymentGatewaySystem.exceptions.InvalidBillException;
import PaymentGatewaySystem.library.payTm.PaytmPaymentResponse;
import PaymentGatewaySystem.models.Bill;
import PaymentGatewaySystem.models.Payment;
import PaymentGatewaySystem.repositories.BillRepository;

public class PaymentServiceImpl implements PaymentService{

    private BillRepository billRepository;
    private PaymentGatewayAdapter paymentGatewayAdapter;

    PaymentServiceImpl(BillRepository billRepository, PaymentGatewayAdapter paymentGatewayAdapter){
        this.billRepository = billRepository;
        this.paymentGatewayAdapter = paymentGatewayAdapter;
    }

    @Override
    public Payment makePayment(long billId) throws InvalidBillException{

        Bill bill = billRepository.findBillById(billId).orElseThrow(() -> new InvalidBillException("Bill not found"));
        return paymentGatewayAdapter.makePayment(billId, bill.getTotalAmount());

    }
}
