package msharp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainWindowController extends Handler {
    
    private CompilerBuilder compOptions;
    
    @FXML
    public Button chooseInputFileButton;
    
    @FXML
    public TextField chosenFilePath;
    
    @FXML
    public Button compileButton;
    
    
    @FXML
    public ListView<String> log;
    
    public void ChooseInputFileButtonAction (ActionEvent e)
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter msharpExtensionFilter =
                new FileChooser.ExtensionFilter(
                        "MSharp - Source file (.pdf)", "*.msharp");
        fc.getExtensionFilters().add(msharpExtensionFilter);
        fc.setSelectedExtensionFilter(msharpExtensionFilter);
        File selectedFile = new File(chosenFilePath.getText());
        if (!selectedFile.isDirectory()) selectedFile = new File(selectedFile.getParent());
        fc.setInitialDirectory(selectedFile);
        File selected = fc.showOpenDialog(null);
        if (selected != null)
            chosenFilePath.setText(selected.getAbsolutePath());
        
    }
    
    public void initialize ()
    {
        compOptions = new CompilerBuilder();
        
        chosenFilePath.setText(System.getProperty("user.dir"));
        
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
    
    public void CompileButtonAction (ActionEvent e)
    {
        log.getItems().clear();
        compOptions.setLoggerHandler(this);
        compOptions.setInputPath(chosenFilePath.getText());
        compOptions.setOutputPath(compOptions.getInputPath().substring(0, compOptions.getInputPath().lastIndexOf(".")) + ".schem");
        Compiler comp = compOptions.buildCompiler();
        comp.tryCompile();
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
        AdvancedOptionsController advancedOptions = new AdvancedOptionsController();
        advancedOptions = advancedOptions.showStage(((Node) e.getSource()).getScene().getWindow());
        compOptions = advancedOptions.getCompOptions();
        compOptions = advancedOptions.getCompOptions();
        
        
    }
}
