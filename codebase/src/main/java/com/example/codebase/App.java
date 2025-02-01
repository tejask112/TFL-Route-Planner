package com.example.codebase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class App extends Application {

  private final int width = 700;
  private final int height = 500;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();

    //Top title bar
    HBox topBar = new HBox();
    topBar.setStyle("-fx-background-color: #181818; -fx-padding: 15");
    topBar.setAlignment(Pos.CENTER);
    root.setTop(topBar);

    Scene scene = new Scene(root, 900, 500);
    stage.setScene(scene);
    stage.show();

  }
}
