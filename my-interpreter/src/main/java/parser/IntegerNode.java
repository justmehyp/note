package parser;

import lexer.Token;

public class IntegerNode extends AstNode {
    public Token token;
    public int value;

    public IntegerNode(Token token) {
        this.token = token;
        this.value = Integer.parseInt(token.value);
    }
}
