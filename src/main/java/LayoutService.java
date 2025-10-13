/* Author: Austin Meredith
 * Date: 10.9.25
 * Description: This file takes decides what cords to use when starting the path of each panel.
 * */
import java.util.ArrayList;

public class LayoutService {
  static double lastWidth = 0;
  static double lastHeight = 0;
  public static void findStartPoint(ArrayList<Panel> panels) {
    // logic to find and set the start point
    
    lastHeight = 0;
    lastWidth = 0;
    for (Panel panel : panels) {

      if (panel.id == "p-0") { // p-0 should always be the front panel
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, 5.0);
        }
      } else if (panel.id == "p-1") {
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, 5.0);
        }
      } else if (panel.id == "p-2") { 
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, lastHeight + 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, lastHeight + 5.0);
        }
      } else if (panel.id == "p-3") {
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, lastHeight + 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, lastHeight + 5.0);
        }
      } else if (panel.id == "p-4") {
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, lastHeight + 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, lastHeight + 5.0);
        }
      } else if (panel.id == "p-5") {
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, lastHeight + 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, lastWidth + 5.0);
          panel.startPoint.add(1, lastHeight + 5.0);
        }
      }
      
      // used to track where panels need to be placed so they dont overlap each other
      lastHeight += panel.height;
      lastWidth += panel.width;
    }
  }
}
