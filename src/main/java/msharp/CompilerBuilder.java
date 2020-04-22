package msharp;

import java.util.logging.Handler;

public class CompilerBuilder {
    private String inputPath;
    private String outputPath;
    private int turnAroundLength = 20;
    private boolean generateAst = false;
    private boolean shortenRedstone = false;
    private String fillerBlock = "minecraft:stone";
    private Handler loggerHandler;
    
    public Compiler buildCompiler ()
    {
        return new Compiler(inputPath, outputPath, turnAroundLength, generateAst, shortenRedstone, fillerBlock, loggerHandler);
    }
    
    public String getInputPath ()
    {
        return inputPath;
    }
    
    public void setInputPath (String inputPath)
    {
        this.inputPath = inputPath;
    }
    
    public String getOutputPath ()
    {
        return outputPath;
    }
    
    public void setOutputPath (String outputPath)
    {
        this.outputPath = outputPath;
    }
    
    public int getTurnAroundLength ()
    {
        return turnAroundLength;
    }
    
    public void setTurnAroundLength (int turnAroundLength)
    {
        this.turnAroundLength = turnAroundLength;
    }
    
    public boolean isGenerateAst ()
    {
        return generateAst;
    }
    
    public void setGenerateAst (boolean generateAst)
    {
        this.generateAst = generateAst;
    }
    
    public boolean isShortenRedstone ()
    {
        return shortenRedstone;
    }
    
    public void setShortenRedstone (boolean shortenRedstone)
    {
        this.shortenRedstone = shortenRedstone;
    }
    
    public String getFillerBlock ()
    {
        return fillerBlock;
    }
    
    public void setFillerBlock (String fillerBlock)
    {
        this.fillerBlock = fillerBlock;
    }
    
    public Handler getLoggerHandler ()
    {
        return loggerHandler;
    }
    
    public void setLoggerHandler (Handler loggerHandler)
    {
        this.loggerHandler = loggerHandler;
    }
    
    
}
