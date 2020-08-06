package parser;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/*
 * 从 Lexer 拉取 Token
 *
 * BEGIN
 *   BEGIN
 *     number := 2;
 *     a := number * 10;
 *     b := 10 * a + a / 2;
 *     c := b - a
 *   END;
 *   x := 11;
 * END.
 *
 * program: compound_statement DOT
 * compound_statement: BEGIN statement_list END
 * statement_list: statement (SEMI statement)*
 * statement: compound_statement | assign_statement | empty
 * assign_statement: variable ASSIGN expr
 * variable: ID
 * empty: <nothing>
 *
 * expr: term (PLUS|MINUS term)*
 * term: factor (MUL|DIV factor)*
 * factor: variable | PLUS|MINUS factor | INTEGER | LPAREN expr RPAREN
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
        return program();
    }

    // program: compound_statement DOT
    private AstNode program() {
        AstNode node = compound_statement();
        eat(TokenType.DOT);
        return node;
    }

    // compound_statement: BEGIN statement_list END
    private AstNode compound_statement() {
        eat(TokenType.BEGIN);
        AstNode node = new CompoundStatementNode(statement_list());
        eat(TokenType.END);
        return node;
    }

    // statement_list: statement (SEMI statement)*
    private List<AstNode> statement_list() {
        List<AstNode> result = new ArrayList<>();
        result.add(statement());
        while (currentToken.type == TokenType.SEMI) {
            eat(TokenType.SEMI);
            result.add(statement());
        }
        return result;
    }


    // statement: compound_statement | assign_statement | empty
    private AstNode statement() {
        if (currentToken.type == TokenType.BEGIN) {
            return compound_statement();
        }
        else if (currentToken.type == TokenType.ID) {
            return assign_statement();
        }
        else {
            return empty();
        }
    }

    // assign_statement: variable ASSIGN expr
    private AstNode assign_statement() {
        final AstNode variable = variable();
        eat(TokenType.ASSIGN);
        final AstNode expr = expr();
        return new BinOpNode(variable, Token.ASSIGN, expr);
    }

    // variable: ID
    private AstNode variable() {
        final Token currentToken = this.currentToken;
        eat(TokenType.ID);
        return new VariableNode(currentToken);
    }

    // empty: <nothing>
    private AstNode empty() {
        return NoOpNode.INSTANCE;
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

    // factor: variable | PLUS|MINUS factor | INTEGER | LPAREN expr RPAREN
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
        else if (token.type == TokenType.ID) {
            eat(TokenType.ID);
            return new VariableNode(token);
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
