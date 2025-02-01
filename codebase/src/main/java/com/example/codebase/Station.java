package com.example.codebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Station {

  private final String name;
  private final ArrayList<Line> lines;
  private final Boolean lift;
  private final Integer zone;

  public Station (String nStationName, ArrayList<Line> nLines, Boolean nLift, Integer nZone){
    name = nStationName;
    lines = nLines;;
    lift = nLift;
    zone = nZone;
  }

  public String toString(){
    return name;
  }

  public String getName() {
    return name;
  }

}
