package com.example.codebase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.StageStyle;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();

    // ---------------------------- Top title bar ----------------------------
    HBox topBar = new HBox();
    topBar.setStyle("-fx-background-color: #181818; -fx-padding: 15");

    // introducing the tfl logo
    Image logo = new Image(getClass().getResourceAsStream("/images/tfl-logo.jpg"));
    ImageView logoView = new ImageView(logo);
    logoView.setFitHeight(30);
    logoView.setPreserveRatio(true);
    logoView.setStyle("-fx-padding: 0 0 0 100");

    // introducing the thames illustration logo
    Image thames = new Image(getClass().getResourceAsStream("/images/thames-logo.png"));
    ImageView thamesView = new ImageView(thames);
    thamesView.setFitHeight(30);
    thamesView.setPreserveRatio(true);

    HBox.setMargin(logoView, new javafx.geometry.Insets(0, 10, 0, 0));
    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.getChildren().addAll(logoView, thamesView);
    root.setTop(topBar);

    // ---------------------------- Bottom Half HBox ----------------------------
    HBox bottomHalf = new HBox();

    VBox userInput = new VBox();
    userInput.setStyle("-fx-background-color: blue");
    VBox resultBox = new VBox();
    resultBox.setStyle("-fx-background-color: green");
    userInput.setPrefWidth(225);
    resultBox.setPrefWidth(575);
    HBox.setHgrow(userInput, Priority.ALWAYS);
    HBox.setHgrow(resultBox, Priority.ALWAYS);

    bottomHalf.getChildren().addAll(userInput,resultBox);
    root.setCenter(bottomHalf);

    // ---------------------------- UserInput VBox ----------------------------

    Scene scene = new Scene(root, 900, 500);
    stage.setScene(scene);
    stage.show();

  }
}
