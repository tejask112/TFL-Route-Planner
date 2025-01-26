package codebase;

import java.util.ArrayList;

public class Station {

  private String name;
  private ArrayList<Line> lines;
  private Boolean lift;
  private Integer zone;


  public Station (String nStationName, ArrayList<Line> nLines, Boolean nLift, Integer nZone){
    name = nStationName;
    lines = nLines;;
    lift = nLift;
    zone = nZone;
  }

  public String toString(){
    return name;
  }

}
