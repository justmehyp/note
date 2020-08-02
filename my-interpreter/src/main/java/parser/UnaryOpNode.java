package parser;

import lexer.Token;

public class UnaryOpNode extends AstNode {
    public Token token;
    public Token op;
    public AstNode child;

    public UnaryOpNode(Token op, AstNode child) {
        this.token = op;
        this.op = op;
        this.child = child;
    }
}
