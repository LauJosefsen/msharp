package msharp;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import msharp.Compiler.Compiler;
import msharp.Compiler.CompilerBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.prefs.Preferences;

public class MainWindowController {
    
    
    private CompilerBuilder compOptions;
    Preferences prefs;
    
    @FXML
    public TextField chosenFilePath;
    
    @FXML
    public Button outputFileButton;
    
    @FXML
    public Button compileButton;
    
    @FXML
    public CheckBox overrideOutputPath;
    
    @FXML
    public TextField chosenOutputPath;
    
    @FXML
    public ProgressIndicator spinner;
    
    public void ChooseInputFileButtonAction (ActionEvent e)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter msharpExtensionFilter =
                new FileChooser.ExtensionFilter(
                        "MSharp - Source file (.pdf)", "*.msharp");
        setupFileChooserBasedOnCurrentlySelected(fc, msharpExtensionFilter, chosenFilePath);
        File selected = fc.showOpenDialog(null);
        if (selected != null)
            setInputPath(selected.getAbsolutePath());
    
    }
    
    private void setupFileChooserBasedOnCurrentlySelected (FileChooser fc, FileChooser.ExtensionFilter extensionFilter, TextField textField)
    {
        fc.getExtensionFilters().add(extensionFilter);
        fc.setSelectedExtensionFilter(extensionFilter);
        File selectedFile = new File(chosenFilePath.getText());
        if(!selectedFile.exists()) selectedFile = new File(System.getProperty("user.dir"));
        if (!selectedFile.isDirectory()) selectedFile = new File(selectedFile.getParent());
        fc.setInitialDirectory(selectedFile);
    }
    
    public void ChooseOutputFileButtonAction (ActionEvent e)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter schemExtensionFilter =
                new FileChooser.ExtensionFilter(
                        "Minecraft Schematic File (.schem)", "*.schem");
        setupFileChooserBasedOnCurrentlySelected(fc, schemExtensionFilter, chosenOutputPath);
        File selected = fc.showSaveDialog(null);
        if (selected != null)
            chosenOutputPath.setText(selected.getAbsolutePath());
    
    }
    
    private static final String prefInputPath = "INPUT_PATH";
    private static final String prefOutputPath = "OUTPUT_PATH";
    private static final String prefOverrideOutputPath = "OVERRIDE_OUTPUT_PATH";
    private static final String prefGenerateAst = "FLAG_GENERATE_AST";
    private static final String prefTurnAroundLength = "FLAG_TURN_AROUND_LENGTH";
    private static final String prefFillerBlock = "FLAG_FILLER_BLOCK";
    
    
    public void initialize ()
    {
        compOptions = new CompilerBuilder();
        
        // lets see if we have some saved settings..
        prefs = Preferences.userNodeForPackage(this.getClass());
        
        chosenFilePath.setText(prefs.get(prefInputPath,""));
        overrideOutputPath.setSelected(prefs.getBoolean(prefOverrideOutputPath,false));
        if(overrideOutputPath.isSelected()){
            chosenOutputPath.setText(prefs.get(prefOutputPath,""));
        }
        else{
            setInputPath(chosenFilePath.getText()); // resets the disabled output text field to a proper value.
        }
        // dependent on the preferences, enable or disable output path.
        outputFileButton.setDisable(!overrideOutputPath.isSelected());
        chosenOutputPath.setDisable(!overrideOutputPath.isSelected());
        
        // comp options
        compOptions.setGenerateAst(prefs.getBoolean(prefGenerateAst,false));
        compOptions.setTurnAroundLength(prefs.getInt(prefTurnAroundLength, 20));
        compOptions.setFillerBlock(prefs.get(prefFillerBlock,"minecraft:stone"));
    }
    public void overrideOutputPathAction(ActionEvent e){
        outputFileButton.setDisable(!overrideOutputPath.isSelected());
        chosenOutputPath.setDisable(!overrideOutputPath.isSelected());

        // reset the output destination to the generated path based on input path.
        setInputPath(chosenFilePath.getText());
    }
    
    public void CompileButtonAction (ActionEvent e)
    {
        //todo clear the log
        compOptions.setInputPath(chosenFilePath.getText());
        compOptions.setOutputPath(chosenOutputPath.getText());
        
        // lets save the used options in user preferences.
        prefs.put(prefInputPath,compOptions.getInputPath());
        prefs.putBoolean(prefOverrideOutputPath,overrideOutputPath.isSelected());
        prefs.put(prefOutputPath,compOptions.getOutputPath());
        prefs.putBoolean(prefGenerateAst,compOptions.isGenerateAst());
        prefs.putInt(prefTurnAroundLength, compOptions.getTurnAroundLength());
        prefs.put(prefFillerBlock,compOptions.getFillerBlock());

        // and compile.
        Compiler comp = compOptions.buildCompiler();
    
        // oh, and also we want to disable the compile button, and enable a load icon.
        compileButton.setDisable(true);
        spinner.setVisible(true);
        spinner.setProgress(-1d);
        

        // Because compiling, especially when generating the AST-svg can take some time, and we meanwhile want the program to not freeze, we want to run this in another thread.
        Task<Void> task = new Task<Void>(){
    
            @Override
            protected Void call () throws Exception
            {
                comp.tryCompile();
                return null;
            }
        };
        
        task.setOnSucceeded(event ->{
            spinner.setVisible(false);
            compileButton.setDisable(false);
        });
        
        Thread thread =  new Thread (task);
        thread.start();
    }
    
    
    private void setInputPath(String inputPath){
        chosenFilePath.setText(inputPath);
        if(!overrideOutputPath.isSelected()) {
            if (!inputPath.contains("."))
                chosenOutputPath.setText(""); // user haven't selected a file for some reason. We cant really handle that here.
            chosenOutputPath.setText(inputPath.substring(0, inputPath.lastIndexOf(".")) + ".schem");
        }
    }
    
    public void openAdvancedOptions (ActionEvent e)
    {
        AdvancedOptionsController.showStage(((Node) e.getSource()).getScene().getWindow(),compOptions);
    }
}
