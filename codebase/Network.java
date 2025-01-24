package codebase;

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

  private final Map<String, List<Edge>> tflNetwork;





}
