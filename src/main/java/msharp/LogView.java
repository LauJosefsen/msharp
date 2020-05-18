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

public class LogView extends ListView<LogRecord> {
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
    
    private final LogViewLogHandler logHandler;
    
    
    public LogView(){
        // set the handler up.
        logHandler = new LogViewLogHandler();
        LogManager.getLogManager().getLogger("").addHandler(logHandler);
        
        
        // setup styling
        this.setCellFactory(param -> new ListCell<LogRecord>() {
            @Override
            protected void updateItem (LogRecord item, boolean empty)
            {
                super.updateItem(item, empty);
            
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: white;-fx-text-fill:black;");
                    return;
                }
                
                String pattern = " HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date(item.getMillis()));
                String sb = date + ": ["+ item.getLevel() +"] " +item.getMessage();
                
                if (item.getLevel() == Level.SEVERE) {
                    setStyle("-fx-background-color: orange;-fx-text-fill:black;");
                
                } else if (item.getLevel() == Level.INFO) {
                    setStyle("-fx-background-color: white;-fx-text-fill:black;");
                
                } else if (item.getLevel() == Level.WARNING) {
                    setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
                }
                if(item.getMessage().contains("[SUCCESS]")){
                    // since logger cant handle this..
                    sb = date + ": " + item.getMessage();
                    setStyle("-fx-background-color: lightgreen;-fx-text-fill:black;");
                }
                setText(sb);
            }
        });
        
        
        // begin refreshing
        Timeline logTransfer = new Timeline(
                new KeyFrame(
                        Duration.millis(100),
                        event -> {
                            while(logHandler.logList.size() > 0){
                                this.getItems().add(logHandler.logList.remove());
                                this.scrollTo(this.getItems().size() - 1);
                            }
                        }
                )
        );
        logTransfer.setCycleCount(Timeline.INDEFINITE);
        logTransfer.play();
    }
    
}
