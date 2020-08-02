package parser;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;

/*
 * 从 Lexer 拉取 Token
 *
 * expr: term (PLUS|MINUS term)*
 * term: factor (MUL|DIV factor)*
 * factor: PLUS|MINUS factor | INTEGER | LPAREN expr RPAREN
 *
 * terminal 是一个Token?
 */
public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    public AstNode parse() {
        return expr();
    }

    // expr: term (PLUS|MINUS term)*
    private AstNode expr() {
        AstNode node = term();
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            Token op = currentToken;
            if (op.type == TokenType.PLUS) {
                eat(TokenType.PLUS);
            }
            else {
                eat(TokenType.MINUS);
            }
            node = new BinOpNode(node, op, term());
        }
        return node;
    }

    // term: factor (MUL|DIV factor)*
    private AstNode term() {
        AstNode node = factor();
        while (currentToken.type == TokenType.MUL || currentToken.type == TokenType.DIV) {
            Token op = currentToken;
            if (op.type == TokenType.MUL) {
                eat(TokenType.MUL);
            }
            else {
                eat(TokenType.DIV);
            }
            node = new BinOpNode(node, op, factor());
        }
        return node;
    }

    // factor: PLUS|MINUS factor | INTEGER | LPAREN expr RPAREN
    private AstNode factor() {
        Token token = currentToken;
        if (token.type == TokenType.INTEGER) {
            eat(TokenType.INTEGER);
            return new IntegerNode(token);
        }
        else if (token.type == TokenType.LPAREN) {
            eat(TokenType.LPAREN);
            AstNode node = expr();
            eat(TokenType.RPAREN);
            return node;
        }
        else if (token.type == TokenType.PLUS || token.type == TokenType.MINUS) {
            Token op = currentToken;
            if (TokenType.PLUS == op.type) {
                eat(TokenType.PLUS);
            }
            else {
                eat(TokenType.MINUS);
            }
            return new UnaryOpNode(op, factor());
        }
        return null;
    }

    private void eat(TokenType tokenType) {
        if (currentToken.type == tokenType) {
            currentToken = lexer.getNextToken();
        }
        else {
            throw new RuntimeException("Expected token type: " + tokenType + ", current token type: " + currentToken.type);
        }
    }
}
