package exceptions;

public class WrongEmailFormatException extends RuntimeException{
    public WrongEmailFormatException(String message){
        super(message);
    }
}
