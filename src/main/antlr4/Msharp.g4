grammar Msharp;

@header {
    package antlr4;
    import static org.antlr.v4.runtime.Lexer.HIDDEN;
}

prog
    : (partDcl | Nl | assignNumVariable)* playDcl
    ;

partDcl:
     'part' Id Nl stmt+ Nl 'end part' Nl
    ;
playDcl
    : 'play' Nl stmt+  Nl 'end play'
    ;

stmt
    : partBody              # StmtPBody
    | ops                   # StmtOps
    | Nl                    # MultStmtNL
    | multilineRepeat       # MultStmtMultRepeat    // repeat x times ... end repeat
    | assignNumVariable     # MultStmtAssignNum
    ;

ops
    : Instrument                                                            # OpsIntru
    | OctaveDown                                                            # OpsOctDown
    | OctaveUp                                                              # OpsOctUp
    | tempoOp                                                               # OpsTempOp
    | Bpm Lparen numberExpr Comma tempoOp Rparen                            # OpsBpmDcl
    | Scale Lparen ((Tone Comma)+ (TransposeDown | TransposeUp))? Rparen    # OpsScale
    ;

tempoOp
    : digsOrNumberExprInParenthesis Percent digsOrNumberExprInParenthesis
    ;

digsOrNumberExprInParenthesis
    : Digs
    | Lparen numberExpr Rparen
    ;

multilineRepeat
    : 'repeat' numberExpr 'times' Nl stmtOrEveryStmt+ 'end repeat'
    ;

// Question: Why do we need this as a parser rule and not just use (multStmt | everyStmt)+ in multilineRepeat.
// Answer: Antlr generates the context so that we, using that proposed method gets TWO arrays of stmts, one being
//         the multStmt array and one being everyStmt array. Having two arrays, we cant determine the order of the
//         different statements. By using this parser rule, we only get one, sorted array.
stmtOrEveryStmt
    : stmt              # MultStmtOrEveryStmtMultStmt
    | everyStmt             # MultStmtOrEveryStmtEveryStmt
    ;

everyStmt
    : 'every' numberExpr 'times' Nl stmtOrEveryStmt+ 'end every' Nl+ elseStmt?
    ;
elseStmt
    : 'else' Nl stmtOrEveryStmt+ 'end else'
    ;

partBody
    : Tone Digs                                                                                                         # PbodyTone
    | {_input.LA(1) == Tone && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? Tone Lparen numberExpr Rparen    # PbodyTone
    | Tone                                                                                                              # PbodyTone
    | Id                                                                                                                # PbodyId
    | Pause                                                                                                             # PbodyPause
    | Lparen stmt+ Rparen                                                                                               # PbodyParen
    | partBody partAfter                                                                                                # PbodyOperators
    ;
    //| partBody TransposeUp numberExpr?                                                                                  # PbodyTransUp
    //| partBody TransposeDown numberExpr?                                                                                # PbodyTransDown
  //  | partBody And partBody                                                                                             # PbodyAnd
//    | partBody Repeat numberExpr                                                                                        # PbodySingleLRepeat
partAfter
    : {_input.LA(1) == TransposeUp && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? TransposeUp numberExpr           # TransposeOperator
    | TransposeUp                                                                                                       # TransposeOperator
    | {_input.LA(1) == TransposeDown && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? TransposeDown numberExpr         # TransposeOperator
    | TransposeDown                                                                                                     # TransposeOperator
    | And partBody                                                                                                      # AndOperator
    | Repeat numberExpr                                                                                                 # RepeatOperator
    ;

numberExpr
    : numberExpr Plus numberTerm            # ExprOp
    | numberExpr Pause numberTerm           # ExprOp
    | numberTerm                            # ExprValue
    ;
numberTerm
    : numberTerm Repeat numberFactor        # TermOp
    | numberTerm OctaveUp numberFactor      # TermOp
    | numberTerm Percent numberFactor       # TermOp
    | numberFactor                          # TermValue
    ;
numberFactor
    : Lparen numberExpr Rparen              # FactorParens
    | Id                                    # FactorId
    | Digs                                  # FactorDigs
    ;



    /*Id                                    # NumberExprId
    | Digs                                  # NumberExprDigs
    | numberExpr numberOp numberExpr        # NumberExprActualExpression
    | Lparen numberExpr Rparen              # NumberExprParens
    ;


numberOp
    : Plus
    | Repeat
    | Pause
    | OctaveUp
    | Percent
    ;*/

assignNumVariable
    : Id Assign numberExpr Nl
    ;

// lexer rules
Nl: ('\n' | '\r')+;
Bpm: 'BPM';
Scale: 'SCALE';
Tone: [a-g];
Percent: '%';
Lparen: '(';
Rparen: ')';
Comma: ',';
Digs: '-'?[0-9]+;
Assign: '=';
Instrument: [A-Z]+ ':';
OctaveUp: '/';
OctaveDown: '\\';
TransposeUp: '^';
TransposeDown: '_';
And: '&';
Repeat: '*';
Pause: '-';
Plus: '+';
Id: [A-Z][a-zA-Z0-9]*;

Peroids: '|' -> skip;
S: (' ' | '\t') -> channel(HIDDEN);
MultilineComment: '#**' .*? '**#' -> skip;
SingleLineComment: '#' ~[\r\n]* Nl -> skip;