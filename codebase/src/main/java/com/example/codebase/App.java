package com.example.codebase;

import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();

    // ---------------------------- Top title bar ----------------------------
    HBox topBar = new HBox();
    topBar.setStyle("-fx-background-color: #2d3039");

    // introducing the tfl logo
    Image logo = new Image(getClass().getResourceAsStream("/images/tfl-logo.jpg"));
    ImageView logoView = new ImageView(logo);
    logoView.setFitHeight(70);
    logoView.setPreserveRatio(true);
    logoView.setStyle("-fx-translate-x: 25px");

    // introducing the thames illustration logo
    Image thames = new Image(getClass().getResourceAsStream("/images/thames-logo.png"));
    ImageView thamesView = new ImageView(thames);
    thamesView.setFitHeight(50);
    thamesView.setPreserveRatio(true);
    thamesView.setStyle("-fx-translate-x: 25px");

    HBox.setMargin(logoView, new javafx.geometry.Insets(0, 10, 0, 0));
    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.getChildren().addAll(logoView, thamesView);
    root.setTop(topBar);

    // ---------------------------- Bottom Half HBox ----------------------------
    HBox bottomHalf = new HBox();

    VBox userInput = new VBox();
    userInput.setStyle("-fx-background-color: #181818");
    VBox resultBox = new VBox();
    resultBox.setStyle("-fx-background-color: blue");
    userInput.setPrefWidth(225);
    resultBox.setPrefWidth(575);
    HBox.setHgrow(userInput, Priority.ALWAYS);
    HBox.setHgrow(resultBox, Priority.ALWAYS);

    bottomHalf.getChildren().addAll(userInput,resultBox);
    root.setCenter(bottomHalf);

    // ---------------------------- UserInput VBox ----------------------------
    Label title = new Label("Plan a Journey");
    title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-background-color: #2d3039 ; -fx-text-fill: white; -fx-translate-x: 20px; -fx-translate-y: 40px; -fx-padding: 5 5 5 5;");



    ComboBox<Station> srcStation = new ComboBox<>();
    srcStation.getItems().addAll(EustonSquare, KingsCrossStPancras);
    srcStation.setConverter(new StringConverter<Station>() {
      @Override
      public String toString(Station station) {
        return station.getName();
      }

      @Override
      public Station fromString(String s) {
        return null;
      }
    });
    srcStation.setPromptText("From");

    userInput.getChildren().addAll(title, srcStation);

    Scene scene = new Scene(root, 1000, 650);
    stage.setScene(scene);
    stage.show();

  }
}
