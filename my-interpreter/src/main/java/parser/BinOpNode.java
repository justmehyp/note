package parser;

import lexer.Token;
public class BinOpNode extends AstNode {
    public Token token;
    public AstNode left;
    public Token op;
    public AstNode right;

    public BinOpNode(AstNode left, Token op, AstNode right) {
        this.token = op;
        this.left = left;
        this.op = op;
        this.right = right;
    }
}
