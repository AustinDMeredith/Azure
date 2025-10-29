/* Author: Austin Meredith
 * Date: 10/7/25
 * Description: Generate SVG path strings with finger-joint teeth for a rectangular panel.
 */

public class PathGen {

  public static void generatePanelPath(Panel panel, double toothWidth) {
    // 1) Start point, tooth depth, height and width of panel
    double x0 = panel.startPoint.get(0);
    double y0 = panel.startPoint.get(1);
    double toothDepth = 3.175;
    double w = panel.width;
    double h = panel.height;

    // Build path with a StringBuilder; use absolute M then relative l segments
    StringBuilder d = new StringBuilder();
    d.append(String.format("M%.3f %.3f ", x0, y0));

    // Set up edge sequence: top, right, bottom, left
    int i = 0;
    for (Panel.EdgeRole edgeRole : panel.edges) {
      int dx, dy, last, next;
      if (i == 0) {dx = 1; dy = 0; next = 1; last = 3;}
      else if (i == 1) {dx = 0; dy = 1; next = 2; last = 0;}
      else if (i == 2) {dx = -1; dy = 0; next = 3; last = 1;}
      else {dx = 0; dy = -1; next = 0; last = 2;}

      if (edgeRole == Panel.EdgeRole.male) {
        if (i % 2 == 0) {
          d.append(MalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel)); 
        } else {
          d.append(MalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));    
        }
      } else if (edgeRole == Panel.EdgeRole.female) {
        if (i % 2 == 0) {
          d.append(FemalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        } else {
          d.append(FemalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        }
      } else if (edgeRole == Panel.EdgeRole.bottom) {
        if (i % 2 ==0) {

        } else {
          
        }
      }
      i++;
    }
    // Close
    d.append("Z");

    // Assign back to the panel 
    panel.path = d.toString();
  }
}
