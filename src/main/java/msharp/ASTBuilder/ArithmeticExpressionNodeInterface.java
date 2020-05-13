package msharp.ASTBuilder;

import msharp.Compiler.IllegalCompilerAction;
import msharp.Compiler.SymbolTable;

public interface ArithmeticExpressionNodeInterface extends NodeInterface {
    // every arithmetic expression should be able to be visited and return int by our arithemetic expression visitor.
    int accept(ArithmeticExpressionVisitor visitor, SymbolTable symbolTable) ;
}
