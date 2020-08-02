package lexer;

public class UnrecognizedTokenException extends RuntimeException {
    public UnrecognizedTokenException(char token) {
        super(token + "");
    }
}
