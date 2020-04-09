package msharp.Nodes;

public class tempoChangeNode implements stmtNode{
    private int numerator;
    private int denomniator;

    public tempoChangeNode(int numerator, int denomniator) {
        this.numerator = numerator;
        this.denomniator = denomniator;
    }

    @Override
    public String toString() {
        return numerator + "%" + denomniator;
    }
}
