package PaymentGatewaySystem.service;

import PaymentGatewaySystem.exceptions.InvalidBillException;
import PaymentGatewaySystem.models.Payment;

public interface PaymentService {

    Payment makePayment(long billId) throws InvalidBillException;
}
