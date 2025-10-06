/*
 * Author: Austin Meredith
 * Date: 10/4/25
 * Description: This file holds all the basic data that we get from the ui and a list of the panels
 * */
import java.util.ArrayList;

public class BoxSpec {
  // all of these should be in mm
  double highth;
  double width;
  double depth;
  double tolerance;
  double teethPerEdge;
  ArrayList<Panel> panels = new ArrayList<Panel>();

  public BoxSpec(double highth, double width, double depth, double tolerance, double teethPerEdge) {
    this.highth = highth;
    this.width = width;
    this.depth = depth;
    this.tolerance = tolerance;
    this.teethPerEdge = teethPerEdge;
  }
}
