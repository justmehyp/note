package parser;

import java.util.List;

public class CompoundNode extends AstNode {
    public List<AstNode> children;

    public CompoundNode(List<AstNode> children) {
        this.children = children;
    }
}
