package msharp.Nodes;

import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public interface StmtNode extends Node {
    List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx);
}
