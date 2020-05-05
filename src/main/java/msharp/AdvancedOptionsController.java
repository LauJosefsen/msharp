package msharp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import msharp.Compiler.CompilerBuilder;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class AdvancedOptionsController {
    CompilerBuilder compOptions = new CompilerBuilder();
    
    public CheckBox astPdf;
    public CheckBox shortenRedstone;
    public TextField turnAroundLength;
    public TextField fillerBlock;
    
    public void initialize ()
    {
        
        // only allow integers in turnAroundLength
        // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            
            if (text.matches("[0-9]*")) {
                return change;
            }
            
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        turnAroundLength.setTextFormatter(textFormatter);
    }
    
    public AdvancedOptionsController showStage (Window window)
    {
        Parent root = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("AdvancedOptions.fxml"));
            root = loader.load();//FXMLLoader.load(getClass().getClassLoader().getResource("AdvancedOptions.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace(); //todo fix this
        }
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Advanced options");
        assert root != null;
        dialog.setScene(new Scene(root));
        dialog.initOwner(window);
        
        
        dialog.showAndWait();
        
        return loader.getController();
    }
    
    public CompilerBuilder getCompOptions ()
    {
        return compOptions;
    }
    
    public void saveOptionsAction (ActionEvent e)
    {
        
        compOptions.setFillerBlock(fillerBlock.getText());
        compOptions.setTurnAroundLength(Integer.parseInt(turnAroundLength.getText()));
        compOptions.setShortenRedstone(shortenRedstone.isSelected());
        compOptions.setGenerateAst(astPdf.isSelected());
        
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
