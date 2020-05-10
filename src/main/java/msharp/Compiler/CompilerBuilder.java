package msharp.Compiler;

import msharp.Compiler.Compiler;

import java.util.logging.Handler;

public class CompilerBuilder {
    private String inputPath;
    private String outputPath;
    private int turnAroundLength = 20;
    private boolean generateAst = false;
    private String fillerBlock = "minecraft:stone";

    public Compiler buildCompiler ()
    {
        return new Compiler(inputPath, outputPath, turnAroundLength, generateAst,  fillerBlock);
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

    public String getFillerBlock ()
    {
        return fillerBlock;
    }

    public void setFillerBlock (String fillerBlock)
    {
        this.fillerBlock = fillerBlock;
    }


}
