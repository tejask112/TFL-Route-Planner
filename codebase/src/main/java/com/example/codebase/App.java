package com.example.codebase;

import com.example.codebase.Network.Edge;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.util.StringConverter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
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


    tflNetwork.addEdge(Amersham, ChalfontLatimer, Metropolitan, 5, "Platform 1, 2 or 3", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(ChalfontLatimer, Amersham, Metropolitan, 5, "Platform 1", "NA", new ArrayList<>(List.of("Metropolitan Amersham")));
    tflNetwork.addEdge(ChalfontLatimer, Chesham, Metropolitan, 8, "Platform 3", "Platform 1", new ArrayList<>(List.of("Metropolitan Chesham")));
    tflNetwork.addEdge(Chesham, ChalfontLatimer, Metropolitan, 8, "Platform 1", "Platform 3", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(ChalfontLatimer, Chorleywood, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Chorleywood, ChalfontLatimer, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham")));
    tflNetwork.addEdge(Chorleywood, Rickmansworth, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate", "Metropolitan Chesham")));
    tflNetwork.addEdge(Rickmansworth, Chorleywood, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham")));
    tflNetwork.addEdge(Rickmansworth, MoorPark, Metropolitan, 6, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(MoorPark, Rickmansworth, Metropolitan, 6, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham")));
    tflNetwork.addEdge(MoorPark, Northwood, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Northwood, MoorPark, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Northwood, NorthwoodHills, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(NorthwoodHills, Northwood, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthwoodHills, Pinner, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Pinner, NorthwoodHills, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Pinner, NorthHarrow, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(NorthHarrow, Pinner, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthHarrow, HarrowOnTheHill, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(HarrowOnTheHill, NorthHarrow, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(HarrowOnTheHill, NorthwickPark, Metropolitan, 2, "Platform 3", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(NorthwickPark, HarrowOnTheHill, Metropolitan, 2, "Platform 1", "Platform 4", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(NorthwickPark, PrestonRoad, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(PrestonRoad, NorthwickPark, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(PrestonRoad, WembleyPark, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(WembleyPark, PrestonRoad, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(WembleyPark, FinchleyRoad, Metropolitan, 8, "Platform 5", "Platform 6", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(FinchleyRoad, WembleyPark, Metropolitan, 8, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(FinchleyRoad, BakerStreet, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(BakerStreet, FinchleyRoad, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(BakerStreet, GreatPortlandStreet, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(GreatPortlandStreet, BakerStreet, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(GreatPortlandStreet, EustonSquare, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(EustonSquare, GreatPortlandStreet, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(EustonSquare, KingsCrossStPancras, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(KingsCrossStPancras, EustonSquare, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(KingsCrossStPancras, Farringdon, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Farringdon, KingsCrossStPancras, Metropolitan, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Farringdon, Barbican, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Barbican, Farringdon, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Barbican, Moorgate, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Moorgate, Barbican, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(Moorgate, LiverpoolStreet, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(LiverpoolStreet, Moorgate, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(LiverpoolStreet, Aldgate, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Aldgate, LiverpoolStreet, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge", "Metropolitan Amersham", "Metropolitan Chesham", "Metropolitan Watford")));
    tflNetwork.addEdge(HarrowOnTheHill, WestHarrow, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(WestHarrow, HarrowOnTheHill, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(WestHarrow, RaynersLane, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(RaynersLane, WestHarrow, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(RaynersLane, Eastcote, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Eastcote, RaynersLane, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Eastcote, RuislipManor, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(RuislipManor, Eastcote, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(RuislipManor, Ruislip, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Ruislip, RuislipManor, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Ruislip, Ickenham, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Ickenham, Ruislip, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Ickenham, Hillingdon, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Hillingdon, Ickenham, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Uxbridge")));
    tflNetwork.addEdge(Uxbridge, Hillingdon, Metropolitan, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(MoorPark, Croxley, Metropolitan, 3, "Platform 3", "Platform 4", new ArrayList<>(List.of("Metropolitan Watford")));
    tflNetwork.addEdge(Croxley, MoorPark, Metropolitan, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Croxley, Watford, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Watford")));
    tflNetwork.addEdge(Watford, Croxley, Metropolitan, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Metropolitan Aldgate")));
    tflNetwork.addEdge(Cockfosters, Oakwood, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Oakwood, Cockfosters, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Oakwood, Southgate, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Southgate, Oakwood, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Southgate, ArnosGrove, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ArnosGrove, Southgate, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ArnosGrove, BoundsGreen, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(BoundsGreen, ArnosGrove, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BoundsGreen, WoodGreen, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(WoodGreen, BoundsGreen, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(WoodGreen, TurnpikeLane, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(TurnpikeLane, WoodGreen, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(TurnpikeLane, ManorHouse, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ManorHouse, TurnpikeLane, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ManorHouse, FinsburyPark, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(FinsburyPark, ManorHouse, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(FinsburyPark, KingsCrossStPancras, Piccadilly, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(KingsCrossStPancras, FinsburyPark, Piccadilly, 5, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(KingsCrossStPancras, RussellSquare, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(RussellSquare, KingsCrossStPancras, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RussellSquare, Holborn, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Holborn, RussellSquare, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Holborn, CoventGarden, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(CoventGarden, Holborn, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(CoventGarden, LeicesterSquare, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(LeicesterSquare, CoventGarden, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(LeicesterSquare, PiccadillyCircus, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(PiccadillyCircus, LeicesterSquare, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(PiccadillyCircus, GreenPark, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(GreenPark, PiccadillyCircus, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(GreenPark, HydeParkCorner, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(HydeParkCorner, GreenPark, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HydeParkCorner, Knightsbridge, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Knightsbridge, HydeParkCorner, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Knightsbridge, SouthKensington, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(SouthKensington, Knightsbridge, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthKensington, GloucesterRoad, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(GloucesterRoad, SouthKensington, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(GloucesterRoad, EarlsCourt, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge", "Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(EarlsCourt, GloucesterRoad, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(EarlsCourt, BaronsCourt, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(BaronsCourt, EarlsCourt, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BaronsCourt, Hammersmith, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(Hammersmith, BaronsCourt, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hammersmith, TurnhamGreen, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(TurnhamGreen, Hammersmith, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(TurnhamGreen, ActonTown, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ActonTown, TurnhamGreen, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5", "Piccadilly Heathrow T4")));
    tflNetwork.addEdge(ActonTown, EalingCommon, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(EalingCommon, ActonTown, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(EalingCommon, NorthEaling, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(NorthEaling, EalingCommon, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(NorthEaling, ParkRoyal, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(ParkRoyal, NorthEaling, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(ParkRoyal, Alperton, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Alperton, ParkRoyal, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Alperton, SudburyTown, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SudburyTown, Alperton, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SudburyTown, SudburyHill, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SudburyHill, SudburyTown, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SudburyHill, SouthHarrow, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(SouthHarrow, SudburyHill, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthHarrow, RaynersLane, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(RaynersLane, SouthHarrow, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RaynersLane, Eastcote, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Eastcote, RaynersLane, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Eastcote, RuislipManor, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(RuislipManor, Eastcote, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(RuislipManor, Ruislip, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Ruislip, RuislipManor, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Ruislip, Ickenham, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Ickenham, Ruislip, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Ickenham, Hillingdon, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Hillingdon, Ickenham, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(Uxbridge, Hillingdon, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Hillingdon, Uxbridge, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Uxbridge")));
    tflNetwork.addEdge(ActonTown, SouthEaling, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(SouthEaling, ActonTown, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(SouthEaling, Northfields, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(Northfields, SouthEaling, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Northfields, BostonManor, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(BostonManor, Northfields, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(BostonManor, Osterley, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(Osterley, BostonManor, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(Osterley, HounslowEast, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowEast, Osterley, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowEast, HounslowCentral, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowCentral, HounslowEast, Piccadilly, 1, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowCentral, HounslowWest, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HounslowWest, HounslowCentral, Piccadilly, 2, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HounslowWest, HattonCross, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4", "Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HattonCross, HounslowWest, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HattonCross, HeathrowTerminal4, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4")));
    tflNetwork.addEdge(HeathrowTerminal4, HattonCross, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HattonCross, HeathrowTerminals2_3, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HeathrowTerminals2_3, HattonCross, Piccadilly, 3, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal5, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HeathrowTerminal5, HeathrowTerminals2_3, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Cockfosters")));
    tflNetwork.addEdge(HeathrowTerminal4, HeathrowTerminals2_3, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T5")));
    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal4, Piccadilly, 4, "Platform 1", "Platform 2", new ArrayList<>(List.of("Piccadilly Heathrow T4")));

    ComboBox<Station> srcStation = new ComboBox<>();
    srcStation.getItems().addAll(tflNetwork.getStations());
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
    srcStation.setPromptText("Departure");
    srcStation.setStyle("-fx-translate-y: 50px; -fx-translate-x: 20px;");
    srcStation.getStyleClass().add("stationComboBox");

    ComboBox<Station> destStation = new ComboBox<>();
    destStation.getItems().addAll(tflNetwork.getStations());
    destStation.setConverter(new StringConverter<Station>() {
      @Override
      public String toString(Station station) {
        return station.getName();
      }
      @Override
      public Station fromString(String s) {
        return null;
      }
    });
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

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle (ActionEvent e) {
        try {
          if (srcStation.getValue() == null || destStation.getValue() == null){
            throw new Exception("Departure and Arrival Stations Required!");
          } else {
            errorLabel.setText("");
            LinkedList<Edge> route = tflNetwork.findRoute(srcStation.getValue(), destStation.getValue());
            System.out.println(route);
            System.out.println(tflNetwork.findLinesAlongRoute(route));
            try {
              if (route.isEmpty()) {
                throw new Exception("Departure and Arrival stations must be distinct");
              } else {
                Platform.runLater(() -> {
                  // generates the top title bar
                  HBox destinationMessageHBox = new HBox();
                  Label destinationMessage = new Label("Your Route to "+destStation.getValue().getName());
                  destinationMessageHBox.getChildren().add(destinationMessage);
                  destinationMessageHBox.setPrefWidth(resultBox.getPrefWidth());
                  destinationMessage.getStyleClass().add("destinationMessage");
                  resultBox.getChildren().add(destinationMessageHBox);

                  // generating the hashmap for the different lines and their colours
                  Map<Line, String> lineColours = new HashMap<>();
                  lineColours.put(Metropolitan, "#9B0056");
                  lineColours.put(Piccadilly, "#003688");

                  // generating the outerHbox which contains the station lines and station descriptions
                  HBox outerHbox = new HBox();
                  VBox timeAndRouteBox = new VBox();
                  timeAndRouteBox.setPrefWidth(150);
                  VBox stationsBox = new VBox();
                  HBox.setHgrow(stationsBox, Priority.ALWAYS);
                  stationsBox.setMaxWidth(Double.MAX_VALUE);

                  // generating the two vboxes for the time and route box
                  VBox timeBox = new VBox();
                  VBox routeBox = new VBox();
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

                  // generating the current time and route line for the source station
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                  LocalTime currentTime = LocalTime.now();
                  String initialTime = LocalTime.now().format(formatter);
                  Label currentTimeLabel = new Label(initialTime);
                  currentTimeLabel.getStyleClass().addAll("timeLabelDeparture");
                  timeBox.getChildren().add(currentTimeLabel);

                  // generating and adding the board line label
                  LinkedList<String> listSubLines = tflNetwork.findSubLinesAlongRoute(route);
                  Label boardLabel = new Label("Board "+listSubLines.get(0)+" - "+route.get(0).getDeparturePlatform());
                  boardLabel.getStyleClass().add("boardLabel");
                  stationsBox.getChildren().add(originBox);
                  stationsBox.getChildren().add(boardLabel);
                  System.out.println(listSubLines);
                  String currentLine = listSubLines.peek();
                  int edgeIterationCount = 0;

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
                        if (edgeIterationCount + 1 < route.size()) {
                          HBox switchBox = new HBox();
                          Label line = new Label(route.get(edgeIterationCount+1).getLine().toString());
                          line.setStyle("-fx-background-color: " + lineColours.get(route.get(edgeIterationCount+1).getLine()));
                          line.getStyleClass().add("boardLineName");
                          station.getStyleClass().add("boardStationName");
                          switchBox.getChildren().addAll(station, line);
                          Label switchLines = new Label("Switch to " + listSubLines.peek() + " - " + edge.getDeparturePlatform());
                          switchLines.getStyleClass().add("boardLabel");
                          stationsBox.getChildren().addAll(switchBox, switchLines);
                          Label switchTime = new Label(localTime);
                          switchTime.getStyleClass().add("timeLabelSwitch");
                          timeBox.getChildren().add(switchTime);
                        } else {
                          // Special case: Last edge but a switch is needed.
                          HBox switchBox = new HBox();
                          Label line = new Label(edge.getLine().toString());
                          line.setStyle("-fx-background-color: " + lineColours.get(edge.getLine()));
                          line.getStyleClass().add("boardLineName");
                          station.getStyleClass().add("boardStationName");
                          switchBox.getChildren().addAll(station, line);
                          Label switchLines = new Label("Switch to " + listSubLines.peek() + " - " + edge.getDeparturePlatform());
                          switchLines.getStyleClass().add("boardLabel");
                          stationsBox.getChildren().addAll(switchBox, switchLines);
                          Label arrivalTime = new Label(localTime);
                          arrivalTime.getStyleClass().addAll("timeLabelSwitch");
                          timeBox.getChildren().add(arrivalTime);
                        }
                      }
                    }
                  }
                  resultBox.getChildren().add(outerHbox);
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

    submit.setOnAction(event);

    Scene scene = new Scene(root, 1000, 650);
    scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
    stage.setScene(scene);
    stage.show();

  }
}
