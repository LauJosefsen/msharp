package msharp;

import msharp.MinecraftClasses.MinecraftFitment;
import msharp.MinecraftClasses.NoteStructure;
import msharp.Nodes.Node;
import msharp.Nodes.ProgNode;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Compiler {
    String inputPath;
    Logger log = LogManager.getLogManager().getLogger("");
    CustomAntlrErrorListener antlrErrorListener = new CustomAntlrErrorListener(log);
    private static final String fatalError = "[FATAL] Fix above mentioned issues before compiling again. ABORTED.";


    public Compiler(String inputPath, Handler loggerHandler) {
        this.inputPath = inputPath;

        // we remove the handler first, to ensure we dont add it twice.
        log.removeHandler(loggerHandler);
        log.addHandler(loggerHandler);
    }

    public void compile(){
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(inputPath)));
        } catch (IOException e) {
            log.info("[FATAL] "+e.toString());
            return;
        }

        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str);
        // create a lexer that feeds off of input CharStream
        cfgLexer lexer = new cfgLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        cfgParser parser = new cfgParser(tokens);

        parser.removeErrorListeners();
        lexer.removeErrorListeners();
        parser.addErrorListener(antlrErrorListener);
        lexer.addErrorListener(antlrErrorListener);


        ParseTree tree = parser.prog(); // begin parsing at init rule
        if(parser.getNumberOfSyntaxErrors() != 0){
            log.severe(fatalError);
            return;
        }
        log.info("Made parse tree");

        BuildAstVisitor visitor = new BuildAstVisitor();
        Node ast = visitor.visit(tree);
        log.info("Made Abstract Syntax Tree");

        if(visitor.getSemanticErrors().size() > 0){
            for(String error : visitor.getSemanticErrors()){
                log.info(error);
            }
            log.severe(fatalError);
            return;
        }


//        Graphviz.fromGraph(ast.toGraph()).totalMemory(1073741824).render(Format.SVG).toFile(new File("example/ex1.svg"));

        // Code-Generation
        // Interprets the AST into a list of notes with timing
        BuildNoteListVisitor notePopulator = new BuildNoteListVisitor(visitor.symbolTable);


        List<FinalNote> notes = notePopulator.visit((ProgNode) ast);

        if(notePopulator.getErrors().size() > 0){
            for(String error : notePopulator.getErrors()){
                log.info("[SYNTAX ERROR] "+error);
            }
            log.severe(fatalError);
            return;
        }

        log.info("Generated final notes. ("+notes.size()+")");

        Collections.sort(notes);

        log.info("Sorted final notes. Song length is " + notes.get(notes.size()-1).getTiming().toDouble()+" seconds.");

        // Lets fit the collection of notes to the Minecraft-limitations
        NoteStructure minecraftNotes = MinecraftFitment.fitToMinecraft(notes);

        log.info("Fitted the final notes to minecraft. Duration: "+minecraftNotes.size()+" redstone ticks");

        String outputPath = inputPath.substring(0,inputPath.lastIndexOf("."))+".schem";
        minecraftNotes.GenerateSchematic().saveToFile(outputPath);

        log.info("[SUCCESS] Generated output schematic file as "+outputPath);

    }
}
