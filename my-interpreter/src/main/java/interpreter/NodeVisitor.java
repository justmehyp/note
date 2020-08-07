package interpreter;

import parser.AstNode;
import parser.BinOpNode;
import parser.IntegerNode;
import parser.UnaryOpNode;

public interface NodeVisitor {

    default Object visit(AstNode astNode) {
        if (astNode instanceof IntegerNode) {
            return visitIntegerNode((IntegerNode) astNode);
        }
        else if (astNode instanceof BinOpNode) {
            return visitBinOpNode((BinOpNode) astNode);
        }
        else if (astNode instanceof UnaryOpNode) {
            return visitUnaryOpNode((UnaryOpNode) astNode);
        }
        else {
            return null; // todo 未支持的 AstNode
        }
    }

    default Object visitUnaryOpNode(UnaryOpNode node) {
        return null;
    }

    default Object visitBinOpNode(BinOpNode node) {
        return null;
    }

    default Integer visitIntegerNode(IntegerNode node) {
        return null;
    }
}
