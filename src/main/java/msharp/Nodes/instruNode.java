package msharp.Nodes;

public class instruNode implements stmtNode{
    private String instrument;

    public instruNode(String instrument) {
        this.instrument = instrument;
    }

    @Override
    public String toString() {
        return instrument + ":";
    }
}
