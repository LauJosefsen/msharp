grammar Msharp;

@header {
    package antlr4;
    import static org.antlr.v4.runtime.Lexer.HIDDEN;
}

prog
    : (partDcl | Nl | assignNumVariable)* playDcl
    ;

partDcl
    : Id Assign stmt+ Nl                        # PartDclSingleLine
    | 'part' Id Nl multStmt+ Nl 'end part' Nl   # PartDclMultiLine
    ;
playDcl
    : 'play' Nl multStmt+  Nl 'end play'
    ;

stmt
    : partBody              # StmtPBody
    | ops                   # StmtOps
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

multStmt
    : stmt                  # MultStmtStmt
    | Nl                    # MultStmtNL
    | multilineRepeat       # MultStmtMultRepeat    // repeat x times ... end repeat
    | assignNumVariable     # MultStmtAssignNum
    ;

multilineRepeat
    : 'repeat' numberExpr 'times' Nl multStmtOrEveryStmt+ 'end repeat'
    ;

// Question: Why do we need this as a parser rule and not just use (multStmt | everyStmt)+ in multilineRepeat.
// Answer: Antlr generates the context so that we, using that proposed method gets TWO arrays of stmts, one being
//         the multStmt array and one being everyStmt array. Having two arrays, we cant determine the order of the
//         different statements. By using this parser rule, we only get one, sorted array.
multStmtOrEveryStmt
    : multStmt              # MultStmtOrEveryStmtMultStmt
    | everyStmt             # MultStmtOrEveryStmtEveryStmt
    ;

everyStmt
    : 'every' numberExpr 'times' Nl multStmtOrEveryStmt+ 'end every' Nl+ elseStmt?
    ;
elseStmt
    : 'else' Nl multStmtOrEveryStmt+ 'end else'
    ;

partBody
    : Tone Digs                                                                                                         # PbodyTone
    | {_input.LA(1) == Tone && _input.get(_input.index() + 1).getChannel() != HIDDEN}? Tone Lparen numberExpr Rparen    # PbodyTone
    | Tone                                                                                                              # PbodyTone
    | Id                                                                                                                # PbodyId
    | Pause                                                                                                             # PbodyPause
    | Lparen stmt+ Rparen                                                                                               # PbodyParen
    | partBody TransposeUp numberExpr?                                                                                  # PbodyTransUp
    | partBody TransposeDown numberExpr?                                                                                # PbodyTransDown
    | partBody And partBody                                                                                             # PbodyAnd
    | partBody Repeat numberExpr                                                                                        # PbodySingleLRepeat
    ;

numberExpr
    : Id                                    # NumberExprId
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
    ;

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