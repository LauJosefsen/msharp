package msharp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void setCompOptions (CompilerBuilder compOptions)
    {
        this.compOptions = compOptions;
        astPdf.setSelected(compOptions.isGenerateAst());
        turnAroundLength.setText(String.valueOf(compOptions.getTurnAroundLength()));
        fillerBlock.setText(compOptions.getFillerBlock());
    }
    
    CompilerBuilder compOptions;
    

    
    public CheckBox astPdf;
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
    
        // make sure length doesnt exceed 5 decimals.
        turnAroundLength.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (turnAroundLength.getText().length() > 5) {
                turnAroundLength.setText(turnAroundLength.getText().substring(0, 5));
            }
        });
    }
    
    public static void showStage (Window window, CompilerBuilder compOptions) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(AdvancedOptionsController.class.getClassLoader().getResource("AdvancedOptions.fxml"));
        Parent root = loader.load();
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Advanced options");
        dialog.setScene(new Scene(root));
        dialog.initOwner(window);
        
        ((AdvancedOptionsController) loader.getController()).setCompOptions(compOptions);
        
        dialog.showAndWait();
    }
    
    public void saveOptionsAction (ActionEvent e)
    {
        
        compOptions.setFillerBlock(fillerBlock.getText());
        compOptions.setTurnAroundLength(Integer.parseInt(turnAroundLength.getText()));
        compOptions.setGenerateAst(astPdf.isSelected());
        
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
