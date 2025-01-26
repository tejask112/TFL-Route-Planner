package codebase;

public class Line {

  private final String name;
  private Boolean ac;

  public Line (String nName, Boolean nAc) {
    name = nName;
    ac = nAc;
  }

  public String toString(){
    return name;
  }

}
