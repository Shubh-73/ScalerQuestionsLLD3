package PaymentGatewaySystem.exceptions;

public class InvalidBillException extends RuntimeException{

    public InvalidBillException(String message){
        super(message);
    }
}
