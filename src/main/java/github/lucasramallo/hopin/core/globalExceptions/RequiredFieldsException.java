package github.lucasramallo.hopin.core.globalExceptions;

public class RequiredFieldsException extends RuntimeException {
    public RequiredFieldsException(String message) {
        super(message);
    }
}
