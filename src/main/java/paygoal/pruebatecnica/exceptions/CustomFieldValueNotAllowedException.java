package paygoal.pruebatecnica.exceptions;

public class CustomFieldValueNotAllowedException extends RuntimeException {
    public CustomFieldValueNotAllowedException() {
        super();
    }

    public CustomFieldValueNotAllowedException(String message) {
        super(message);
    }
}
