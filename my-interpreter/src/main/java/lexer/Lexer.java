package lexer;

/**
 * 输入字符流，输出Token
 */
public class Lexer {
    private String text;
    private int pos = -1;
    private Character currentChar = null;

    public Lexer(String text) {
        this.text = text;
        if (text != null && !text.isEmpty()) {
            this.pos = 0;
            this.currentChar = text.charAt(this.pos);
        }
    }

    public Token getNextToken() {
        if (currentChar != null) {
            if (isWhiteSpace(currentChar)) {
                skipWhiteSpace();
            }

            if (currentChar != null) {
                if (isDigit(currentChar)) {
                    final String value = integer();
                    return new Token(TokenType.INTEGER, value);
                }
                if (currentChar == '+') {
                    advance();
                    return new Token(TokenType.PLUS, "+");
                }
                if (currentChar == '-') {
                    advance();
                    return new Token(TokenType.MINUS, "-");
                }
                if (currentChar == '*') {
                    advance();
                    return new Token(TokenType.MUL, "*");
                }
                if (currentChar == '/') {
                    advance();
                    return new Token(TokenType.DIV, "/");
                }
                if (currentChar == '(') {
                    advance();
                    return new Token(TokenType.LPAREN, "(");
                }
                if (currentChar == ')') {
                    advance();
                    return new Token(TokenType.RPAREN, ")");
                }

                throw new UnrecognizedTokenException(currentChar);
            }
        }
        return Token.EOF;
    }

    private boolean isDigit(Character aChar) {
        return aChar != null && "0123456789".indexOf(aChar) >= 0;
    }

    private String integer() {
        StringBuilder sb = new StringBuilder();
        while (isDigit(currentChar)) {
            sb.append((char)currentChar);
            advance();
        }
        return sb.toString();
    }

    private void advance() {
        pos++;
        currentChar = pos >= text.length() ? null : text.charAt(pos);
    }


    private void skipWhiteSpace() {
        while (isWhiteSpace(currentChar)) {
            advance();
        }
    }

    private boolean isWhiteSpace(char aChar) {
        return aChar == ' ';
    }

}
