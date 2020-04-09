package msharp.Nodes;

public class bpmDclNode implements stmtNode{
    private tempoChangeNode tempo;
    private int bpm;

    public bpmDclNode(int bpm, tempoChangeNode tempo) {
        this.tempo = tempo;
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return "BPM(" + bpm + "," + tempo.toString() + ")";
    }
}
