package msharp.Nodes;

import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NotePopulation;
import msharp.NotePopulation.nodeContext;

import java.util.List;

public interface stmtNode extends node {
    public List<FinalNote> accept(NotePopulation visitor, nodeContext ctx);
}
