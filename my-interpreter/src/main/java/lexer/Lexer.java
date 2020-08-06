package lexer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 输入字符流，输出Token
 */
public class Lexer {
    private String text;
    private int pos = -1;
    private Character currentChar = null;

    private static final Map<String, Token> KEY_WORD_TOKEN_MAP = new HashMap<>();
    static {
        KEY_WORD_TOKEN_MAP.put("BEGIN", new Token(TokenType.BEGIN, "BEGIN"));
        KEY_WORD_TOKEN_MAP.put("END", new Token(TokenType.END, "EN"));
    }

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
                    return Token.PLUS;
                }
                if (currentChar == '-') {
                    advance();
                    return Token.MINUS;
                }
                if (currentChar == '*') {
                    advance();
                    return Token.MUL;
                }
                if (currentChar == '/') {
                    advance();
                    return Token.DIV;
                }
                if (currentChar == '(') {
                    advance();
                    return Token.LPAREN;
                }
                if (currentChar == ')') {
                    advance();
                    return Token.RPAREN;
                }
                if (currentChar == '.') {
                    advance();
                    return Token.DOT;
                }
                if (currentChar == ';') {
                    advance();
                    return Token.SEMI;
                }
                if (currentChar == ':' && peekIfExpected('=')) {
                    advance();
                    advance();
                    return Token.ASSIGN;
                }
                if (isAlpha(currentChar)) {
                    return keywordOrID();
                }

                throw new UnrecognizedTokenException(currentChar);
            }
        }
        return Token.EOF;
    }

    private boolean isAlpha(Character aChar) {
        return aChar != null && (('A' <= aChar && aChar <= 'Z' ) || ('a' <= aChar && aChar <= 'z'));
    }

    private boolean isDigit(Character aChar) {
        return aChar != null && "0123456789".indexOf(aChar) >= 0;
    }

    private Token keywordOrID() {
        StringBuilder sb = new StringBuilder();
        while (isAlpha(currentChar)) {
            sb.append((char) currentChar);
            advance();
        }
        final String word = sb.toString();
        return Optional.ofNullable(KEY_WORD_TOKEN_MAP.get(word))
                .orElse(new Token(TokenType.ID, word));
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

    private boolean peekIfExpected(char expectedChar) {
        int peekPos = pos + 1;
        return peekPos < text.length() && text.charAt(peekPos) == expectedChar;
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
