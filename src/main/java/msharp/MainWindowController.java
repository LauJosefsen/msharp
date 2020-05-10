package msharp;

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

public class MainWindowController extends Handler {
    
    
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
    public ListView<String> log;
    
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
        
        log.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem (String item, boolean empty)
            {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setText(null);
                    setStyle(null);
                } else if (item.contains("[FATAL]")) {
                    
                    setText(item);
                    setStyle("-fx-background-color: orange;");
                    
                } else if (item.contains("[SUCCESS]")) {
                    
                    setText(item);
                    setStyle("-fx-background-color: lightgreen;");
                    
                } else if (item.contains("[WARNING]")) {
                    
                    setText(item);
                    setStyle("-fx-background-color: lightgreen;");
                    
                } else {
                    
                    setText(item);
                    setStyle("-fx-background-color: white;");
                }
            }
        });
    }
    public void overrideOutputPathAction(ActionEvent e){
        outputFileButton.setDisable(!overrideOutputPath.isSelected());
        chosenOutputPath.setDisable(!overrideOutputPath.isSelected());

        // reset the output destination to the generated path based on input path.
        setInputPath(chosenFilePath.getText());
    }
    
    public void CompileButtonAction (ActionEvent e)
    {
        log.getItems().clear();
        compOptions.setLoggerHandler(this);
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
        comp.tryCompile();
        
        // todo make this async. Will throw an error, because it will try to log to JavaFx which is not happy when performing changes from another thread.
        // todo should also remove the logger handler from this class... which is needed for aforementioned.
        // Because compiling, especially when generating the AST can take some time, and we meanwhile want the program to not freeze, we want to run this in another thread.
        // the double colon operator creates a reference to the methods.  https://www.geeksforgeeks.org/double-colon-operator-in-java/
        // new Thread (comp::tryCompile).start();
    }
    
    @Override
    public void publish (LogRecord logRecord)
    {
        String pattern = " HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date(logRecord.getMillis()));
        String sb = date +
                ": " +
                logRecord.getMessage();
        log.getItems().add(sb);
        log.scrollTo(log.getItems().size() - 1);
    }
    private void setInputPath(String inputPath){
        chosenFilePath.setText(inputPath);
        if(!overrideOutputPath.isSelected()) {
            if (!inputPath.contains("."))
                chosenOutputPath.setText(""); // user haven't selected a file for some reason. We cant really handle that here.
            chosenOutputPath.setText(inputPath.substring(0, inputPath.lastIndexOf(".")) + ".schem");
        }
    }
    
    @Override
    public void flush ()
    {
        log.getItems().removeAll();
        
    }
    
    @Override
    public void close () throws SecurityException
    {
        // o,O
    }
    
    public void openAdvancedOptions (ActionEvent e)
    {
        AdvancedOptionsController.showStage(((Node) e.getSource()).getScene().getWindow(),compOptions);
    }
}
