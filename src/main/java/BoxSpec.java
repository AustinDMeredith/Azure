/*
 * Author: Austin Meredith
 * Date: 10/4/25
 * Description: This file holds all the basic data that we get from the ui and a list of the panels
 * */
import java.util.ArrayList;

public class BoxSpec {
  // all of these should be in mm
  static double height;
  static double width;
  static double depth;
  static double tolerance;
  static int teethPerEdge;
  static ArrayList<Panel> panels = new ArrayList<Panel>();
}
