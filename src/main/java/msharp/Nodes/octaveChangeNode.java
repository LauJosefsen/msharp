package msharp.Nodes;

public class octaveChangeNode extends stmtNode{
    private int deltaOctave;

    public octaveChangeNode(int deltaOctave) {
        this.deltaOctave = deltaOctave;
    }

    @Override
    public String toString() {
        return null;
    }
}