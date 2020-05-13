package msharp.ASTBuilder;

import msharp.Compiler.IllegalCompilerAction;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public interface StmtNode extends NodeInterface {
    // every stmt should be able to be visited by our ast-builder.
    List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx) ;
}
