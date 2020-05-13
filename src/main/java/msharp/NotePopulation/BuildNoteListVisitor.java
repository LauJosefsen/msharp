package msharp.NotePopulation;

import msharp.Compiler.IllegalCompilerAction;
import msharp.MinecraftClasses.Instrument;
import msharp.ASTBuilder.*;
import msharp.Compiler.NumberExpressionVisitor;
import msharp.Compiler.Symbol;
import msharp.Compiler.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class BuildNoteListVisitor{
    private SymbolTable symbolTable;
    private final NumberExpressionVisitor exprVisitor = new NumberExpressionVisitor();

    public BuildNoteListVisitor (SymbolTable symbolTable)
    {
        this.symbolTable = symbolTable;
    }

    public List<FinalNote> visit (ProgNode prog) throws IllegalCompilerAction
    {
        // ctx is used to store current context about variables such as octaves, instrument, Bpm, Tempo and timing.
        NodeContext ctx = new NodeContext();
        return visit(prog.getMain(), ctx);
    }

    private List<FinalNote> visit (PlayNode play, NodeContext ctx) throws IllegalCompilerAction
    {
        symbolTable.openScope();
        List<FinalNote> noteList = new ArrayList<>();
        for (StmtNode stmt : play.getStmts()) {
            noteList.addAll(stmt.accept(this, ctx));
        }
        symbolTable.closeScope();
        return noteList;
    }

    public List<FinalNote> visit (AndNode node, NodeContext ctx) throws IllegalCompilerAction
    {

        // Makes a clone of the current context, so the changes to the context doesn't overlap into the "global" scope
        NodeContext leftCtx = ctx.clone();
        List<FinalNote> notes = new ArrayList<>(node.getLeft().accept(this, leftCtx));

        NodeContext rightCtx = ctx.clone();
        notes.addAll(node.getRight().accept(this, rightCtx));

        ctx.timing = leftCtx.timing;
        return notes;
    }

    public List<FinalNote> visit (BpmDclNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        // This visit should only change the ctx, and add no new notes.
        ctx.bpm.bpm = node.getBpm().accept(exprVisitor,symbolTable);
        ctx.bpm.tempo = new Fraction(
                node.getTempo().getNumerator().accept(exprVisitor,symbolTable),
                node.getTempo().getDenominator().accept(exprVisitor,symbolTable));
        return new ArrayList<>();
    }

    public List<FinalNote> visit (EveryNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        symbolTable.openScope();
        
        List<FinalNote> notes = new ArrayList<>();

        // Integer on the stack is 1-based
        if (ctx.repeatIterationStack.peek().value % node.getAmount().accept(exprVisitor,symbolTable) == 0) {
            notes.addAll(node.getTrueCase().accept(this, ctx));
        } else {
            notes.addAll(node.getElseCase().accept(this, ctx));
        }
        
        symbolTable.closeScope();

        return notes;
    }

    public List<FinalNote> visit (IdNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        // type check and check scope
        Symbol symbol = symbolTable.retrieveSymbol(node.getId());
    
        //scope checking
        if(symbol == null){
            // make some error noise
            throw new IllegalCompilerAction("Name \""+node.getId()+"\", was not declared in current scope.");
        }
    
        //type checking
        if(symbol.value.getClass() != StmtList.class){
            // make some error noise
            throw new IllegalCompilerAction("Name \""+node.getId()+"\", was declared in current scope, but is not a part.");
        }
        
        StmtList part = (StmtList) symbol.value;
        
        NodeContext newCtx = ctx.clone();
        symbolTable.openScope();
        List<FinalNote> toAdd = part.accept(this, newCtx);
        symbolTable.closeScope();
        ctx.timing = newCtx.timing;
        return toAdd;
    }

    public List<FinalNote> visit (InstruNode node, NodeContext ctx)
    {
        // This visit should only change the ctx, and add no new notes.
        ctx.instrument = Instrument.fromString(node.getInstrument());
        return new ArrayList<>();
    }

    public List<FinalNote> visit (NoteNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        List<FinalNote> notes = new ArrayList<>();

        // if NoteNode has an octave defined, we change the octave defined in the current ctx.
        if (node.getOctave() != null) {
            ctx.octave = node.getOctave().accept(exprVisitor,symbolTable);
        }
        // if the Node isn't a pause, we add the Node to the Node list.
        if (node.getLetter() != '-') {
            FinalNote note = new FinalNote(ctx.instrument, ToneEnum.fromLetter(node.getLetter()), ctx.octave,
                    new FractionPrecise(ctx.timing, new FractionPrecise(1, 1))); // new fraction to avoid using same reference.
            
            // before leaving, we must transpose the note if scale says so.
            if(ctx.scale.getInScale().contains(note.getTone())){
                note.transpose(ctx.scale.isUp() ? 1 : -1);
            }
            
            notes.add(note);
        }

        // Move the timer in the context by the duration of the note.
        moveTimerByNoteDuration(ctx);
        
        

        return notes;
    }

    private void moveTimerByNoteDuration (NodeContext ctx)
    {
        // adds the note "duration" to timing.This is calculated based on Bpm and Tempo.
        //        float secondPrBeat = (float) ((1.0/ctx.bpm.bpm)*60.0);    // calculates the time between beats
        //        float beatsPrNode = (float) ((1.0*ctx.bpm.tempo.toFraction()) / ctx.tempo.toFraction());
        FractionPrecise secondPrBeat = new FractionPrecise(60, ctx.bpm.bpm);
        FractionPrecise beatsPrNode = new FractionPrecise(ctx.bpm.tempo, ctx.tempo);


        // Bpm 120, 1/4
        // og vi vil spille 1/16 Node
        // secondPrBeat = 0.5s
        // beatsPrNode = (1/4) / (1/16) = 4
        // tid = 0.5s/4 = 1/8s

        //        ctx.timing += ((float)Math.round((secondPrBeat/beatsPrNode)*1000))/1000;
        ctx.timing = ctx.timing.add(new FractionPrecise(secondPrBeat, beatsPrNode));

        //ctx.timing += secondPrBeat/beatsPrNode;
    }

    public List<FinalNote> visit (OctaveChangeNode node, NodeContext ctx)
    {
        // This visit should only change the ctx, and add no new notes.
        ctx.octave += node.getDeltaOctave();
        return new ArrayList<>();   // returns empty list
    }

    public List<FinalNote> visit (RepeatNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        List<FinalNote> notes = new ArrayList<>();

        IntByReference iteration = new IntByReference(1);
    
        symbolTable.openScope();
        
        // We add the iteration as a Integer class (reference type), so that the every-nodes can peek at the stack.
        ctx.repeatIterationStack.push(iteration);
        for (; iteration.value <= node.getAmount().accept(exprVisitor,symbolTable); iteration.value++) {
            notes.addAll(node.getStmts().accept(this, ctx));
        }
        ctx.repeatIterationStack.pop();
        
        symbolTable.closeScope();

        return notes;
    }

    public List<FinalNote> visit (StmtList node, NodeContext ctx) throws IllegalCompilerAction
    {
        List<FinalNote> notes = new ArrayList<>();

        NodeContext cloned = ctx.clone();

        // Visits all the nodes in the stmtlist
        for (StmtNode stmt : node) {
            notes.addAll(stmt.accept(this, cloned));
        }
        ctx.timing = cloned.timing;

        return notes;
    }

    public List<FinalNote> visit (TempoChangeNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        // This visit should only change the ctx, and add no new notes.
        ctx.tempo = new Fraction(
                node.getNumerator().accept(exprVisitor,symbolTable),
                node.getDenominator().accept(exprVisitor,symbolTable));
        return new ArrayList<>();
    }

    public List<FinalNote> visit (TransposeNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        List<FinalNote> toBeTransposed = node.getToBeTransposed().accept(this, ctx);

        for (FinalNote note : toBeTransposed) {
            note.transpose(node.getDeltaTonation().accept(exprVisitor,symbolTable));
        }

        return toBeTransposed;
    }
    
    public List<FinalNote> visit (ScaleNode node, NodeContext ctx)
    {
        ctx.scale = node;
        return new ArrayList<>();
    }
    
    public List<FinalNote> visit (NumDeclNode node, NodeContext ctx) throws IllegalCompilerAction
    {
        symbolTable.enterSymbol(node.getId(),node.getValue().accept(exprVisitor,symbolTable));
        return new ArrayList<>();
    }
}
