/*
 * Author: Austin Meredith
 * Date: 10/4/25
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
  ArrayList<Panel> panels = new ArrayList<Panel>();

  // constructor
  public BoxSpec (double height, double width, double depth, double tolerance, double teethWidth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.tolerance = tolerance;
    this.teethWidth = teethWidth;
  }

  // getters
  public double getHeight() {return this.height;}
  public double getWidth() {return this.width;}
  public double getDepth() {return this.depth;}
  public double getTolerance() {return this.tolerance;}
  public double getTeethWidth() {return this.teethWidth;}

  public ArrayList<Panel> getPanels() {return this.panels;}
}
