package aggregatedRevenueManagementSystem.exceptions;

import aggregatedRevenueManagementSystem.models.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }

}
