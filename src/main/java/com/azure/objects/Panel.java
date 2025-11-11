package com.azure.objects;
/* Author: Austin Meredith
 * Date Created: 10.4.25
 * Last Changed: 11.3.25
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

  public enum PanelRole {lid, front, back, right, left, bottom, basedBottom}
  public enum EdgeRole {male, female, top, bottom, slide}

  // constructor
  public Panel (String id, PanelRole role, double height, double width) {
    this.id = id;
    this.role = role;
    this.height = height;
    this.width = width;
  }
}
