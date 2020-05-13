package msharp.ASTBuilder;

import msharp.Compiler.IllegalCompilerAction;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public interface StmtNode extends Node {
    List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx) throws IllegalCompilerAction;
}
