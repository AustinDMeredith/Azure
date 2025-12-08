package com.azure.objects;
/* Author: Austin Meredith
 * Date Created: 10.4.25
 * Last Changed: 11.15.25
 * Description: This file holds edge specifications and path data to send to the svg service.
 * */
import java.util.ArrayList;

public class Panel {
  public String id;
  public PanelRole role;
  public String path;
  public double height, width, radius;
  public double tolerance = .03;
  public ArrayList<Double> startPoint = new ArrayList<Double>();
  public ArrayList<Double> holeStartPoints = new ArrayList<Double>();
  public ArrayList<EdgeRole> edges = new ArrayList<EdgeRole>();
  public ArrayList<Double> finalEdgeLengths = new ArrayList<Double>();

  // a list of all the panel roles to use
  public static enum PanelRole {slidingLid, liftingLid, innerLid, handle, top, front, back, right, left, bottom, basedBottom, 
    slidableFront, slidableLeft, slidableRight, slidableBack, bottomLeftRail, bottomRightRail, topLeftRail, topRightRail, backRail, 
    frontTop, frontBottom, backTop, backBottom, rightTop, rightBottom, leftTop, leftBottom, hingeRight, hingeLeft}
  
  // a list of all the edge roles to use
  public static enum EdgeRole {male, female, top, bottom, 
    slidableSide, slidableBack, slidableFront, flat, flatLid,
    maleHinge, maleCutOut, femaleConnector, femaleBack}

  // constructor for normal panels
  public Panel (String id, PanelRole role, double height, double width) {
    this.id = id;
    this.role = role;
    this.height = height;
    this.width = width;
  }

  // constructor for hinge panels (the circle)
  public Panel (String id, PanelRole role, double radius) {
    this.id = id;
    this.role = role;
    this.radius = radius;
  }
}
