package com.azure.objects;
/* Author: Austin Meredith
 * Date: 10/4/25
 * Last Changed: 11.3.25
 * Description: This file holds all the basic data that we get from the ui and a list of the panels
 * */
import java.util.ArrayList;

public class BoxSpec {
  // class atributes
  public double height;
  public double width;
  public double depth;
  public double tolerance;
  public double teethWidth;
  public String engraving;
  public ArrayList<Panel> panels = new ArrayList<Panel>();

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
