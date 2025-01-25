package codebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

  // Represents an edge (connection) between two stations, the line connecting them and the travel time
  static class Edge {
    String destination;
    String line;
    int travelTime;

    Edge(String nDestination, String nLine, int nTravelTime) {
      destination = nDestination;
      line = nLine;
      travelTime = nTravelTime;
    }
  }

  public static Map<String, List<Edge>> tflNetwork;

  // initialises the tfl network graph
  public Network () {
    tflNetwork = new HashMap<>();
  }

  // Add a new node (ie station) to the tfl network
  public void addStation(String stationName){
    tflNetwork.put(stationName, new ArrayList<>());
  }

  // Adds a connection between two stations
  public void addEdge(String src, String dest, String line, int travelTime) {
    tflNetwork.put(src, new ArrayList<>());
    tflNetwork.put(dest, new ArrayList<>());
    tflNetwork.get(src).add(new Edge(dest, line, travelTime));
    tflNetwork.get(dest).add(new Edge(src, line, travelTime));
  }

  public void removeEdge(String src, String dest) {
    tflNetwork.get(src).remove(dest);
    tflNetwork.get(dest).remove(src);
  }

}
