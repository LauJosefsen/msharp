package msharp.Nodes;

public class bpmDclNode extends stmtNode{
    private tempoChangeNode tempo;
    private int bpm;

    public bpmDclNode(int bpm, tempoChangeNode tempo) {
        this.tempo = tempo;
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return null;
    }
}
