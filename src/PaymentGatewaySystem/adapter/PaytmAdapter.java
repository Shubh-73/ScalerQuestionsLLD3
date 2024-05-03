package PaymentGatewaySystem.adapter;

import PaymentGatewaySystem.library.payTm.PaytmAPI;
import PaymentGatewaySystem.library.payTm.PaytmPaymentResponse;
import PaymentGatewaySystem.models.Payment;
import PaymentGatewaySystem.models.PaymentStatus;

public class PaytmAdapter implements PaymentGatewayAdapter{

    private PaytmAPI paytmAPI;

    public PaytmAdapter(){
        this.paytmAPI = new PaytmAPI();
    }

    @Override
    public Payment makePayment(long billId, double amount){
        PaytmPaymentResponse paytmPaymentResponse = paytmAPI.makePayment(billId, amount);
        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.valueOf(paytmPaymentResponse.getPaymentStatus()));
        payment.setBillId(billId);
        payment.setTransactionId(paytmPaymentResponse.getTransactionId());
        return payment;

    }

}
