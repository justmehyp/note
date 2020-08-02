import interpreter.Interpreter;
import lexer.Lexer;
import parser.Parser;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class InterpreterTest {
    public static void main(String[] args) throws Exception {
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print(">> ");
            String line = lineNumberReader.readLine();

            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            else if ("exit".equals(line) || "quit".equals(line)) {
                break;
            }

            final Lexer lexer = new Lexer(line);
            final Parser parser = new Parser(lexer);
            final Interpreter interpreter = new Interpreter(parser);
            final Object result = interpreter.interpret();
            System.out.println(result);
        }
    }
}
