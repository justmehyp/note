package lexer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Token {
    public TokenType type;
    public String value;

    public static final Token EOF = new Token(TokenType.EOF, null);
    public static final Token PLUS = new Token(TokenType.PLUS, "+");
    public static final Token MINUS = new Token(TokenType.MINUS, "-");
    public static final Token MUL = new Token(TokenType.MUL, "*");
    public static final Token DIV = new Token(TokenType.DIV, "/");
    public static final Token LPAREN = new Token(TokenType.LPAREN, "(");
    public static final Token RPAREN = new Token(TokenType.RPAREN, ")");
    public static final Token DOT = new Token(TokenType.DOT, ".");
    public static final Token ASSIGN = new Token(TokenType.ASSIGN, ":=");
    public static final Token SEMI = new Token(TokenType.SEMI, ";");
}
