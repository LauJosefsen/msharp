package msharp.ASTBuilder;

public enum ExprOpEnum {
    DIVIDE,MULTIPLY,ADD,SUBTRACT,MODULO;

    public static ExprOpEnum fromString (String text)
    {
        switch(text){
            case "+": return ADD;
            case "-": return SUBTRACT;
            case "*": return MULTIPLY;
            case "/": return DIVIDE;
            case "%": return MODULO;
            default: throw new IllegalArgumentException("Operator unknown: " + text);
        }
    }
}
