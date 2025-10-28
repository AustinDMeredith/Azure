/* Author: Austin Meredith
 * Date: 10/4/25
 * Description: This file holds edge specifications and path data to send to the svg service.
 * */
import java.util.ArrayList;

public class Panel {
  String id;
  PanelRole role;
  String path;
  double height, width;
  ArrayList<Double> startPoint = new ArrayList<Double>();
  ArrayList<EdgeRole> edges = new ArrayList<EdgeRole>();
  ArrayList<Double> finalEdgeLengths = new ArrayList<Double>();

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
