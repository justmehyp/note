package lexer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Token {
    public TokenType type;
    public String value;

    public static final Token EOF = new Token(TokenType.EOF, null);
}
