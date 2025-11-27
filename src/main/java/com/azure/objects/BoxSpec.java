package com.azure.objects;
/* Author: Austin Meredith
 * Date: 10/4/25
 * Last Changed: 11.13.25
 * Description: This file holds all the basic data that we get from the ui and a list of the panels
 * */
import java.util.ArrayList;

public class BoxSpec {
  // class atributes
  public BoxType boxType;
  public double height;
  public double width;
  public double depth;
  public double radius;
  public Panel.PanelRole lidType;
  public double teethWidth;
  public String engraving;
  public String svg;
  public ArrayList<Panel> panels = new ArrayList<Panel>();

  // constructor
  public BoxSpec () {};

  public static enum BoxType {based, hinged, simple};

  // getters
  public double getHeight() {return height;}
  public double getWidth() {return width;}
  public double getDepth() {return depth;}
  public double getTeethWidth() {return teethWidth;}

  public ArrayList<Panel> getPanels() {return panels;}
}
