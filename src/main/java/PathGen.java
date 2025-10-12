/* Author: Austin Meredith
 * Date: 10/7/25
 * Description: Generate SVG path strings with finger-joint teeth for a rectangular panel.
 */

public class PathGen {

  public static void generatePanelPath(Panel panel, double toothWidth) {
    // 1) Start point (e.g., "5, 5")
    double x0 = panel.startPoint.get(0);
    double y0 = panel.startPoint.get(1);
    double toothDepth = 3.175;
    double w = panel.width;
    double h = panel.height;

    // Build path with a StringBuilder; use absolute M then relative l segments
    StringBuilder d = new StringBuilder();
    d.append(String.format("M%.3f %.3f ", x0, y0));

    // Set up edge sequence: top, right, bottom, left
    // Top edge → move +X
    d.append(edgeWithTeeth(w, toothWidth, toothDepth, 1, 0, panel.edges.get(0)));
    // Right edge → move +Y
    d.append(edgeWithTeeth(h, toothWidth, toothDepth, 0, 1, panel.edges.get(1)));
    // Bottom edge → move -X
    d.append(edgeWithTeeth(w, toothWidth, toothDepth, -1, 0, panel.edges.get(2)));
    // Left edge → move -Y
    d.append(edgeWithTeeth(h, toothWidth, toothDepth, 0, -1, panel.edges.get(3)));

    // Close
    d.append("Z");

    // Assign back to the panel 
    panel.path = d.toString();
  }

  private static String edgeWithTeeth(double length, double toothWidth, double depth, int dx, int dy, Panel.EdgeRole role) {

    // sets the amount of teeth per edge and the corner offset
    double teethD = length / toothWidth;
    int teeth = (int)teethD / 2;
    double cornerOffset = ((length % teeth) + toothWidth) / 2;
    // flip when traversing backwards to keep corners aligned visually
    int px = dy, py = -dx;

    StringBuilder sb = new StringBuilder();
       
    if (role == Panel.EdgeRole.male) {
      sb.append(rel(dx * (cornerOffset - depth), dy * (cornerOffset - depth)));
      for (int i = 0; i < teeth; i++) {
        if (i == teeth - 1) {
          // go OUT by 'depth'
          sb.append(rel(px * depth, py * depth));
          // half step along the edge baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
          // return to baseline
          sb.append(rel(px * -depth, py * -depth));
        } else {
          // go OUT by 'depth'
          sb.append(rel(px * depth, py * depth));
          // half step along the edge baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
          // return to baseline
          sb.append(rel(px * -depth, py * -depth));
          // second half step along baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
        }
      }
      sb.append(rel(dx * (cornerOffset - depth), dy * (cornerOffset - depth)));
    } else if (role == Panel.EdgeRole.female) {
      sb.append(rel(dx * cornerOffset, dy * cornerOffset));
      for (int i = 0; i < teeth; i++) {
        if (i == teeth - 1) {
          // go in by 'depth'
          sb.append(rel(px * -depth, py * -depth));
          // half step along the edge baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
          // return out by 'depth'
          sb.append(rel(px * depth, py * depth));
        } else {
          // go in by 'depth'
          sb.append(rel(px * -depth, py * -depth));
          // half step along the edge baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
          // return out by 'depth'
          sb.append(rel(px * depth, py * depth));
          // second half step along baseline
          sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
        }
      }
      sb.append(rel(dx * cornerOffset, dy * cornerOffset));
    }

    return sb.toString();
  }

  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
