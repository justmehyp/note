package parser;

import lexer.Token;

public class VariableNode extends AstNode {
    public Token token;
    public String variableName;

    public VariableNode(Token token) {
        this.token = token;
        this.variableName = token.value;
    }
}
