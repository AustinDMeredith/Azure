/* Author: Austin Meredith
 * Date: 10/4/25
 * Description: This file holds edge specifications and path data to send to the svg service.
 * */
import java.util.ArrayList;

public class PanelService {
  String id;
  ArrayList<Double> path = new ArrayList<Double>();
  EdgeRole edge1, edge2, edge3, edge4;

  public enum EdgeRole {male, female, top, bottom}
  
  // call the edge role setter here
}
