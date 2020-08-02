package interpreter;


import lexer.TokenType;
import parser.*;

/**
 * 遍历Parser生成的AST
 */
public class Interpreter {

    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    public Object interpret() {
        AstNode ast = parser.parse();
        return visit(ast);
    }

    private Object visit(AstNode astNode) {
        if (astNode instanceof IntegerNode) {
            return visitIntegerNode((IntegerNode) astNode);
        }
        else if (astNode instanceof BinOpNode) {
            BinOpNode node = ((BinOpNode) astNode);
            switch (node.op.type) {
                case PLUS:
                    return Integer.parseInt(visit(node.left).toString()) + Integer.parseInt(visit(node.right).toString());
                case MINUS:
                    return Integer.parseInt(visit(node.left).toString()) - Integer.parseInt(visit(node.right).toString());
                case MUL:
                    return Integer.parseInt(visit(node.left).toString()) * Integer.parseInt(visit(node.right).toString());
                case DIV:
                    return Integer.parseInt(visit(node.left).toString()) / Integer.parseInt(visit(node.right).toString());
                default:
                    return null; // todo 未支持的 BinOpNode
            }
        }
        else if (astNode instanceof UnaryOpNode) {
            UnaryOpNode node = ((UnaryOpNode) astNode);
            if(node.token.type == TokenType.PLUS) {
                return visit(node.child);
            }
            else if (node.token.type == TokenType.MINUS) {
                return -Integer.parseInt(visit(node.child).toString());
            }
            else {
                return null; // todo 未支持的 UnaryOpNode
            }
        }
        else {
            return null; // todo 未支持的 AstNode
        }
    }

    private Integer visitIntegerNode(IntegerNode node) {
        return node.value;
    }
}
