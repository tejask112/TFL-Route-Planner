package com.example.codebase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Network {

  // Represents an edge (connection) between two stations, the line connecting them and the travel time
  static class Edge {
    Station destination;
    Line line;
    int travelTime;
    String travellingDirection;
    ArrayList<String> subLines = new ArrayList<>();

    Edge(Station nDestination, Line nLine, int nTravelTime, String nTravellingDirection, ArrayList<String> nSubLines) {
      this.destination = nDestination;
      this.line = nLine;
      this.travelTime = nTravelTime;
      this.travellingDirection = nTravellingDirection;
      this.subLines = nSubLines;
    }

    public Station getDestination() { return destination; }

    public Integer getDistance() { return travelTime; }

    public Line getLine() { return line; }

    public ArrayList<String> getSubLines() { return subLines; }

    public String getTravellingDirection() { return travellingDirection; }

    public int getTravelTime() { return travelTime; }
    public String toString(){
      return destination + " (" + line + ", " + travelTime + "mins, " + " travelling direction: " + travellingDirection + ", through sublines: " + subLines + ")";
    }
  }

  private final Map<Station, List<Edge>> tflNetwork;

  // initialises the tfl network graph
  public Network () {
    this.tflNetwork = new HashMap<>();
  }

  // Add a list of new nodes (ie station) to the tfl network
  public void addStation(ArrayList<Station> stations){
    for (Station stationName : stations){
      tflNetwork.putIfAbsent(stationName, new ArrayList<>());
    }
  }

  // Adds a connection between two stations
  public void addEdge(Station src, Station dest, Line line, int travelTime, String travellingDirection, ArrayList<String> subLines) {
    tflNetwork.putIfAbsent(src, new ArrayList<>());
    tflNetwork.get(src).add(new Edge(dest, line, travelTime, travellingDirection, subLines));
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

  // Runs Dijkstra's algorithm between two stations to find the shortest route
  public LinkedList<Edge> findRoute(Station src, Station dest) {
    Map<Station, Integer> distanceLog = new HashMap<>();
    Map<Station, Edge> predecessorEdges = new HashMap<>();
    PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingInt(distanceLog::get));
    for (Station station : tflNetwork.keySet()){
      distanceLog.put(station, Integer.MAX_VALUE);  //set distance to all other nodes as infinity
    }
    distanceLog.put(src, 0); //initialise distance to source node as 0
    queue.add(src);  //add the source node to the priority queue

    while (!queue.isEmpty()) {
      Station currentStation = queue.poll();

      //if destination is reached
      if(currentStation.equals(dest)){
        break;
      }

      for (Edge edge : tflNetwork.get(currentStation)) {
        Integer newDistance = distanceLog.get(currentStation) + edge.getDistance();
        if (newDistance < distanceLog.get(edge.getDestination())) {
          distanceLog.put(edge.getDestination(), newDistance);
          queue.remove(edge.getDestination());
          queue.add(edge.getDestination());
          predecessorEdges.put(edge.getDestination(), edge);
        }
      }
    }

    // reconstructing the path
    LinkedList<Edge> path = new LinkedList<>();
    Station currentStation = dest;
    while (predecessorEdges.containsKey(currentStation)) {
      Edge edge = predecessorEdges.get(currentStation);
      path.addFirst(edge);
      currentStation = getStationFromEdge(edge, currentStation);
    }

    if (distanceLog.get(dest) == Integer.MAX_VALUE) {
      System.out.println("error");
      return null;
    } else {
      return path;
    }
  }

  private Station getStationFromEdge(Edge edge, Station station) {
    for(Map.Entry<Station, List<Edge>> entry : tflNetwork.entrySet()) {
      if (entry.getValue().contains(edge) && edge.getDestination().equals(station)) {
        return entry.getKey();
      }
    }
    return null;
  }

  public LinkedList<Line> findLinesAlongRoute(LinkedList<Edge> route){
    LinkedList<Line> lines = new LinkedList<>();
    Map<Line, Boolean> mappedLines = new HashMap<>();
    for (Edge edge : route){
      if (mappedLines.get(edge.getLine()) == null) {
        mappedLines.put(edge.getLine(), Boolean.TRUE);
        lines.add(edge.getLine());
      }
    }
    return lines;
  }

  public LinkedList<ArrayList<String>> findAllSublinesAlongRoute(LinkedList<Edge> route) {
    LinkedList<ArrayList<String>> allSublines = new LinkedList<>();
    for (Edge edge : route) {
      allSublines.add(edge.getSubLines());
    }
    return allSublines;
  }

  public LinkedList<String> findSubLinesAlongRoute(LinkedList<Edge> route) {
   LinkedList<String> finalSubLineList = new LinkedList<>();
    Map<String, Boolean> subLinesMap = new HashMap<>();
    Map<String, Integer> subLinesCount = new HashMap<>();
    int iteratorCount = 0;

    // traverse through each edge
    for (Edge edge : route) {
      iteratorCount++;
      //list of sublines on that specific edge
      ArrayList<String>  listOfSubLines = edge.getSubLines();
      //puts each subline for that edge in the hashmap
      if (subLinesMap.isEmpty()) {
        for (String s : listOfSubLines) {
          subLinesMap.put(s, true);
          subLinesCount.put(s, 0);
        }
      }
      // checks if at least one of the sublines in listOfSubLines is present
      int count = 0;
      for (String s : listOfSubLines) {
        if (subLinesMap.get(s) == null) { count++; }
      }
      if (count == listOfSubLines.size()) {
        String maxSubLine = Collections.max(subLinesCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (int i=0; i<subLinesCount.get(maxSubLine); i++) {
          finalSubLineList.add(maxSubLine);
        }
        subLinesMap.clear();
        subLinesCount.clear();
        for (String s : listOfSubLines) {
          subLinesMap.put(s, true);
          subLinesCount.put(s, 1);
        }
      } else {
        for (String s : new ArrayList<>(subLinesMap.keySet())) {
          if (!listOfSubLines.contains(s)) {
            subLinesMap.remove(s);
            subLinesCount.remove(s);
          }
        }
        for (String s : listOfSubLines) {
          subLinesMap.put(s, true);
          if (subLinesCount.get(s) == null) { subLinesCount.put(s, 1); }
          else {subLinesCount.put(s, subLinesCount.get(s)+1);}
        }
      }
      if (iteratorCount == route.size()){
        String maxSubLine = Collections.max(subLinesCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (int i=0; i<subLinesCount.get(maxSubLine); i++) {
          finalSubLineList.add(maxSubLine);
        }
      }
    }
    return finalSubLineList;
  }

  public Set<Station> getStations() {
    return tflNetwork.keySet();
  }


}
