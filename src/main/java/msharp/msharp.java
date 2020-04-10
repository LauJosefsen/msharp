package msharp;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import msharp.Nodes.node;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class msharp {

    public static void main(String[] args) throws IOException {
        // temporary input string to test with
        //String str = "a = 10 10 + a - 32";
        String str = new String(Files.readAllBytes(Paths.get("examples/hallelujah.txt")));

        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str);
        // create a lexer that feeds off of input CharStream
        cfgLexer lexer = new cfgLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        cfgParser parser = new cfgParser(tokens);
        ParseTree tree = parser.prog(); // begin parsing at init rule

        buildAstVisitor visitor = new buildAstVisitor();
        node ast = visitor.visit(tree);

        if(visitor.getSemanticErrors().size() > 0){
            for(String error : visitor.getSemanticErrors()){
                System.out.println(error);
            }
            return;
        }

        Graphviz.fromGraph(ast.toGraph()).render(Format.SVG).toFile(new File("example/ex1.svg"));



//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        System.out.println("Result: \n" + ast.toString());
    }
}