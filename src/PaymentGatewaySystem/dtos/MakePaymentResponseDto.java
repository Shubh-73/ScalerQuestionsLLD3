package PaymentGatewaySystem.dtos;

import PaymentGatewaySystem.dtos.ResponseStatus;
import PaymentGatewaySystem.models.PaymentStatus;

public class MakePaymentResponseDto {

    private ResponseStatus responseStatus;
    private String transactionId;
    private PaymentStatus paymentStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
