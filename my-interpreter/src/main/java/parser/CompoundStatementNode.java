package parser;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatementNode extends AstNode {
    public List<AstNode> children;

    public CompoundStatementNode(List<AstNode> children) {
        this.children = children;
    }
}
