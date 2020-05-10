package msharp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

public class LogView extends ListView<String> {
    public static class LogViewLogHandler extends Handler {
        Queue<LogRecord> logList = new LinkedList<LogRecord>();
        
        @Override
        public void publish (LogRecord logRecord)
        {
            logList.add(logRecord);
        }
        
        @Override
        public void flush ()
        {
            logList.clear();
        }
        
        @Override
        public void close () throws SecurityException
        {
            // we dont need to close anything..
        }
    }
    
    private LogViewLogHandler logHandler;
    
    
    public LogView(){
        // set the handler up.
        logHandler = new LogViewLogHandler();
        LogManager.getLogManager().getLogger("").addHandler(logHandler);
        
        
        // setup styling
        this.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem (String item, boolean empty)
            {
                super.updateItem(item, empty);
            
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: white;-fx-text-fill:black;");
                } else if (item.contains("[FATAL]")) {
                
                    setText(item);
                    setStyle("-fx-background-color: orange;-fx-text-fill:black;");
                
                } else if (item.contains("[SUCCESS]")) {
                
                    setText(item);
                    setStyle("-fx-background-color: lightgreen;-fx-text-fill:black;");
                
                } else if (item.contains("[WARNING]")) {
                
                    setText(item);
                    setStyle("-fx-background-color: lightgreen;-fx-text-fill:black;");
                
                } else {
                
                    setText(item);
                    setStyle("-fx-background-color: white;-fx-text-fill:black;");
                }
            }
        });
        
        
        // begin refreshing
        Timeline logTransfer = new Timeline(
                new KeyFrame(
                        Duration.millis(100),
                        event -> {
                            update();
                        }
                )
        );
        logTransfer.setCycleCount(Timeline.INDEFINITE);
        logTransfer.play();
    }
    
    
    
    private void update(){
        // basically add whatever new log there is.
        
        while(logHandler.logList.size() > 0){
            LogRecord logRecord = logHandler.logList.remove();
            String pattern = " HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date(logRecord.getMillis()));
            String sb = date +
                    ": " +
                    logRecord.getMessage();
            this.getItems().add(sb);
        }
        this.scrollTo(this.getItems().size() - 1);
    }
    
}
