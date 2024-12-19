package exceptions;

public class WrongCustomerDataException extends RuntimeException {
    public WrongCustomerDataException(String message) {
        super(message);
    }
}
