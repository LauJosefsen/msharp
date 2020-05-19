grammar Msharp;

@header {
    package antlr4;
    import static org.antlr.v4.runtime.Lexer.HIDDEN;
}

prog
    : (partDcl | Nl | assignNumVariable)* playDcl
    ;

partDcl:
     'part' Id Nl statement+ Nl 'end part' Nl
    ;

assignNumVariable
    : Id Assign numberExpr Nl
    ;

playDcl
    : 'play' Nl statement+  Nl 'end play'
    ;

statement
    : partExpr              # StatementPartExpr
    | Nl                    # StatementNewLine
    | multilineRepeat       # StatementMultilineRepeat    // repeat x times ... end repeat
    | assignNumVariable     # StatementAssignNumber
    | Instrument                                                            # StatementInstrument
    | OctaveDown                                                            # StatementOctaveDown
    | OctaveUp                                                              # StatementOctaveUp
    | tempoOp                                                               # StatementChangeTempo
    | Bpm Lparen numberExpr Comma tempoOp Rparen                            # StatementChangeBPM
    | Scale Lparen ((Tone Comma)+ (TransposeDown | TransposeUp))? Rparen    # StatementChangeScale
    ;

// has its own rule because it is used 2 places.
tempoOp
    : numberExpr Percent numberExpr
    ;

/*
 / repeat-body, every and else:
 */
multilineRepeat
    : 'repeat' numberExpr 'times' Nl stmtOrEveryStmt+ 'end repeat'
    ;

// Question: Why do we need this as a parser rule and not just use (multStmt | everyStmt)+ in multilineRepeat.
// Answer: Antlr generates the context so that we, using that proposed method gets TWO arrays of stmts, one being
//         the multStmt array and one being everyStmt array. Having two arrays, we cant determine the order of the
//         different statements. By using this parser rule, we only get one, sorted array.
stmtOrEveryStmt
    : statement                  # StatementOrEveryStatementStatement
    | everyStmt             # StatementOrEveryStatementEveryStatement
    ;

everyStmt
    : 'every' numberExpr 'times' Nl stmtOrEveryStmt+ 'end every' Nl+ elseStmt?
    ;
elseStmt
    : 'else' Nl stmtOrEveryStmt+ 'end else'
    ;

/*
/  Parts:
*/

partExpr
    : partExpr And partTerm #PartExprAndOperator
    | partTerm #PartExprPartTerm;

partTerm
    : partTerm transpose                                                                                                # PartTermTranspose
    | partTerm Repeat numberExpr                                                                                        # PartTermRepeatOperator
    | partFactor                                                                                                        # PartTermPartFactor
    ;

// this is to keep it mutually left recursive. (when using semantic predicates)
transpose
    : {_input.LA(1) == TransposeUp && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? TransposeUp numberExpr
    | TransposeUp
    | {_input.LA(1) == TransposeDown && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? TransposeDown numberExpr
    | TransposeDown
    ;

partFactor
    : Tone Digs                                                                                                         # PartFactorTone
    | {_input.LA(1) == Tone && _input.size() > _input.index()+1 && _input.get(_input.index() + 1).getChannel() != HIDDEN}? Tone Lparen numberExpr Rparen    # PartFactorTone
    | Tone                                                                                                              # PartFactorTone
    | Id                                                                                                                # PartFactorId
    | Pause                                                                                                             # PartFactorPause
    | Lparen statement+ Rparen                                                                                          # PartFactorParenthesis;

/*
/ NumberExpressions:
*/

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


/*
/ Lexer rules:
*/
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