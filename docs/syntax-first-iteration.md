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

## Other statements
There exists different kind of statements, all of which are allowed inside a part or the play-method.

### Note
The simplest and most trivial statement is the note. A note is any letter `a` through `g`, followed by an optional octave. Supplying an octave will change the octave context (See above)
### Call another part to be played
Declared parts can of course be called from both other parts and the play method. This is done simply by writing the part's name. A part may NOT call itself.

### Repeat and every statements
In msharp you can repeat something. The trivial repeat operator looks as follows
```
c*8 # plays the note c 8 times
Interlude*8 # plays the part Interlude 8 times
(c a g)*8 plays the inline part 8 times.
```
You can also note repetition using multiline structure, which allows to have us to introduce the "every" statement.
```
#The following play the c 8 times. The 6th time, it will ALSO play a g.
repeat 8 times
    c
    every 6 times
        g
    end every
end repeat

```
In nested repeat statements, the every statement will always use the inner most repeat statement as its reference.

### Make an inline part
If you have a need to make a part to only be used once, for example if you need to repeat the notes `c a g b` 4 times, you can use an inline part, noted with parenthesis
```
(c a g b)*4
```


### Make an inline part
If you have a need to make a part to only be used once, for example if you need to repeat the notes `c a g b` 4 times, you can use an inline part, noted with parenthesis

### Transpose
Although you can't write the note "F#" directly in msharp, you can write and F or a G and transpose it respectively up or down.
To transpose a note up, use the `^` symbol. To tranpose it down, use the `_` symbol.
```
# F-sharp can be noted both as
g^ 
# and
g_
```
Notes can be tranposed by many steps, such that `g^5` will transpose the g node up by 5 tone classes. If the tone is to break an octave, the octave will be increased/decreased, but it will NOT be changed in the context. For example `c4_` will become `b3`.

You can also transpose parts, which will transpose all the notes in the part.
```
Interlude^
(c a g)_
```

### Parallel execution
To achieve parellel execution, and have multiple notes play at once, the AND-operator can be used.
This operator is used as 
```
<statement or part> & <statement or part>
```
and will play both statements at the same time. It is worth noting, that the msharp compiler will wait for the LEFT-side to be finished playing, before playing the next statement. (The right-side statement might still be playing at this time.)
