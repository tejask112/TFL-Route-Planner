package com.example.codebase;

import com.example.codebase.Network.Edge;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.StringConverter;
import java.time.LocalTime;
import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;


public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    BorderPane root = new BorderPane();

    // ---------------------------- Top title bar ----------------------------
    HBox topBar = new HBox();
    topBar.getStyleClass().add("topTitleBar");

    // introducing the tfl logo
    Image logo = new Image(getClass().getResourceAsStream("/images/tfl-logo.jpg"));
    ImageView logoView = new ImageView(logo);
    logoView.setFitHeight(70);
    logoView.setPreserveRatio(true);
    logoView.getStyleClass().add("tflLogo");

    // introducing the thames illustration logo
    Image thames = new Image(getClass().getResourceAsStream("/images/thames-logo.png"));
    ImageView thamesView = new ImageView(thames);
    thamesView.setFitHeight(50);
    thamesView.setPreserveRatio(true);
    thamesView.getStyleClass().add("thamesLogo");

    HBox.setMargin(logoView, new javafx.geometry.Insets(0, 10, 0, 0));
    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.getChildren().addAll(logoView, thamesView);

    HBox topBarButtons = new HBox();
    topBarButtons.getStyleClass().add("topBarButtons");
    topBar.getChildren().add(topBarButtons);

    // introducing the line status button
    Button lineStatusBtn = new Button("Line Status");
    lineStatusBtn.getStyleClass().add("topBarActualButtons");
    topBarButtons.getChildren().add(lineStatusBtn);

    // introducing the fares button
    Button faresBtn = new Button("Fares");
    faresBtn.getStyleClass().add("topBarActualButtons");
    topBarButtons.getChildren().add(faresBtn);

    root.setTop(topBar);

    // ---------------------------- Bottom Half HBox ----------------------------
    HBox bottomHalf = new HBox();

    VBox userInput = new VBox();
    userInput.getStyleClass().add("userInputBox");
    VBox resultBox = new VBox();
    resultBox.getStyleClass().add("resultsBox");
    userInput.setPrefWidth(225);
    resultBox.setPrefWidth(575);
    HBox.setHgrow(userInput, Priority.ALWAYS);
    HBox.setHgrow(resultBox, Priority.ALWAYS);

    bottomHalf.getChildren().addAll(userInput,resultBox);
    root.setCenter(bottomHalf);

    // ---------------------------- UserInput VBox ----------------------------
    Label title = new Label("Plan a Journey");
    title.getStyleClass().add("planJourneyTitle");

    Network tflNetwork = new Network();

    // Create the line object
    Line Metropolitan = new Line("Metropolitan", Boolean.TRUE);
    Line Piccadilly = new Line("Piccadilly", Boolean.FALSE);

    // Create stations
    Station Amersham = new Station("Amersham", new ArrayList<>(List.of(Metropolitan)), true, 9);
    Station ChalfontLatimer = new Station("Chalfont & Latimer", new ArrayList<>(List.of(Metropolitan)), true, 8);
    Station Chesham = new Station("Chesham", new ArrayList<>(List.of(Metropolitan)), true, 9);
    Station Rickmansworth = new Station("Rickmansworth", new ArrayList<>(List.of(Metropolitan)), true, 7);
    Station MoorPark = new Station("Moor Park", new ArrayList<>(List.of(Metropolitan)), true, 6);
    Station Northwood = new Station("Northwood", new ArrayList<>(List.of(Metropolitan)), true, 6);
    Station NorthwoodHills = new Station("Northwood Hills", new ArrayList<>(List.of(Metropolitan)), false, 6);
    Station Pinner = new Station("Pinner", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station NorthHarrow = new Station("North Harrow", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station HarrowOnTheHill = new Station("Harrow-on-the-Hill", new ArrayList<>(List.of(Metropolitan)), true, 5);
    Station WembleyPark = new Station("Wembley Park", new ArrayList<>(List.of(Metropolitan)), true, 4);
    Station FinchleyRoad = new Station("Finchley Road", new ArrayList<>(List.of(Metropolitan)), true, 2);
    Station BakerStreet = new Station("Baker Street", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station GreatPortlandStreet = new Station("Great Portland Street", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station EustonSquare = new Station("Euston Square", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station KingsCrossStPancras = new Station("King's Cross St. Pancras", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station Farringdon = new Station("Farringdon", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station Barbican = new Station("Barbican", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station Moorgate = new Station("Moorgate", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station LiverpoolStreet = new Station("Liverpool Street", new ArrayList<>(List.of(Metropolitan)), true, 1);
    Station Chorleywood = new Station("Chorleywood", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station NorthwickPark = new Station("Northwick Park", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station PrestonRoad = new Station("Preston Road", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station Aldgate = new Station("Aldgate", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station WestHarrow = new Station("West Harrow", new ArrayList<>(List.of(Metropolitan)), false, 5);
    Station RaynersLane = new Station("Rayners Lane", new ArrayList<>(List.of(Metropolitan, Piccadilly)), false, 5);
    Station Eastcote = new Station("Eastcote", new ArrayList<>(List.of(Metropolitan, Piccadilly)), false, 5);
    Station RuislipManor = new Station("Ruislip Manor", new ArrayList<>(List.of(Metropolitan, Piccadilly)), false, 6);
    Station Ruislip = new Station("Ruislip", new ArrayList<>(List.of(Metropolitan, Piccadilly)), false, 6);
    Station Ickenham = new Station("Ickenham", new ArrayList<>(List.of(Metropolitan, Piccadilly)), false, 6);
    Station Hillingdon = new Station("Hillingdon", new ArrayList<>(List.of(Metropolitan, Piccadilly)), true, 6);
    Station Uxbridge = new Station("Uxbridge", new ArrayList<>(List.of(Metropolitan, Piccadilly)), true, 6);
    Station Croxley = new Station("Croxley", new ArrayList<>(List.of(Metropolitan)), true, 7);
    Station Watford = new Station("Watford", new ArrayList<>(List.of(Metropolitan)), true, 7);
    Station Cockfosters = new Station("Cockfosters", new ArrayList<>(List.of(Piccadilly)), false, 5);
    Station Oakwood = new Station("Oakwood", new ArrayList<>(List.of(Piccadilly)), false, 5);
    Station Southgate = new Station("Southgate", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station ArnosGrove = new Station("Arnos Grove", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station BoundsGreen = new Station("Bounds Green", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station WoodGreen = new Station("Wood Green", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station TurnpikeLane = new Station("Turnpike Lane", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station ManorHouse = new Station("Manor House", new ArrayList<>(List.of(Piccadilly)), false, 2);
    Station FinsburyPark = new Station("Finsbury Park", new ArrayList<>(List.of(Piccadilly)), false, 2);
    Station RussellSquare = new Station("Russell Square", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station Holborn = new Station("Holborn", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station CoventGarden = new Station("Covent Garden", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station LeicesterSquare = new Station("Leicester Square", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station PiccadillyCircus = new Station("Piccadilly Circus", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station GreenPark = new Station("Green Park", new ArrayList<>(List.of(Piccadilly)), true, 1);
    Station HydeParkCorner = new Station("Hyde Park Corner", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station Knightsbridge = new Station("Knightsbridge", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station SouthKensington = new Station("South Kensington", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station GloucesterRoad = new Station("Gloucester Road", new ArrayList<>(List.of(Piccadilly)), false, 1);
    Station EarlsCourt = new Station("Earl's Court", new ArrayList<>(List.of(Piccadilly)), false, 2);
    Station BaronsCourt = new Station("Barons Court", new ArrayList<>(List.of(Piccadilly)), false, 2);
    Station Hammersmith = new Station("Hammersmith", new ArrayList<>(List.of(Piccadilly)), true, 2);
    Station TurnhamGreen = new Station("Turnham Green", new ArrayList<>(List.of(Piccadilly)), false, 2);
    Station ActonTown = new Station("Acton Town", new ArrayList<>(List.of(Piccadilly)), true, 3);
    Station EalingCommon = new Station("Ealing Common", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station NorthEaling = new Station("North Ealing", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station ParkRoyal = new Station("Park Royal", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station Alperton = new Station("Alperton", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station SudburyTown = new Station("Sudbury Town", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station SudburyHill = new Station("Sudbury Hill", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station SouthHarrow = new Station("South Harrow", new ArrayList<>(List.of(Piccadilly)), false, 5);
    Station SouthEaling = new Station("South Ealing", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station Northfields = new Station("Northfields", new ArrayList<>(List.of(Piccadilly)), false, 3);
    Station BostonManor = new Station("Boston Manor", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station Osterley = new Station("Osterley", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station HounslowEast = new Station("Hounslow East", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station HounslowCentral = new Station("Hounslow Central", new ArrayList<>(List.of(Piccadilly)), false, 4);
    Station HounslowWest = new Station("Hounslow West", new ArrayList<>(List.of(Piccadilly)), false, 5);
    Station HattonCross = new Station("Hatton Cross", new ArrayList<>(List.of(Piccadilly)), false, 5);
    Station HeathrowTerminals2_3 = new Station("Heathrow Terminals 2 & 3", new ArrayList<>(List.of(Piccadilly)), true, 6);
    Station HeathrowTerminal4 = new Station("Heathrow Terminal 4", new ArrayList<>(List.of(Piccadilly)), true, 6);
    Station HeathrowTerminal5 = new Station("Heathrow Terminal 5", new ArrayList<>(List.of(Piccadilly)), true, 6);

    tflNetwork.addStation(new ArrayList<>(List.of(
        // Metropolitan (main snippet):
        Amersham, ChalfontLatimer, Chesham, Rickmansworth, MoorPark, Northwood, NorthwoodHills,
        Pinner, NorthHarrow, HarrowOnTheHill, WembleyPark, FinchleyRoad, BakerStreet,
        GreatPortlandStreet, EustonSquare, KingsCrossStPancras, Farringdon, Barbican, Moorgate,
        LiverpoolStreet, Aldgate, Chorleywood, NorthwickPark, PrestonRoad,
        WestHarrow, RaynersLane, Eastcote, RuislipManor, Ruislip, Ickenham, Hillingdon, Uxbridge,
        Croxley, Watford, Cockfosters, Oakwood, Southgate, ArnosGrove, BoundsGreen, WoodGreen,
        TurnpikeLane, ManorHouse, FinsburyPark, RussellSquare, Holborn, CoventGarden, LeicesterSquare,
        PiccadillyCircus, GreenPark, HydeParkCorner, Knightsbridge, SouthKensington,
        GloucesterRoad, EarlsCourt, BaronsCourt, Hammersmith, TurnhamGreen, ActonTown,
        EalingCommon, NorthEaling, ParkRoyal, Alperton, SudburyTown, SudburyHill, SouthHarrow,
        SouthEaling, Northfields, BostonManor, Osterley, HounslowEast, HounslowCentral,
        HounslowWest, HattonCross, HeathrowTerminals2_3, HeathrowTerminal4, HeathrowTerminal5
    )));


    tflNetwork.addEdge(Amersham, ChalfontLatimer, Metropolitan, 5, "Westbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(ChalfontLatimer, Amersham, Metropolitan, 5, "Eastbound", new ArrayList<>(List.of("Metropolitan Amersham")));
    tflNetwork.addEdge(ChalfontLatimer, Chesham, Metropolitan, 8, "Northbound", new ArrayList<>(List.of("Metropolitan Chesham")));
    tflNetwork.addEdge(Chesham, ChalfontLatimer, Metropolitan, 8, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(ChalfontLatimer, Chorleywood, Metropolitan, 4, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Chorleywood, ChalfontLatimer, Metropolitan, 4, "Westbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham")));
    tflNetwork.addEdge(Chorleywood, Rickmansworth, Metropolitan, 5, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham")));
    tflNetwork.addEdge(Rickmansworth, Chorleywood, Metropolitan, 5, "Westbound", new ArrayList<>(List.of("Metropolitan Amersham","Metropolitan Chesham")));
    tflNetwork.addEdge(Rickmansworth, MoorPark, Metropolitan, 6, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham")));
    tflNetwork.addEdge(MoorPark, Rickmansworth, Metropolitan, 6, "Westbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(MoorPark, Northwood, Metropolitan, 4, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Northwood, MoorPark, Metropolitan, 4, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Northwood, NorthwoodHills, Metropolitan, 3, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthwoodHills, Northwood, Metropolitan, 3, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthwoodHills, Pinner, Metropolitan, 3, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Pinner, NorthwoodHills, Metropolitan, 3, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Pinner, NorthHarrow, Metropolitan, 2, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthHarrow, Pinner, Metropolitan, 2, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthHarrow, HarrowOnTheHill, Metropolitan, 4, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(HarrowOnTheHill, NorthHarrow, Metropolitan, 4, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(HarrowOnTheHill, NorthwickPark, Metropolitan, 2, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(NorthwickPark, HarrowOnTheHill, Metropolitan, 2, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(NorthwickPark, PrestonRoad, Metropolitan, 2, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(PrestonRoad, NorthwickPark, Metropolitan, 2, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(PrestonRoad, WembleyPark, Metropolitan, 2, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(WembleyPark, PrestonRoad, Metropolitan, 2, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(WembleyPark, FinchleyRoad, Metropolitan, 8, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(FinchleyRoad, WembleyPark, Metropolitan, 8, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(FinchleyRoad, BakerStreet, Metropolitan, 5, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(BakerStreet, FinchleyRoad, Metropolitan, 5, "Northbound", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(BakerStreet, GreatPortlandStreet, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(GreatPortlandStreet, BakerStreet, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(GreatPortlandStreet, EustonSquare, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(EustonSquare, GreatPortlandStreet, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(EustonSquare, KingsCrossStPancras, Metropolitan, 3, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(KingsCrossStPancras, EustonSquare, Metropolitan, 3, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(KingsCrossStPancras, Farringdon, Metropolitan, 4, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(Farringdon, KingsCrossStPancras, Metropolitan, 4, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Farringdon, Barbican, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(Barbican, Farringdon, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Barbican, Moorgate, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(Moorgate, Barbican, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Moorgate, LiverpoolStreet, Metropolitan, 3, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham", "Metropolitan Watford", "Metropolitan Uxbridge")));
    tflNetwork.addEdge(LiverpoolStreet, Moorgate, Metropolitan, 3, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(LiverpoolStreet, Aldgate, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Aldgate, LiverpoolStreet, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(HarrowOnTheHill, WestHarrow, Metropolitan, 2, "Northbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(WestHarrow, HarrowOnTheHill, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(WestHarrow, RaynersLane, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(RaynersLane, WestHarrow, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(RaynersLane, Eastcote, Metropolitan, 3, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Eastcote, RaynersLane, Metropolitan, 3, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Eastcote, RuislipManor, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(RuislipManor, Eastcote, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(RuislipManor, Ruislip, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Ruislip, RuislipManor, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Ruislip, Ickenham, Metropolitan, 3, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Ickenham, Ruislip, Metropolitan, 3, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Ickenham, Hillingdon, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Hillingdon, Ickenham, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Metropolitan, 2, "Westbound", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Uxbridge, Hillingdon, Metropolitan, 2, "Eastbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(MoorPark, Croxley, Metropolitan, 3, "Northbound", new ArrayList<>(List.of("Metropolitan Watford")));
    tflNetwork.addEdge(Croxley, MoorPark, Metropolitan, 3, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Croxley, Watford, Metropolitan, 5, "Northbound", new ArrayList<>(List.of("Metropolitan Watford")));
    tflNetwork.addEdge(Watford, Croxley, Metropolitan, 5, "Southbound", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Cockfosters, Oakwood, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Oakwood, Cockfosters, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Oakwood, Southgate, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Southgate, Oakwood, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Southgate, ArnosGrove, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ArnosGrove, Southgate, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ArnosGrove, BoundsGreen, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(BoundsGreen, ArnosGrove, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BoundsGreen, WoodGreen, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(WoodGreen, BoundsGreen, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(WoodGreen, TurnpikeLane, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(TurnpikeLane, WoodGreen, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(TurnpikeLane, ManorHouse, Piccadilly, 3, "Southbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ManorHouse, TurnpikeLane, Piccadilly, 3, "Northbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ManorHouse, FinsburyPark, Piccadilly, 2, "Southbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(FinsburyPark, ManorHouse, Piccadilly, 2, "Northbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(FinsburyPark, KingsCrossStPancras, Piccadilly, 5, "Southbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(KingsCrossStPancras, FinsburyPark, Piccadilly, 5, "Northbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(KingsCrossStPancras, RussellSquare, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(RussellSquare, KingsCrossStPancras, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RussellSquare, Holborn, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Holborn, RussellSquare, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Holborn, CoventGarden, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(CoventGarden, Holborn, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(CoventGarden, LeicesterSquare, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(LeicesterSquare, CoventGarden, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(LeicesterSquare, PiccadillyCircus, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(PiccadillyCircus, LeicesterSquare, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(PiccadillyCircus, GreenPark, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(GreenPark, PiccadillyCircus, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(GreenPark, HydeParkCorner, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(HydeParkCorner, GreenPark, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HydeParkCorner, Knightsbridge, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Knightsbridge, HydeParkCorner, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Knightsbridge, SouthKensington, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(SouthKensington, Knightsbridge, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthKensington, GloucesterRoad, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(GloucesterRoad, SouthKensington, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(GloucesterRoad, EarlsCourt, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(EarlsCourt, GloucesterRoad, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(EarlsCourt, BaronsCourt, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(BaronsCourt, EarlsCourt, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BaronsCourt, Hammersmith, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Hammersmith, BaronsCourt, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hammersmith, TurnhamGreen, Piccadilly, 4, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(TurnhamGreen, Hammersmith, Piccadilly, 4, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(TurnhamGreen, ActonTown, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ActonTown, TurnhamGreen, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ActonTown, EalingCommon, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(EalingCommon, ActonTown, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(EalingCommon, NorthEaling, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(NorthEaling, EalingCommon, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(NorthEaling, ParkRoyal, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(ParkRoyal, NorthEaling, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ParkRoyal, Alperton, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Alperton, ParkRoyal, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Alperton, SudburyTown, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SudburyTown, Alperton, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SudburyTown, SudburyHill, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SudburyHill, SudburyTown, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SudburyHill, SouthHarrow, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SouthHarrow, SudburyHill, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthHarrow, RaynersLane, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(RaynersLane, SouthHarrow, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RaynersLane, Eastcote, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Eastcote, RaynersLane, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Eastcote, RuislipManor, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(RuislipManor, Eastcote, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RuislipManor, Ruislip, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Ruislip, RuislipManor, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Ruislip, Ickenham, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Ickenham, Ruislip, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Ickenham, Hillingdon, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Hillingdon, Ickenham, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Uxbridge, Hillingdon, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Uxbridge"))); //duplicate line - remove this one.
    tflNetwork.addEdge(ActonTown, SouthEaling, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(SouthEaling, ActonTown, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthEaling, Northfields, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(Northfields, SouthEaling, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Northfields, BostonManor, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(BostonManor, Northfields, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BostonManor, Osterley, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(Osterley, BostonManor, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Osterley, HounslowEast, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowEast, Osterley, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowEast, HounslowCentral, Piccadilly, 1, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowCentral, HounslowEast, Piccadilly, 1, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowCentral, HounslowWest, Piccadilly, 2, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowWest, HounslowCentral, Piccadilly, 2, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowWest, HattonCross, Piccadilly, 3, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HattonCross, HounslowWest, Piccadilly, 3, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HattonCross, HeathrowTerminal4, Piccadilly, 4, "Southbound", new ArrayList<>(List.of("Piccadilly Heathrow T4")));
    tflNetwork.addEdge(HeathrowTerminal4, HattonCross, Piccadilly, 4, "Northbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HattonCross, HeathrowTerminals2_3, Piccadilly, 3, "Northbound", new ArrayList<>(List.of("Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HeathrowTerminals2_3, HattonCross, Piccadilly, 3, "Southbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal5, Piccadilly, 4, "Westbound", new ArrayList<>(List.of("Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HeathrowTerminal5, HeathrowTerminals2_3, Piccadilly, 4, "Eastbound", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HeathrowTerminal4, HeathrowTerminals2_3, Piccadilly, 4, "Northbound", new ArrayList<>(List.of("Piccadilly Heathrow T5"))); // Check direction
    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal4, Piccadilly, 4, "Southbound", new ArrayList<>(List.of("Piccadilly Heathrow T4"))); // Check direction

    ObservableList<Station> stationList = FXCollections.observableArrayList(tflNetwork.getStations());

    ComboBox<Station> srcStation = new ComboBox<>(stationList);
    srcStation.setPromptText("Departure");
    srcStation.setStyle("-fx-translate-y: 50px; -fx-translate-x: 20px;");
    srcStation.getStyleClass().add("stationComboBox");

    ComboBox<Station> destStation = new ComboBox<>(stationList);
    destStation.setPromptText("Arrival");
    destStation.setStyle("-fx-translate-y: 60px; -fx-translate-x: 20px;");
    destStation.getStyleClass().add("stationComboBox");

    Label leaveTitle = new Label("Leaving: ");
    leaveTitle.getStyleClass().addAll("leaveText", "leaveText-Title");
    Label leaveText = new Label("Now");
    leaveText.getStyleClass().addAll("leaveText", "leaveText-Text");
    GridPane gridPane = new GridPane();
    gridPane.add(leaveTitle, 0, 0); // Column 0, Row 0
    gridPane.add(leaveText, 1, 0);  // Column 1, Row 0
    userInput.setMargin(gridPane, new Insets(65, 0, 0, 25));

    Button submit = new Button("Find quickest route");
    submit.getStyleClass().add("planJourneyButton");
    userInput.setMargin(submit, new Insets(10, 0, 0, 20));
    Label errorLabel = new Label("");
    errorLabel.getStyleClass().add("noStationsError");
    userInput.getChildren().addAll(title, srcStation, destStation, gridPane, submit, errorLabel);

    // event handler for the line status button
    EventHandler<ActionEvent> lineStats = new EventHandler<ActionEvent>() {
      @Override
      public void handle (ActionEvent actionEvent) {
        Platform.runLater(() -> {
          resultBox.getChildren().clear();

          // generate the top title bar
          HBox lineStatusTitleBox = new HBox();
          Label lineStatusTitle = new Label("Current Line Status");
          lineStatusTitleBox.getChildren().add(lineStatusTitle);
          lineStatusTitleBox.setPrefWidth(resultBox.getPrefWidth());
          lineStatusTitle.getStyleClass().add("resultBoxTitle");
          resultBox.getChildren().add(lineStatusTitleBox);

          // generating vbox to output to
          VBox lineStatusResultBox = new VBox();

          // generate ScrollPane
          ScrollPane scrollPane = new ScrollPane(lineStatusResultBox);
          scrollPane.setFitToWidth(true);
          scrollPane.getStyleClass().add("lineStatusResultBox");

          // generating tfl colours hashmap
          Map<String, String> lineColoursString = new HashMap<>();
          lineColoursString.put("Bakerloo", "#B36305");
          lineColoursString.put("Central", "#E32017");
          lineColoursString.put("Circle", "#FFD300");
          lineColoursString.put("District", "#00782A");
          lineColoursString.put("Hammersmith & City", "#F3A9BB");
          lineColoursString.put("Jubilee", "#A0A5A9");
          lineColoursString.put("Metropolitan", "#9B0056");
          lineColoursString.put("Northern", "#000000");
          lineColoursString.put("Piccadilly", "#003688");
          lineColoursString.put("Victoria", "#0098D4");
          lineColoursString.put("Waterloo & City", "#95CDBA");

          // make API call
          ArrayList<ArrayList<String>> liveStatusAPIresponse = getLiveStatuses();
          for (ArrayList<String> lineDetails : liveStatusAPIresponse) {
            HBox individualLine = new HBox();
            individualLine.getStyleClass().add("individualLine");
            String name = lineDetails.get(0);
            String statusDescription = lineDetails.get(1);
            String reason = lineDetails.get(2);

            Label lineName = new Label(name);
            if (name.contains("&")) {
              lineName.setStyle("-fx-text-fill: black; -fx-background-color: " + lineColoursString.get(name) + ";");
            } else {
              lineName.setStyle("-fx-text-fill: white; -fx-background-color: " + lineColoursString.get(name) + ";");
            }
            lineName.getStyleClass().add("lineNameBoxes");
            lineName.setMinWidth(175);
            individualLine.getChildren().add(lineName);

            if (reason.equals("")) {
              Label statusDescriptionLabel = new Label(statusDescription);
              statusDescriptionLabel.getStyleClass().add("individualStatusDescription");
              statusDescriptionLabel.getStyleClass().add("individualStatusGoodServiceText");
              individualLine.getChildren().add(statusDescriptionLabel);
            } else {
              VBox innerStatusDescriptionBox = new VBox();
              innerStatusDescriptionBox.getStyleClass().add("individualStatusDescription");
              Label statusDescriptionLabel = new Label(statusDescription);
              statusDescriptionLabel.getStyleClass().add("individualStatusDelaysText");
              statusDescriptionLabel.setWrapText(true);
              Label reasonLabel = new Label(reason);
              reasonLabel.getStyleClass().add("individualStatusDelaysTextDescription");
              reasonLabel.setWrapText(true);
              reasonLabel.setMaxWidth(Double.MAX_VALUE);
              reasonLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
              HBox.setHgrow(reasonLabel, Priority.ALWAYS); // Ensure it expands as needed

              innerStatusDescriptionBox.getChildren().addAll(statusDescriptionLabel, reasonLabel);
              individualLine.getChildren().add(innerStatusDescriptionBox);

            }

            lineStatusResultBox.getChildren().add(individualLine);
          }

          resultBox.getChildren().add(scrollPane);

        });
      }
    };

    // event handler for the submit button
    EventHandler<ActionEvent> routePlanner = new EventHandler<ActionEvent>() {
      public void handle (ActionEvent e) {
        try {
          if (srcStation.getValue() == null || destStation.getValue() == null){
            throw new Exception("Departure and Arrival Stations Required!");
          } else {
            errorLabel.setText("");
            LinkedList<Edge> route = tflNetwork.findRoute(srcStation.getValue(), destStation.getValue());
            System.out.println("ROUTE: " + route);
            try {
              if (route.isEmpty()) {
                throw new Exception("Departure and Arrival stations must be distinct");
              } else {
                Platform.runLater(() -> {
                  resultBox.getChildren().clear();

                  // generates the top title bar
                  HBox destinationMessageHBox = new HBox();
                  Label destinationMessage = new Label("Your Route to "+destStation.getValue().getName());
                  destinationMessageHBox.getChildren().add(destinationMessage);
                  destinationMessageHBox.setPrefWidth(resultBox.getPrefWidth());
                  destinationMessage.getStyleClass().add("resultBoxTitle");
                  resultBox.getChildren().add(destinationMessageHBox);

                  // generating the hashmap for the different lines and their colours
                  Map<Line, String> lineColours = new HashMap<>();
                  lineColours.put(Metropolitan, "#9B0056");
                  lineColours.put(Piccadilly, "#003688");

                  // generating the outerHbox which contains the station lines and station descriptions
                  HBox outerHbox = new HBox();
                  HBox outerExtenderHBox = new HBox();
                  outerExtenderHBox.setPrefHeight(40);

                  VBox outerOuterHBox = new VBox(outerHbox, outerExtenderHBox);
                  VBox timeAndRouteBox = new VBox();
                  timeAndRouteBox.setPrefWidth(165);
                  VBox stationsBox = new VBox();
                  HBox.setHgrow(stationsBox, Priority.ALWAYS);
                  stationsBox.setMaxWidth(Double.MAX_VALUE);

                  // generating the scroll bar
                  ScrollPane outerHboxScrollPane = new ScrollPane(outerOuterHBox);
                  outerHboxScrollPane.setFitToWidth(true);

                  // generating the two vboxes for the time and route box
                  VBox timeBox = new VBox();
                  VBox routeBox = new VBox();
                  routeBox.setPrefWidth(25);
                  timeAndRouteBox.getChildren().addAll(timeBox, routeBox);
                  outerHbox.getChildren().addAll(timeAndRouteBox, stationsBox);

                  // generating the labels for the source station to be outputted on screen
                  HBox originBox = new HBox();
                  Label originTitle = new Label(srcStation.getValue().getName());
                  originTitle.getStyleClass().add("boardStationName");
                  Label originLine = new Label(route.get(0).getLine().toString());
                  originLine.getStyleClass().add("boardLineName");
                  originLine.setStyle("-fx-background-color: "+lineColours.get(route.get(0).getLine()));
                  originBox.getChildren().addAll(originTitle, originLine);

                  // generated all the sublines along a route
                  LinkedList<ArrayList<String>> allSublines = tflNetwork.findAllSublinesAlongRoute(route);
                  System.out.println("ALL LINES: " + allSublines);

                  // generating the sublines
                  LinkedList<String> listSubLines = tflNetwork.findSubLinesAlongRoute(route);
                  System.out.println("SUBLINES: " + listSubLines);

                  // generate button to view other lines to take
                  HBox innerTimeLabel = new HBox();
                  VBox buttonBox = new VBox();
                  Button viewNextTrainsAtDeparture = new Button("Live Times");
                  viewNextTrainsAtDeparture.getStyleClass().add("trainDepartureButton");
                  buttonBox.getStyleClass().add("trainDepartureButtonBox");
                  buttonBox.getChildren().add(viewNextTrainsAtDeparture);
                  innerTimeLabel.getChildren().add(buttonBox);

                  // handling the TFL API call for the first train to take
                  JSONObject departureJson = new JSONObject();
                  try {
                    departureJson = getTimeOfTrainArrival(returnNaptanFromCsv(srcStation.getValue().getName()), route.get(0).getLine().toString(), listSubLines.peek(), route.get(0).getTravellingDirection());
                  } catch (Exception ex) {
                    Platform.runLater(() -> {
                      errorLabel.setText(ex.getMessage());
                    });
                  }
                  String platformName = departureJson.getString("platformName");
                  String expectedArrival = departureJson.getString("expectedArrival");
                  expectedArrival = expectedArrival.substring(expectedArrival.indexOf('T') + 1);
                  expectedArrival = expectedArrival.substring(0, 5);

                  // generating the time label for when the first train arrives
                  Label departureTimeLabel = new Label(expectedArrival);

                  departureTimeLabel.getStyleClass().addAll("timeLabelDeparture");
                  innerTimeLabel.getChildren().add(departureTimeLabel);
                  timeBox.getChildren().add(innerTimeLabel);

                  // converting the departure time to time
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                  LocalTime currentTime = LocalTime.parse(expectedArrival);

                  // generating the label instructing user to board subline at which platform.
                  String departureSubline = null;
                  if (departureJson.getString("towards").equals("Check Front of Train")) {
                    departureSubline = listSubLines.get(0);
                  } else {
                    departureSubline = route.get(0).getLine().toString() + " " + departureJson.getString("towards");
                  }
                  Label boardLabel = new Label("Board "+ departureSubline +" | "+platformName);
                  boardLabel.getStyleClass().add("boardLabel");
                  stationsBox.getChildren().add(originBox);
                  stationsBox.getChildren().add(boardLabel);
                  String currentLine = listSubLines.peek();
                  int edgeIterationCount = 0;

                  viewNextTrainsAtDeparture.setOnAction(event -> {
                    try {
                      displayTrains(srcStation.getValue().getName(), returnNaptanFromCsv(srcStation.getValue().getName()), route.get(0).getLine().toString(), route.get(0).getTravellingDirection(), lineColours.get(route.get(0).getLine()));
                    } catch (Exception ex) {
                      throw new RuntimeException(ex);
                    }
                  });

                  for(Edge edge : route) {
                    currentTime = currentTime.plusMinutes(edge.getTravelTime());
                    String localTime = currentTime.format(formatter);
                    edgeIterationCount++;
                    Label station = new Label(edge.getDestination().getName());
                    currentLine = listSubLines.pop();
                    if (edgeIterationCount==route.size()){
                      station.getStyleClass().add("boardStationName");
                      stationsBox.getChildren().add(station);
                      Label arrivalTime = new Label(localTime);
                      arrivalTime.getStyleClass().addAll("timeLabelArrival");
                      timeBox.getChildren().add(arrivalTime);
                    } else {
                      if (currentLine.equals(listSubLines.peek())) {
                        station.getStyleClass().add("intermediateStation");
                        stationsBox.getChildren().add(station);
                        Label emptyTime = new Label();
                        emptyTime.getStyleClass().add("timeLabelEmpty");
                        timeBox.getChildren().add(emptyTime);
                      } else {
                        Label line = new Label();
                        if (edgeIterationCount + 1 < route.size()) {
                          line.setText(route.get(edgeIterationCount+1).getLine().toString());
                          line.setStyle("-fx-background-color: " + lineColours.get(route.get(edgeIterationCount+1).getLine()));
                        } else {
                          // Special case: Last edge but a switch is needed.
                          line.setText(edge.getLine().toString());
                          line.setStyle("-fx-background-color: " + lineColours.get(edge.getLine()));
                        }
                        HBox switchBox = new HBox();
                        line.getStyleClass().add("boardLineName");
                        station.getStyleClass().add("boardStationName");
                        switchBox.getChildren().addAll(station, line);
                        Label switchLines = new Label("Switch to " + listSubLines.peek() + " | " + edge.getTravellingDirection());
                        switchLines.getStyleClass().add("boardLabel");
                        stationsBox.getChildren().addAll(switchBox, switchLines);
                        Label arrivalTime = new Label(localTime);
                        arrivalTime.getStyleClass().addAll("timeLabelSwitch");
                        timeBox.getChildren().add(arrivalTime);
                      }
                    }
                  }
                  resultBox.getChildren().add(outerHboxScrollPane);
                });

              }
            } catch (Exception exception1) {
              Platform.runLater(() -> {
                errorLabel.setText(exception1.getMessage());
              });
            }

          }
        } catch (Exception exception) {
          Platform.runLater(() -> {
            errorLabel.setText(exception.getMessage());
          });
        }
      }
    };

    submit.setOnAction(routePlanner);
    lineStatusBtn.setOnAction(lineStats);

    Scene scene = new Scene(root, 1000, 650);
    scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
    stage.setScene(scene);
    stage.show();

  }

  public static String returnNaptanFromCsv(String name) throws Exception{
    String naptanCode = null;
    try {
      FileReader fr = new FileReader("src/main/resources/NAPTANcsv/naptan.csv");
      CSVReader csvReader = new CSVReader(fr);
      String[] nextRecord;

      while ((nextRecord = csvReader.readNext()) != null) {
        String undergroundName = nextRecord[1];
        if ((name+" Underground Station").equals(undergroundName)) {
          naptanCode = nextRecord[0].toString();
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (naptanCode != null) {
      return naptanCode;
    } else {
      throw new Exception("System Error - No NAPTAN code found");
    }
  }

  public static ArrayList<ArrayList<String>> getLiveStatuses() {
    ArrayList<ArrayList<String>> apiResponse = new ArrayList<>();;
    try {
      String urlString = "https://api.tfl.gov.uk/Line/Mode/tube/Status";
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      // Request Headers from the API
      connection.setRequestProperty("Cache-Control", "no-cache");
      connection.setRequestMethod("GET");
      int status = connection.getResponseCode();

      // ensures valid connection
      if (status == 200) {
        // reads the API response
        BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
        JSONArray jsonArray = new JSONArray(content.toString());

        for (int i=0; i<jsonArray.length(); i++) {
          ArrayList<String> innerArrayList = new ArrayList<>();
          JSONObject object = jsonArray.getJSONObject(i);
          innerArrayList.add(object.getString("name"));

          JSONArray lineStatusDetails = new JSONArray(object.getJSONArray("lineStatuses"));
          for (int j=0; j<lineStatusDetails.length(); j++) {
            JSONObject lineStatusObject = lineStatusDetails.getJSONObject(j);
            innerArrayList.add(lineStatusObject.getString("statusSeverityDescription"));
            innerArrayList.add(lineStatusObject.optString("reason"));
          }
          apiResponse.add(innerArrayList);
        }
      }
      connection.disconnect();
    } catch (Exception exception) {
      System.out.print("exception:" + exception.getMessage());
    }
    System.out.println(apiResponse);
    return apiResponse;
  }

  public static JSONObject getTimeOfTrainArrival(String naptan, String line, String subline, String platformInput) {
    JSONObject returnStats = null;
    try {
      String urlString = "https://api.tfl.gov.uk/Line/" + line.toLowerCase() + "/Arrivals/" + naptan;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      //Request Headers from the API
      connection.setRequestProperty("Cache-Control", "no-cache");
      connection.setRequestMethod("GET");
      int status = connection.getResponseCode();

      // ensures a valid status/connection
      if (status == 200) {
        // reads the API response
        BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
        display(new JSONArray(content.toString()));

        // modifies the method argument to remove the name of the line
        String towardsInput = subline.substring(line.length()+1, subline.length());

        JSONArray jsonArray = new JSONArray (content.toString());
        List<JSONObject> filteredByDirection = filterJSONresponse(jsonArray, platformInput);

        // sorts the filtered array by the time to station attribute
        filteredByDirection.sort(Comparator.comparingInt(obj -> obj.getInt("timeToStation")));
        JSONArray sortedByDirection = new JSONArray(filteredByDirection);
        display(sortedByDirection);

        // finds the optimal earliest subline to take
        for (int i=0; i<sortedByDirection.length(); i++) {
          JSONObject object = sortedByDirection.getJSONObject(i);
          String towardsObj = object.getString("towards");
          if (towardsObj.equals(towardsInput) || towardsObj.equals("Check Front of Train")) {
            returnStats = object;
            break;
          }
        }

        if (returnStats==null){
          returnStats = (JSONObject) sortedByDirection.get(0);
        }
      }
      connection.disconnect();
    } catch (Exception exception) {
      System.out.print("exception:" + exception.getMessage());
    }
    System.out.println(returnStats);
    return returnStats;
  }

  public static List<JSONObject> filterJSONresponse(JSONArray jsonArray, String platformInput) {
    // filters the API response by the direction the user is travelling (ie, westbound, eastbound etc)
    List<JSONObject> filteredByDirection = new ArrayList<>();
    for (int i=0; i<jsonArray.length(); i++) {
      JSONObject object = jsonArray.getJSONObject(i);
      String platformAPI = object.getString("platformName");
      platformAPI = platformAPI.substring(0, platformAPI.indexOf("-")-1);
      if (platformInput.equals(platformAPI)) {
        filteredByDirection.add(object);
      }
    }
    return filteredByDirection;
  }

  public static void display(JSONArray arr) {
    System.out.println("+---------------------------+------------+---------------------+----------------------+------------------+");
    System.out.println("| Platform                 | Vehicle ID | Towards             | Time to Station (s)  | Expected Arrival |");
    System.out.println("+---------------------------+------------+---------------------+----------------------+------------------+");
    for (int i = 0; i < arr.length(); i++) {
      JSONObject object = arr.getJSONObject(i);
      String platformAPI = object.getString("platformName");
      String vehicleId = object.getString("vehicleId");
      String towards = object.optString("towards", "Unknown"); // Handle missing "towards"
      int timeToStation = object.getInt("timeToStation");
      String expectedArrival = object.getString("expectedArrival");
      // Print row in formatted table
      System.out.printf("| %-25s | %-10s | %-19s | %-20d | %-16s |\n",
          platformAPI, vehicleId, towards, timeToStation, expectedArrival);
    }
    System.out.println("+---------------------------+------------+---------------------+----------------------+------------------+");
  }

  public static void displayTrains(String station, String naptan, String line, String platformInput, String lineColour) {
    BorderPane root = new BorderPane();
    Stage displayTrainsStage = new Stage();
    Scene scene = new Scene(root, 900, 275);
    root.getStyleClass().add("resultsBox");

    VBox liveTimesContainer = new VBox(10);
    liveTimesContainer.setStyle("-fx-padding: 15; -fx-alignment: top-left;");

    Label pageTitle = new Label(station + " | Arrival Board");
    pageTitle.getStyleClass().add("arrivalBoardPageTitle");
    Label lineImage = new Label(line);
    lineImage.getStyleClass().add("boardLineName");
    lineImage.setStyle("-fx-background-color: "+lineColour);

    HBox topBar = new HBox(pageTitle, lineImage);

    liveTimesContainer.getChildren().add(topBar);

    String apiResponse = String.valueOf(retrieveNextTrainFromAPI(naptan, line));
    // Modify method argument to remove the name of the line#
    JSONArray jsonArray = new JSONArray(apiResponse);
    List<JSONObject> filteredByDirection = filterJSONresponse(jsonArray, platformInput);
    filteredByDirection.sort(Comparator.comparingInt(obj -> obj.getInt("timeToStation")));

    JSONObject timings = filteredByDirection.get(0).getJSONObject("timing");
    String requestSent = timings.getString("sent");
    requestSent = requestSent.substring(11,16);
    Label requestSentLabel = new Label("Arrival Board last updated at " + requestSent + ". Refresh for latest timings.");
    requestSentLabel.getStyleClass().add("arrivalBoardRequestTime");
    liveTimesContainer.getChildren().add(requestSentLabel);

    HBox liveTimesHeading = new HBox();
    Label platformHeading = new Label("Platform");
    platformHeading.getStyleClass().add("liveTimesHeading");
    platformHeading.setMinWidth(200);
    Label towardsHeading = new Label("Towards");
    towardsHeading.getStyleClass().add("liveTimesHeading");
    towardsHeading.setMinWidth(200);
    Label arrivalHeading = new Label("Arrival");
    arrivalHeading.getStyleClass().add("liveTimesHeading");
    arrivalHeading.setMinWidth(110);
    Label timeToStationHeading = new Label("Arriving in");
    timeToStationHeading.getStyleClass().add("liveTimesHeading");
    timeToStationHeading.setMinWidth(110);
    Label currentLocationHeading = new Label("Current Location");
    currentLocationHeading.getStyleClass().add("liveTimesHeading");
    HBox.setHgrow(currentLocationHeading, Priority.ALWAYS);
    liveTimesHeading.getChildren().addAll(platformHeading, towardsHeading, arrivalHeading, timeToStationHeading, currentLocationHeading);
    liveTimesContainer.getChildren().add(liveTimesHeading);

    VBox liveResults = new VBox();

    ScrollPane arrivalBoardTrainInfo = new ScrollPane(liveResults);
    liveTimesContainer.getChildren().add(arrivalBoardTrainInfo);
    arrivalBoardTrainInfo.setFitToWidth(true);
    arrivalBoardTrainInfo.setFitToHeight(true);
    arrivalBoardTrainInfo.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    arrivalBoardTrainInfo.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);



    ArrayList<Label> timeLabels = new ArrayList<>();

    for (JSONObject object : filteredByDirection) {
      // fetching arrival time
      String arrival = object.getString("expectedArrival");
      arrival = arrival.substring(arrival.indexOf('T') + 1);
      arrival = arrival.substring(0, 5);

      // placing data inside labels
      Label platformName = new Label(object.getString("platformName"));
      platformName.setMinWidth(200);
      platformName.getStyleClass().add("liveTrainRow");
      HBox.setHgrow(platformName, Priority.NEVER);

      Label towards = new Label(object.optString("towards", "Check Front of Train"));
      towards.setMinWidth(200);
      towards.getStyleClass().add("liveTrainRow");
      HBox.setHgrow(towards, Priority.NEVER);

      Label expectedArrival = new Label(arrival);
      expectedArrival.setMinWidth(110);
      expectedArrival.getStyleClass().add("liveTrainRow");
      HBox.setHgrow(expectedArrival, Priority.NEVER);

      Label trainArrivalCountdown = new Label();
      if (object.optString("currentLocation").equals("At Platform")) {
        trainArrivalCountdown.setText("Arrived");
      } else {
        int arrivalCountdown = Integer.parseInt(Integer.toString(object.getInt("timeToStation")));
        int minutes = arrivalCountdown/60;
        int seconds = arrivalCountdown%60;
        if (String.valueOf(seconds).length() == 1) {
          trainArrivalCountdown.setText(minutes + ":0" + seconds + " mins");
        } else {
          trainArrivalCountdown.setText(minutes + ":" + seconds + " mins");
        }
        timeLabels.add(trainArrivalCountdown);
      }

      trainArrivalCountdown.setMinWidth(110);
      trainArrivalCountdown.getStyleClass().add("liveTrainRow");
      HBox.setHgrow(trainArrivalCountdown, Priority.NEVER);

      Label currentLocation = new Label(object.optString("currentLocation"));
      currentLocation.getStyleClass().add("liveTrainRow");
      HBox.setHgrow(currentLocation, Priority.ALWAYS);
      currentLocation.setWrapText(true);

      HBox liveTrainRow = new HBox(platformName, towards, expectedArrival, trainArrivalCountdown, currentLocation);

      liveResults.getChildren().add(liveTrainRow);
    }
    root.setCenter(liveTimesContainer);

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
      for (Label label : timeLabels) {
        try {
          String currentValue = label.getText();
          int mins = Integer.parseInt(currentValue.substring(0, currentValue.indexOf(":")));
          int seconds = Integer.parseInt(currentValue.substring(currentValue.indexOf(":")+1, currentValue.indexOf("m")-1));
          int totalTime = (mins * 60) + seconds;

          if (totalTime > 0) {
            totalTime--;
            int newMins = totalTime/60;
            int newSeconds = totalTime%60;
            if (String.valueOf(newSeconds).length() == 1) {
              label.setText(newMins + ":0" + newSeconds + " mins");
            } else {
              label.setText(newMins + ":" + newSeconds + " mins");
            }
          } else {
            label.setText("Arrived");
            timeLabels.remove(label);
            break;
          }

        } catch (Exception e) {
          System.err.println("Error: " + e);
        }
      }
    }));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    displayTrainsStage.setOnCloseRequest((WindowEvent event) -> {
      if (timeline != null) {
        timeline.stop();
      }
    });

    displayTrainsStage.setScene(scene);
    scene.getStylesheets().add(App.class.getResource("/css/styles.css").toExternalForm());
    displayTrainsStage.setTitle(station + " Arrival Board");
    displayTrainsStage.show();
  }

  private static StringBuilder retrieveNextTrainFromAPI (String naptan, String line) {
    StringBuilder content = new StringBuilder();
    try {
      String urlString = "https://api.tfl.gov.uk/Line/" + line.toLowerCase() + "/Arrivals/" + naptan;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      // Request Headers from the API
      connection.setRequestProperty("Cache-Control", "no-cache");
      connection.setRequestMethod("GET");

      // Read the response
      BufferedReader in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      connection.disconnect();

    } catch (Exception exception) {
      System.out.println("Exception: " + exception.getMessage());
    }
    return content;
  }


}
