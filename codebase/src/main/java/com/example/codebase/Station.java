package com.example.codebase;
import codebase.Network.Edge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Station {

  private final String name;
  private final ArrayList<Line> lines;
  private final Boolean lift;
  private final Integer zone;
  private static final Map<String, Station> stations = new HashMap<>();

  public Station (String nStationName, ArrayList<Line> nLines, Boolean nLift, Integer nZone){
    name = nStationName;
    lines = nLines;;
    lift = nLift;
    zone = nZone;
    stations.put(nStationName, this);
  }

  public String toString(){
    return name;
  }

  public static Station retrieveStationFromString(String name) throws Exception {
    Station station = stations.get(name);
    if (station==null) {
      throw new Exception ("No station with this name found");
    } else {
      return station;
    }
  }

  public Map<String, Station> getStations() {
    return stations;
  }

}
