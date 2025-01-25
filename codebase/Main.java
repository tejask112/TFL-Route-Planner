package codebase;

public class Main {

  public static void main(String[] args) {

    Network tflNetwork = new Network();

    Station BondStreet = new Station("Bond Street");
    Station OxfordCircus = new Station("Oxford Circus");
    Station GreenPark = new Station("Green Park");

    // Add stations
    tflNetwork.addStation(OxfordCircus);
    tflNetwork.addStation(BondStreet);
    tflNetwork.addStation(GreenPark);

    // Add connections
    tflNetwork.addEdge(OxfordCircus, BondStreet, "Central Line", 2);
    tflNetwork.addEdge(OxfordCircus, GreenPark, "Victoria Line", 3);
    tflNetwork.addEdge(BondStreet, GreenPark, "Jubilee Line", 2);

    System.out.println(tflNetwork.getTflNetwork());

    System.out.println("///////////////////////////////////////////////////////////////////");

    tflNetwork.removeStation(GreenPark);

    System.out.println(tflNetwork.getTflNetwork());

  }

}
