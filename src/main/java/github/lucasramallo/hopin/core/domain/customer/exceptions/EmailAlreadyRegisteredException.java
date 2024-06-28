package github.lucasramallo.hopin.core.domain.customer.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException() {
        super("Email already registered!");
    }
}
