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
    for (Panel panel : panels) {
      // used to track where panels need to be placed so they dont overlap each other
      lastHeight += panel.height;
      lastWidth += panel.width;

      if (panel.id == "p-0") { // p-0 should always be the front panel
        if (panel.edges.get(0) == Panel.EdgeRole.male) { // if starting edge is male the start pos is moved down 3.175 mm
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, 5.0 + 3.175);
        } else if (panel.edges.get(0) == Panel.EdgeRole.female) { // if the starting edge is female, dont move start pos
          panel.startPoint.add(0, 5.0);
          panel.startPoint.add(1, 5.0);
        }
      } else if (panel.id == "p-1") {
        panel.startPoint.add(0, lastWidth);
        panel.startPoint.add(0, lastWidth);
      }
    }
  }
}
