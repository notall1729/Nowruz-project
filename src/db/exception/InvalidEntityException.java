package db.exception;

public class InvalidEntityException extends Exception {
    public InvalidEntityException(String massage){
        super(massage);
    }
}
