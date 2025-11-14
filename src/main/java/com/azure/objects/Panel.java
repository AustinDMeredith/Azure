package com.azure.objects;
/* Author: Austin Meredith
 * Date Created: 10.4.25
 * Last Changed: 11.13.25
 * Description: This file holds edge specifications and path data to send to the svg service.
 * */
import java.util.ArrayList;

public class Panel {
  public String id;
  public PanelRole role;
  public String path;
  public double height, width;
  public ArrayList<Double> startPoint = new ArrayList<Double>();
  public ArrayList<EdgeRole> edges = new ArrayList<EdgeRole>();
  public ArrayList<Double> finalEdgeLengths = new ArrayList<Double>();

  public static enum PanelRole {slidingLid, liftingLid, top, front, back, right, left, bottom, basedBottom, slidableFront, slidableLeft, slidableRight, slidableBack, bottomLeftRail, bottomRightRail, topLeftRail, topRightRail, backRail}
  public static enum EdgeRole {male, female, top, bottom, slidableSide, slidableBack, slidableFront, flat}

  // constructor
  public Panel (String id, PanelRole role, double height, double width) {
    this.id = id;
    this.role = role;
    this.height = height;
    this.width = width;
  }
}
