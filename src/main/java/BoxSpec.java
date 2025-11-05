/* Author: Austin Meredith
 * Date: 10/4/25
 * Last Changed: 11.3.25
 * Description: This file holds all the basic data that we get from the ui and a list of the panels
 * */
import java.util.ArrayList;

public class BoxSpec {
  // class atributes
  double height;
  double width;
  double depth;
  double tolerance;
  double teethWidth;
  String engraving;
  ArrayList<Panel> panels = new ArrayList<Panel>();

  // constructor
  public BoxSpec () {};

  // getters
  public double getHeight() {return height;}
  public double getWidth() {return width;}
  public double getDepth() {return depth;}
  public double getTolerance() {return tolerance;}
  public double getTeethWidth() {return teethWidth;}

  public ArrayList<Panel> getPanels() {return panels;}
}
