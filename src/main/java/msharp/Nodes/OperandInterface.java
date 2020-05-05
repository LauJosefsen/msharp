package msharp.Nodes;

import msharp.IllegalCompilerAction;
import msharp.NumberExpressionVisitor;
import msharp.SymbolTable;

public interface OperandInterface extends StmtNode {
    public int accept(NumberExpressionVisitor visitor, SymbolTable symbolTable) throws IllegalCompilerAction;
}
