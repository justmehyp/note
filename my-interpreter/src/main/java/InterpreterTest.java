import interpreter.Interpreter;
import lexer.Lexer;
import parser.Parser;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class InterpreterTest {
    public static void main(String[] args) throws Exception {
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(System.in));

        String line = "";
        while (line != null) {
            System.out.print(">> ");
            line = lineNumberReader.readLine();

            final Lexer lexer = new Lexer(line);
            final Parser parser = new Parser(lexer);
            final Interpreter interpreter = new Interpreter(parser);
            final Object result = interpreter.interpre();
            System.out.println(result);
        }
    }
}
