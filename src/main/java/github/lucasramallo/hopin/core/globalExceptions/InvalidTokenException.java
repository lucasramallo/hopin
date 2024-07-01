package github.lucasramallo.hopin.core.globalExceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid token!");
    }
}
