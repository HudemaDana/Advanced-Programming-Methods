package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent primaryWindow = primaryLoader.load();
        MainWindowController mainWindowController = primaryLoader.getController();
        Scene primaryScene = new Scene(primaryWindow);
        //primaryScene.getStylesheets().add(getClass().getResource("../Gui/css/StatementWindow.css").toExternalForm());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Main Window");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("StatementWindow.fxml"));
        Parent secondaryWindow = secondLoader.load();
        StatementsWindowController statementsWindowController = secondLoader.getController();
        statementsWindowController.setMainWindowController(mainWindowController);
        Scene secondaryScene = new Scene(secondaryWindow);
        secondaryScene.getStylesheets().add(getClass().getResource("../Gui/css/StatementWindow.css").toExternalForm());
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Select Window");
        secondaryStage.setScene(secondaryScene);
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}