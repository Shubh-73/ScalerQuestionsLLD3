package PaymentGatewaySystem.adapter;

import PaymentGatewaySystem.models.Payment;

public interface PaymentGatewayAdapter {

    Payment makePayment(long billId, double amount);
}
