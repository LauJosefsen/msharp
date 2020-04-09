package msharp.Nodes;

public class noteNode implements stmtNode {
    private char letter;
    public int octave;

    public noteNode(char letter, int octave) {
        this.letter = letter;
        this.octave = octave;
    }

    @Override
    public String toString() {
        String o;

        if (octave == -1)
            o = "";
        else
            o = String.valueOf(octave);
        return letter + o;
    }
}
