package msharp.Compiler;

import antlr4.MsharpLexer;
import antlr4.MsharpParser;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import msharp.ASTBuilder.BuildAstVisitor;
import msharp.CustomAntlrErrorListener;
import msharp.MinecraftClasses.NoteStructure;
import msharp.ASTBuilder.NodeInterface;
import msharp.ASTBuilder.ProgNode;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Compiler {
    private final String inputPath;
    private final String outputPath;
    private final int turnAroundLength;
    private final boolean generateAstAsSvg;
    private final String fillerBlock;
    
    Logger log = LogManager.getLogManager().getLogger("");
    CustomAntlrErrorListener antlrErrorListener = new CustomAntlrErrorListener(log);
    
    
    public Compiler (String inputPath, String outputPath, int turnAroundLength, boolean generateAstPdf, String fillerBlock)
    {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.turnAroundLength = turnAroundLength;
        this.generateAstAsSvg = generateAstPdf;
        this.fillerBlock = fillerBlock;
    }
    
    public void tryCompile(){
        try{
            compile();
        }
        catch(IllegalCompilerAction e){
            log.info("[HALT] " + e.getError());
        }
    }
    
    public void compile()
    {
        long startTime = System.nanoTime();
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(inputPath)));
        } catch (IOException e) {
            throw new IllegalCompilerAction(e.toString());
        }
        
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str);
        
        // create a lexer that feeds off of input CharStream
        MsharpLexer lexer = new MsharpLexer(input);
        
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        MsharpParser parser = new MsharpParser(tokens);
        
        parser.removeErrorListeners();
        lexer.removeErrorListeners();
        parser.addErrorListener(antlrErrorListener);
        lexer.addErrorListener(antlrErrorListener);
        
        
        ParseTree tree = parser.prog(); // begin parsing at init rule
        log.info("Made parse tree");
        
        if(antlrErrorListener.isFoundError())
            throw new IllegalCompilerAction("Found one or more syntax errors while parsing. See above.");
        
        BuildAstVisitor visitor = new BuildAstVisitor();
        NodeInterface ast = visitor.visit(tree);
        log.info("Made Abstract Syntax Tree");
        
        
        if (generateAstAsSvg) {
            try {
                String outputPath = inputPath.substring(0, inputPath.lastIndexOf(".")) + ".svg";
                Graphviz.fromGraph(ast.toGraph()).totalMemory(1073741824).render(Format.SVG).toFile(new File(outputPath));
                log.info("[SUCCESS] Saved AST to " + outputPath);
            } catch (IOException e) {
                log.info("[NOTE] Following error occurred while generating AST:");
                log.info(e.toString());
            }
        }
        
        // Make first parse of AST to build global scope.
        SymbolTable symbolTable = new SymbolTable((ProgNode) ast);
        log.info("Made initial symbol table");
        
        
        
        // Code-Generation
        // Interprets the AST into a list of notes with timing
        BuildNoteListVisitor notePopulator = new BuildNoteListVisitor(symbolTable);
//
//
        List<FinalNote> notes = notePopulator.visit((ProgNode) ast);
        

        log.info("Generated final notes. (" + notes.size() + ")");

        Collections.sort(notes);

        log.info("Sorted final notes. Song length is " + notes.get(notes.size() - 1).getTiming().toDouble() + " seconds.");

        // Lets fit the collection of notes to the Minecraft-limitations
        NoteStructure minecraftNotes = new NoteStructure(notes, fillerBlock, turnAroundLength);

        log.info("Fitted the final notes to minecraft. Duration: " + minecraftNotes.size() + " redstone ticks");

        minecraftNotes.GenerateSchematic().saveToFile(outputPath);

        log.info("[SUCCESS] Generated output schematic file as " + outputPath + ". Took "+ Math.round((1.0*System.nanoTime()-startTime)/1e7)/1e2+" seconds.");
        
    }
}
