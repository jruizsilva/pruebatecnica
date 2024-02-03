package paygoal.pruebatecnica.exceptions;

public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException() {
        super();
    }

    public CustomEntityNotFoundException(String message) {
        super(message);
    }
}
