package msharp.Nodes;

public class noteNode extends stmtNode {
    private char letter;
    public int octave;

    public noteNode(char letter, int octave) {
        this.letter = letter;
        this.octave = octave;
    }

    @Override
    public String toString() {
        return null;
    }
}
