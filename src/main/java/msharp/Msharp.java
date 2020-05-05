package msharp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Msharp extends Application {
    
    public static void main (String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start (Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainWindow.fxml")); //todo might be null
        stage.setTitle("MSharp Compiler");
        stage.setScene(new Scene(root));
        stage.show();
    }
}