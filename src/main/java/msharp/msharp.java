package msharp;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class msharp {

    public static void main(String[] args) throws IOException {
        // temporary input string to test with
        //String str = "a = 10 10 + a - 32";
        String str = new String(Files.readAllBytes(Paths.get("examples/hallelujah.txt")));

        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(str);
        // create a lexer that feeds off of input CharStream
        cfgLexer lexer = new cfgLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        cfgParser parser = new cfgParser(tokens);
        ParseTree tree = parser.prog(); // begin parsing at init rule


        //part node = new part();
        //node.toneList = new ArrayList<tone>();
        //tone t1 = new tone();
        //t1.letter = "a";
        //t1.octave = 1;
        //node.toneList.add(t1);
        //System.out.println("Result: " + node.toneList.get(0).letter);


        buildAstVisitor visitor = new buildAstVisitor();
        visitor.visit(tree);

        if(visitor.getSemanticErrors().size() > 0){
            for(String error : visitor.getSemanticErrors()){
                System.out.println(error);
            }
            return;
        }

        String result = visitor.visit(tree).toString();

        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        System.out.println("Result: " + result);
    }
}