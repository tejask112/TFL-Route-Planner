package codebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

  // Represents an edge (connection) between two stations, the line connecting them and the travel time
  static class Edge {
    Station destination;
    String line;
    int travelTime;

    Edge(Station nDestination, String nLine, int nTravelTime) {
      this.destination = nDestination;
      this.line = nLine;
      this.travelTime = nTravelTime;
    }
  }

  private final Map<Station, List<Edge>> tflNetwork;

  // initialises the tfl network graph
  public Network () {
    this.tflNetwork = new HashMap<>();
  }

  // Add a new node (ie station) to the tfl network
  public void addStation(Station stationName){
    tflNetwork.putIfAbsent(stationName, new ArrayList<>());
  }

  // Adds a connection between two stations
  public void addEdge(Station src, Station dest, String line, int travelTime) {
    tflNetwork.putIfAbsent(src, new ArrayList<>());
    tflNetwork.putIfAbsent(dest, new ArrayList<>());
    tflNetwork.get(src).add(new Edge(dest, line, travelTime));
    tflNetwork.get(dest).add(new Edge(src, line, travelTime));
  }

  public List<Edge> getStation(Station station) {
    List<Edge> connection = tflNetwork.get(station);
    return connection;
  }

  public Map<Station, List<Edge>> getTflNetwork(){
    return this.tflNetwork;
  }

}
