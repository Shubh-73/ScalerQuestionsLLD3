package PaymentGatewaySystem.controller;

import PaymentGatewaySystem.dtos.MakePaymentRequestDto;
import PaymentGatewaySystem.dtos.MakePaymentResponseDto;
import PaymentGatewaySystem.dtos.ResponseStatus;
import PaymentGatewaySystem.models.Payment;
import PaymentGatewaySystem.service.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto executePayment(MakePaymentRequestDto makePaymentRequestDto){
        MakePaymentResponseDto responseDto = new MakePaymentResponseDto();
        try{
            Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setPaymentStatus(payment.getPaymentStatus());
            responseDto.setTransactionId(payment.getTransactionId());
            return responseDto;
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
