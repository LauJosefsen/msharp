package msharp;

public class IllegalCompilerAction extends Exception {
    private final String error;
    
    public String getError ()
    {
        return error;
    }
    
    public IllegalCompilerAction (String error){
        this.error = error;
    }
}
