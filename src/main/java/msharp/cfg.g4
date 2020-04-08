grammar cfg;

prog
    : (partDcl | Nl)* playDcl
    ;

partDcl
    : Id Assign stmt+ Nl                            // Single Line
    | 'part' Id Nl multStmt+ Nl 'end part' Nl      // Multi Line
    ;
playDcl
    : 'play' Nl multStmt+  Nl 'end play'
    ;

stmt
    : partBody              # StmtPBody
    | ops                   # StmtOps
    ;

ops
    : Instrument            # OpsIntru
    | OctaveDown            # OpsOctDown
    | OctaveUp              # OpsOctUp
    | tempoOp               # OpsTempOp
    | bpmDcl                # OpsBpmDcl
    ;

bpmDcl
    : Bpm Lparen Digs Comma tempoOp Rparen
    ;

tempoOp
    : Digs Percent Digs
    ;

multStmt    // Todo
    : stmt                  # MultStmtStmt
    | Nl                    # MultStmtNL
    | multilineRepeat       # MultStmtMultRepeat    // repeat x times ... end repeat
    ;

multilineRepeat
    : 'repeat' Digs 'times' Nl (multStmt | everyStmt)+ 'end repeat'
    ;
everyStmt
    : 'every' Digs 'times' Nl (multStmt | everyStmt)+ 'end every' Nl+? elseStmt?
    ;
elseStmt
    : 'else' Nl (multStmt | everyStmt)+ 'end else'
    ;

partBody
    : Tone Digs?                        # PbodyTone
    | Id                                # PbodyId
    | Pause                             # PbodyPause
    | Lparen stmt+ Rparen               # PbodyParen
    | partBody TransposeUp Digs?        # PbodyTransUp
    | partBody TransposeDown Digs?      # PbodyTransDown
    | partBody SingleAnd partBody       # PbodySingleAnd
    | partBody DoubleAnd partBody       # PbodyDoubleAnd
    | partBody Repeat Digs              # PbodySingleLRepeat
    ;





// lexer rules
Nl: ('\n' | '\r')+;
Bpm: 'BPM';
Tone: [a-g];
Percent: '%';
Lparen: '(';
Rparen: ')';
Comma: ',';
Digs: [0-9]+;
Assign: '=';
Instrument: [A-Z]+ ':';
OctaveUp: '/';
OctaveDown: '\\';
TransposeUp: '^';
TransposeDown: '_';
SingleAnd: '&';
DoubleAnd: '&&';
Repeat: '*';
Pause: '-';
Id: [A-Z][a-zA-Z0-9]*;

Peroids: '|' -> skip;
S: (' ' | '\t') -> skip;
MultilineComment: '#**' .*? '**#' Nl -> skip;
SingleLineComment: '#' ~[\r\n]* Nl -> skip;