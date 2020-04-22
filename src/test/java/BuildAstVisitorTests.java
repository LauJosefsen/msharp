import msharp.BuildAstVisitor;
import msharp.Nodes.NoteNode;
import msharp.Nodes.ProgNode;
import msharp.cfgLexer;
import msharp.cfgParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildAstVisitorTests {
    // Utility method for creating a parse tree from an input string
//    private ParseTree MakeParseTreeWithAntlrFromString (String inputStr)
//    {
//        CharStream input = CharStreams.fromString(inputStr);
//        cfgLexer lexer = new cfgLexer(input);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        cfgParser parser = new cfgParser(tokens);
//        return parser.prog();
//    }
//
//
//    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
//    @DisplayName("VisitPbodyToneWithDifferentOctaves")
//    void testVisitPbodyToneWithDifferentOctaves (RepetitionInfo repInfo, TestInfo testInfo)
//    {
//        // Arrange
//        BuildAstVisitor astVisitor = new BuildAstVisitor();
//        String inputStr = "play \n a" + repInfo.getCurrentRepetition() + "\n end play";
//
//        // Act
//        ProgNode ast = (ProgNode) astVisitor.visit(MakeParseTreeWithAntlrFromString(inputStr));
//        NoteNode note = (NoteNode) ast.main.stmts.get(0);
//
//        // Assert
//        assertEquals(note.getOctave(), repInfo.getCurrentRepetition());
//        assertEquals('a', note.getLetter());
//    }
//
//
//    @Test
//    @DisplayName("VisitPbodyToneWithoutOctave")
//    void testVisitPbodyToneWithoutOctave ()
//    {
//        // Arrange
//        BuildAstVisitor astVisitor = new BuildAstVisitor();
//        String inputStr = "play \n a \n end play";
//
//        // Act
//        ProgNode ast = (ProgNode) astVisitor.visit(MakeParseTreeWithAntlrFromString(inputStr));
//        NoteNode note = (NoteNode) ast.main.stmts.get(0);
//
//        // Assert
//        assertEquals(note.getOctave(), -1);
//        assertEquals('a', note.getLetter());
//    }
}
