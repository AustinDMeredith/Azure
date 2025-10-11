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

    // add all the panels to the array
    this.panels.add(new Panel("p-0", Panel.PanelRole.front, this.height, this.width));
    this.panels.add(new Panel("p-1", Panel.PanelRole.bottom, this.depth, this.width));

    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
  
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth);
    }
  }

  // getters
  public double getHeight() {return height;}
  public double getWidth() {return width;}
  public double getDepth() {return depth;}
  public double getTolerance() {return tolerance;}
  public double getTeethWidth() {return teethWidth;}

  public ArrayList<Panel> getPanels() {return panels;}
}
