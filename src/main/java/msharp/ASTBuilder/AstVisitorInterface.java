package msharp.ASTBuilder;

import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public interface AstVisitorInterface {
    List<FinalNote> visit (StmtNode stmtNodes, NodeContext ctx);
}
