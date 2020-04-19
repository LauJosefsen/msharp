package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;
import msharp.Nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotePopulation {
    // todo Set standard octave, hvis nu den første node ikke har en oktav
    private Map<String, Object> symbolTable = new HashMap<>();

    public NotePopulation(Map<String, Object> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<FinalNote> visit(progNode prog){
        // ctx is used to store current context about variables such as octaves, instrument, bpm, tempo and timing.
        nodeContext ctx =  new nodeContext();
        return visit(prog.main,ctx);
    }

    private List<FinalNote> visit(playNode play, nodeContext ctx) {
        List<FinalNote> noteList = new ArrayList<>();
        for(stmtNode stmt : play.stmts){
            noteList.addAll(stmt.accept(this,ctx));
        }
        // De foskellige muligheder for stmt notes
        // 1. Noget der ændrer i ctx
        // 2. Noget der tilføjer x antal noter
        //
        return noteList;
    }

    public List<FinalNote> visit(andNode node, nodeContext ctx){
        List<FinalNote> notes = new ArrayList<>();

        // Makes a clone of the current context, so the changes to the context doesn't overlap into the "global" scope
        nodeContext leftCtx = ctx.clone();
        notes.addAll(node.getLeft().accept(this,leftCtx));

        nodeContext rightCtx = ctx.clone();
        notes.addAll(node.getRight().accept(this,rightCtx));

        // we now set the timing to the max point of either left or right.
        // todo var det den her måde timing skulle virke på????
        ctx.timing = leftCtx.timing;
        return notes;
    }

    public List<FinalNote> visit(bpmDclNode node, nodeContext ctx){
        // This visit should only change the ctx, and add no new notes.
        ctx.bpm.bpm = node.getBpm();
        ctx.bpm.tempo = new tempo(node.getTempo().getNumerator(),node.getTempo().getDenominator());
        return new ArrayList<>();
    }

    public List<FinalNote> visit(everyNode node, nodeContext ctx){
        List<FinalNote> notes = new ArrayList<>();

        // Integer on the stack is 1-based
        if(ctx.repeatIterationStack.peek().value % node.getAmount() == 0){
            notes.addAll(node.getTrueCase().accept(this,ctx));
        }
        else{
            notes.addAll(node.getElseCase().accept(this,ctx));
        }

        return notes;
    }

    public List<FinalNote> visit(idNode node, nodeContext ctx){

        if(!symbolTable.containsKey(node.getId())){
            // todo throw some error?
            throw new IllegalArgumentException("Id does not exists in symboltable"+node.getId());
        }

        return ((stmtList)symbolTable.get(node.getId())).accept(this,ctx);
    }

    public List<FinalNote> visit(instruNode node, nodeContext ctx){
        // This visit should only change the ctx, and add no new notes.
        ctx.instrument = Instrument.fromString(node.getInstrument());
        return new ArrayList<>();
    }

    public List<FinalNote> visit(noteNode node, nodeContext ctx){
        List<FinalNote> notes = new ArrayList<>();

        // if noteNode has an octave defined, we change the octave defined in the current ctx.
        if(node.getOctave() != -1){
            ctx.octave = node.getOctave();
        }

        // if the node isn't a pause, we add the node to the node list.
        if(node.getLetter() != '-') {
            notes.add(new FinalNote(ctx.instrument, ToneEnum.fromLetter(node.getLetter()), ctx.octave, ctx.timing));
        }
        
        // Move the timer in the context by the duration of the note.
        moveTimerByNoteDuration(ctx);

        return notes;
    }

    private void moveTimerByNoteDuration(nodeContext ctx) {
        // adds the note "duration" to timing.This is calculated based on bpm and tempo.
        float secondPrBeat = (float) ((1.0/ctx.bpm.bpm)*60.0);    // calculates the time between beats
        float beatsPrNode = (float) ((1.0*ctx.bpm.tempo.toFraction()) / ctx.tempo.toFraction());

        //todo fix accumulating rounding errors here..

        // bpm 120, 1/4
        // og vi vil spille 1/16 node
        // secondPrBeat = 0.5s
        // beatsPrNode = (1/4) / (1/16) = 4
        // tid = 0.5s/4 = 1/8s

        ctx.timing += ((float)Math.round((secondPrBeat/beatsPrNode)*1000))/1000;

        //ctx.timing += secondPrBeat/beatsPrNode;
    }

    public List<FinalNote> visit(octaveChangeNode node, nodeContext ctx){
        // This visit should only change the ctx, and add no new notes.
        ctx.octave += node.getDeltaOctave();
        return new ArrayList<>();   // returns empty list
    }

    public List<FinalNote> visit(repeatNode node, nodeContext ctx){
        List<FinalNote> notes = new ArrayList<>();

        IntByReference iteration = new IntByReference(1);

        // We add the iteration as a Integer class (reference type), so that the every-nodes can peek at the stack.
        ctx.repeatIterationStack.push(iteration);
        for(; iteration.value <= node.getAmount(); iteration.value++){   // todo tjek at integer er en poienter
            notes.addAll(node.getStmts().accept(this,ctx));
        }
        ctx.repeatIterationStack.pop();

        return notes;
    }

    public List<FinalNote> visit(stmtList node, nodeContext ctx){
        List<FinalNote> notes = new ArrayList<>();

        // Visits all the nodes in the stmtlist
        for(stmtNode stmt : node){
            notes.addAll(stmt.accept(this,ctx));
        }

        return notes;
    }

    public List<FinalNote> visit(tempoChangeNode node, nodeContext ctx){
        // This visit should only change the ctx, and add no new notes.
        ctx.tempo = new tempo(node.getNumerator(),node.getDenominator());
        return new ArrayList<>();
    }

    public List<FinalNote> visit(transposeNode node, nodeContext ctx){
        List<FinalNote> toBeTransposed = node.getToBeTransposed().accept(this,ctx);

        for(FinalNote note : toBeTransposed){
            note.transpose(node.getDeltaTonation());
        }

        return toBeTransposed;
    }

}
