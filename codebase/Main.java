package codebase;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    Network tflNetwork = new Network();

    // Create the line object
    Line Metropolitan = new Line("Metropolitan", true);

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

    // Create network and add station
    tflNetwork.addStation(new ArrayList<>(List.of(
        Amersham, ChalfontLatimer, Chesham, Rickmansworth, MoorPark, Northwood, NorthwoodHills,
        Pinner, NorthHarrow, HarrowOnTheHill, WembleyPark, FinchleyRoad, BakerStreet,
        GreatPortlandStreet, EustonSquare, KingsCrossStPancras, Farringdon, Barbican, Moorgate, LiverpoolStreet
    )));

// Amersham Branch
    tflNetwork.addEdge(Amersham, ChalfontLatimer, Metropolitan, 5, "Platform 1", "Platform 2");
    tflNetwork.addEdge(ChalfontLatimer, Amersham, Metropolitan, 5, "Platform 1", "Platform 2");
// Chesham Branch
    tflNetwork.addEdge(ChalfontLatimer, Chesham, Metropolitan, 8, "Platform 3", "Platform 1");
    tflNetwork.addEdge(Chesham, ChalfontLatimer, Metropolitan, 8, "Platform 1", "Platform 3");
// Main Line
    tflNetwork.addEdge(ChalfontLatimer, Chorleywood, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Chorleywood, ChalfontLatimer, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Chorleywood, Rickmansworth, Metropolitan, 5, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Rickmansworth, Chorleywood, Metropolitan, 5, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Rickmansworth, MoorPark, Metropolitan, 6, "Platform 1", "Platform 2");
    tflNetwork.addEdge(MoorPark, Rickmansworth, Metropolitan, 6, "Platform 1", "Platform 2");
    tflNetwork.addEdge(MoorPark, Northwood, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Northwood, MoorPark, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Northwood, NorthwoodHills, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(NorthwoodHills, Northwood, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(NorthwoodHills, Pinner, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Pinner, NorthwoodHills, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Pinner, NorthHarrow, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(NorthHarrow, Pinner, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(NorthHarrow, HarrowOnTheHill, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(HarrowOnTheHill, NorthHarrow, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(HarrowOnTheHill, NorthwickPark, Metropolitan, 2, "Platform 3", "Platform 2");
    tflNetwork.addEdge(NorthwickPark, HarrowOnTheHill, Metropolitan, 2, "Platform 1", "Platform 4");
    tflNetwork.addEdge(NorthwickPark, PrestonRoad, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(PrestonRoad, NorthwickPark, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(PrestonRoad, WembleyPark, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(WembleyPark, PrestonRoad, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(WembleyPark, FinchleyRoad, Metropolitan, 8, "Platform 5", "Platform 6");
    tflNetwork.addEdge(FinchleyRoad, WembleyPark, Metropolitan, 8, "Platform 1", "Platform 2");
    tflNetwork.addEdge(FinchleyRoad, BakerStreet, Metropolitan, 5, "Platform 1", "Platform 2");
    tflNetwork.addEdge(BakerStreet, FinchleyRoad, Metropolitan, 5, "Platform 1", "Platform 2");
    tflNetwork.addEdge(BakerStreet, GreatPortlandStreet, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(GreatPortlandStreet, BakerStreet, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(GreatPortlandStreet, EustonSquare, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(EustonSquare, GreatPortlandStreet, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(EustonSquare, KingsCrossStPancras, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(KingsCrossStPancras, EustonSquare, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(KingsCrossStPancras, Farringdon, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Farringdon, KingsCrossStPancras, Metropolitan, 4, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Farringdon, Barbican, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Barbican, Farringdon, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Barbican, Moorgate, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Moorgate, Barbican, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Moorgate, LiverpoolStreet, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(LiverpoolStreet, Moorgate, Metropolitan, 3, "Platform 1", "Platform 2");
    tflNetwork.addEdge(LiverpoolStreet, Aldgate, Metropolitan, 2, "Platform 1", "Platform 2");
    tflNetwork.addEdge(Aldgate, LiverpoolStreet, Metropolitan, 2, "Platform 1", "Platform 2");

    System.out.println(tflNetwork.findRoute(HarrowOnTheHill, Chesham));

  }

}
