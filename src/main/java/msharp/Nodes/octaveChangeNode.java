package msharp.Nodes;

public class octaveChangeNode implements stmtNode{
    private int deltaOctave;

    public octaveChangeNode(int deltaOctave) {
        this.deltaOctave = deltaOctave;
    }

    @Override
    public String toString() {
        String str;

        if (deltaOctave <= 0)
            str = "/" + deltaOctave;
        else
            str = "\\" + deltaOctave;

        return str;
    }
}