package msharp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainWindowController extends Handler {

    @FXML
    public Button chooseInputFileButton;

    @FXML
    public TextField chosenFilePath;

    @FXML
    public Button compileButton;

    @FXML
    public ProgressIndicator progressIndicator;

    @FXML
    public ListView<String> log;

    public void ChooseInputFileButtonAction(ActionEvent e){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter msharpExtensionFilter =
                new FileChooser.ExtensionFilter(
                        "MSharp - Source file (.pdf)", "*.msharp");
        fc.getExtensionFilters().add(msharpExtensionFilter);
        fc.setSelectedExtensionFilter(msharpExtensionFilter);
        fc.setInitialDirectory(new File(chosenFilePath.getText()));
        File selected = fc.showOpenDialog(null);
        if(selected !=  null)
            chosenFilePath.setText(selected.getAbsolutePath());

    }

    public void initialize(){
        chosenFilePath.setText(System.getProperty("user.dir"));

        log.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle(null);
                }else if(item.contains("[FATAL]")){

                    setText(item);
                    setStyle("-fx-background-color: orange;");

                } else if(item.contains("[SUCCESS]")){

                    setText(item);
                    setStyle("-fx-background-color: lightgreen;");

                }
                else if(item.contains("[WARNING]")){

                    setText(item);
                    setStyle("-fx-background-color: lightgreen;");

                }
                else {

                    setText(item);
                    setStyle("-fx-background-color: white;");
                }
            }
        });
    }

    public void CompileButtonAction(ActionEvent e){
        log.getItems().clear();
        Compiler comp = new Compiler(chosenFilePath.getText(),this);
        progressIndicator.setProgress(0);
        comp.compile();
        progressIndicator.setProgress(100);
    }

    @Override
    public void publish(LogRecord logRecord) {
        String pattern = " HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date(logRecord.getMillis()));
        String sb = date +
                ": "+
                logRecord.getMessage();
        log.getItems().add(sb);
        log.scrollTo(log.getItems().size()-1);

    }

    @Override
    public void flush() {
        log.getItems().removeAll();

    }

    @Override
    public void close() throws SecurityException {
        // o,O
    }
}
