package msharp.Nodes;

import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public interface StmtNode extends Node {
    public List<FinalNote> accept(BuildNoteListVisitor visitor, NodeContext ctx);
}
