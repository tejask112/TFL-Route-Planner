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
    String departingPlatform;
    String arrivalPlatform;

    Edge(Station nDestination, String nLine, int nTravelTime, String departingPlatform, String arrivalPlatform) {
      this.destination = nDestination;
      this.line = nLine;
      this.travelTime = nTravelTime;
      this.departingPlatform = departingPlatform;
      this.arrivalPlatform = arrivalPlatform;
    }

    public Station getDestination() {
      return destination;
    }

    public String toString(){
      return destination + " (" + line + ", " + travelTime + "mins)";
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
  public void addEdge(Station src, Station dest, String line, int travelTime, String departingPlatform, String arrivingPlatform) {
    tflNetwork.putIfAbsent(src, new ArrayList<>());
    tflNetwork.get(src).add(new Edge(dest, line, travelTime, departingPlatform, arrivingPlatform));
  }

  //removes a station from the tfl network (used when there is part closure)
  public void removeStation(Station stationToRemove){
    if (!tflNetwork.containsKey(stationToRemove)) return;
    List<Edge> connections = tflNetwork.get(stationToRemove);
    for (Edge connection : connections) {
      Station stationName = connection.getDestination();
      List<Edge> neighbourEdges = tflNetwork.get(stationName);
      if(neighbourEdges != null) {
        List<Edge> edgesToRemove = new ArrayList<>();
        for (Edge edge : neighbourEdges) {
          if (edge.getDestination().equals(stationToRemove)) {
            edgesToRemove.add(edge);
          }
        }
        neighbourEdges.removeAll(edgesToRemove);
      }
    }
    tflNetwork.remove(stationToRemove);
  }

  public List<Edge> getStation(Station station) {
    return tflNetwork.get(station);
  }

  public Map<Station, List<Edge>> getTflNetwork(){
    return this.tflNetwork;
  }

}
