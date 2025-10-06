/* Author: Austin Meredith
 * Date: 10/4/25
 * Description: This file holds edge specifications and path data to send to the svg service.
 * */
import java.util.ArrayList;

public class Panel {
  String id;
  PanelRole role;
  String path;
  ArrayList<EdgeRole> edges = new ArrayList<EdgeRole>();
  
  public enum PanelRole {lid, side, bottom}
  public enum EdgeRole {male, female, top, bottom, slide}

  // constructor
  public Panel (String id, PanelRole role) {
    this.id = id;
    this.role = role;
  }

  // call the path gen function here
}
