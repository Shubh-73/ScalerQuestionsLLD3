package PaymentGatewaySystem.library.payTm;

import java.util.Date;
import java.util.UUID;

public class PaytmAPI {

    public PaytmPaymentResponse makePayment(long orderId, double amount){
        PaytmPaymentResponse paytmPaymentResponse = new PaytmPaymentResponse();
        paytmPaymentResponse.setOrderId(Long.toString(orderId));
        paytmPaymentResponse.setTaxAmount(amount);
        paytmPaymentResponse.setPaymentStatus("SUCCESS");
        paytmPaymentResponse.setTransactionDate(new Date());
        paytmPaymentResponse.setTransactionId(UUID.randomUUID().toString());
        return paytmPaymentResponse;
    }
}

//there is a class which is making a payment and then it is returning the response
//response generally consists of details like if it was a success and corresponding details.

