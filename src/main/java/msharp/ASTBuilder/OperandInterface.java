package msharp.ASTBuilder;

import msharp.Compiler.IllegalCompilerAction;
import msharp.Compiler.NumberExpressionVisitor;
import msharp.Compiler.SymbolTable;

public interface OperandInterface extends StmtNode {
    int accept(NumberExpressionVisitor visitor, SymbolTable symbolTable) throws IllegalCompilerAction;
}
