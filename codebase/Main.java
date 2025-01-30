package codebase;
import codebase.Network.Edge;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class Main {

  public static void main(String[] args) throws NoSuchFieldException {

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
// King's Cross is already created with both lines
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

// --- Uxbridge branch from Acton Town ---
//   Note: Some stations (Rayners Lane -> Uxbridge) are shared with the Met
//   Rayners Lane + Eastcote + Ruislip Manor + Ruislip + Ickenham + Hillingdon + Uxbridge
//   have already been created, so we only need the purely Piccadilly stops in between.
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

//    // ======== PICCADILLY LINE EDGES ========
// ======== PICCADILLY LINE EDGES ========
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
//    tflNetwork.addEdge(Uxbridge, Hillingdon, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(ActonTown, SouthEaling, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(SouthEaling, ActonTown, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(SouthEaling, Northfields, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(Northfields, SouthEaling, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(Northfields, BostonManor, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(BostonManor, Northfields, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(BostonManor, Osterley, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(Osterley, BostonManor, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(Osterley, HounslowEast, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowEast, Osterley, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowEast, HounslowCentral, Piccadilly, 1, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowCentral, HounslowEast, Piccadilly, 1, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowCentral, HounslowWest, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowWest, HounslowCentral, Piccadilly, 2, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HounslowWest, HattonCross, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HattonCross, HounslowWest, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HattonCross, HeathrowTerminal4, Piccadilly, 4, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminal4, HattonCross, Piccadilly, 4, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HattonCross, HeathrowTerminals2_3, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminals2_3, HattonCross, Piccadilly, 3, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal5, Piccadilly, 4, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminal5, HeathrowTerminals2_3, Piccadilly, 4, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminal4, HeathrowTerminals2_3, Piccadilly, 4, "Platform 1", "Platform 2");
//    tflNetwork.addEdge(HeathrowTerminals2_3, HeathrowTerminal4, Piccadilly, 4, "Platform 1", "Platform 2");


    LinkedList<Edge> route = tflNetwork.findRoute(SouthKensington, FinchleyRoad);
    System.out.println(tflNetwork.findLinesAlongRoute(route));

  }

}
