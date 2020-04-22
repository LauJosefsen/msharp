# Syntax
An msharp document exists of an arbitary amount of `parts` and one instance of a `play`-part. This can respectively be thought as of functions and a main function in a typical programming language.

## Part Declarations
A part is a collection of statements. In version 1 there are 2 ways to declare a part. 
To declare the part `Interlude`, one can do either of the following:
```
Interlude = <statements>
```
or
```
part Interlude
    <statements>
end part
```

A part can only be declared once. Valid part names are `[A-Z][a-zA-Z]*`.

## Play declaration
The play declaration is like a part, a collection of statements. Play must always be delcared in an msharp program, and must be done as follows:
```
play
    <statements>
end play
```

## Comments
Comments can be anywhere in the msharp program. Multline comments can be begun by `#**` and ended with `**#` while single line comments can be written by simply placing a `#`.
Comments are in this stage totally ignored. As well as comments, the symbol `|` can be placed anywhere in the program, without any meaning. This is intended for people that wish to mark measures in their program.

## Context-changing statements
The language does not require you to tell the compiler every time you play a note, what octave, what instrument, what timing and what duration the note should be played with.
This is instead described using the notes context.

The context in the current stage of msharp consists of the following:
| Name          | Description   | How to change |
| ---|---|---|
| Octave        | The octave of which notes are to be played in. | Can be set using `\` to go down an octave, `/` to go up an octave. Can also be set on notes, for example `g5` will both play the note g, and change the octave to the fifth octave. |
| Instrument    | The instrument of which notes are to be played with.  | Can be set using the syntax `[A-Z]:`, for example the instrument can be set to piano using `PIANO:`. (See instruments for available instruments) |
| BPM           | The amount of beats per minute, as well as what a beat is - is a beat one fourth (1/4) or an eighth (1/8)? | Is set using `BPM(<BPM>,<FRACTION>)` where bpm is a positive integer, and fraction is denoted by `<numerator>%<denominator>` where both numerator and denominators are positive integers. For example a typical song can be 80 BPM, counted with quarter notes. `BPM(80,1%4)`|
| Note duration or Tempo | The duration of how long notes are to be played as. This also affects breaks/pauses. | Like BPM, the duration is set using a fraction: `<numerator>%<denominator>` where both numerator and denominators are positive integers. To set the context to play sixteenths notes: `1%16`.

This context is scoped, in a way so that a context change will not persist back to the caller. This means when "calling" a part, the context is copied, and then given to the part. If the part changed the context, the context will not be changed at the callee. This is also true with parenthesis, which work like "inline-parts".

## Statements
There exists different kind of statements, all of which are allowed inside a part or the play-method.

### Note
The simplest and most trivial statement is the note. A note is any letter `a` through `g`, followed by an optional octave. If no octave is defined, the last played octave will be played. ([Read more about context rules](https://www.google.com)) 
### Call another part to be played
IdNode

### Make an inline part
Parenthesis

### Repeat and every statements
both singleline and multiline repeat, and with multiline repeat, explain every statement.

### Transpose
^ and _
