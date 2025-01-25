package codebase;

public class Main {

  public static void main(String[] args) {

    Network tflNetwork = new Network();

    tflNetwork.addStation("Rayners Lane");
    tflNetwork.addStation("Harrow-On-The-Hill");
    tflNetwork.addEdge("Rayners Lane", "Harrow-On-The-Hill", "Metropolitan", 4);

  }

}
