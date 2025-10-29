/* Author: Austin Meredith
 * Date: 10.29.25
 * Description: Class holds 2 functions, 1 to make the flat edge and 1 to make the holes for the teeth to slot into.
 * */

import java.util.ArrayList;

public class BottomPathGen {
  // This function generates edge path
  public static String edgeGen(
    double length,
    double depth,
    int dx, int dy,
    Panel panel
  ) {
    StringBuilder sb = new StringBuilder();
    // Sets the path so that the edge is length + (depth * 2)
    sb.append(rel(dx * (length + (depth * 2)), dy * (length + (depth * 2))));
    // Calling the function to set final edge length for later testing
    setFinalLength(panel, length, depth);

    return sb.toString();
  }

  // This function generates the hole paths for any given edge
  public static String holeGen(
    double toothWidth,
    double depth,
    int dx, int dy,
    Panel.EdgeRole lastRole,
    Panel.EdgeRole nextRole,
    Panel panel
  ) {
    // ---- basic validation ----
    if (toothWidth <= 0 || depth <= 0) {
      throw new IllegalArgumentException("length, toothWidth, and depth must be > 0");
    }
    // Keep "slot" depth reasonable relative to tooth width
    if (depth >= toothWidth) {
      // You can choose to clamp instead of throw; throwing is safer.
      throw new IllegalArgumentException("depth must be < toothWidth to keep material between slots.");
    }
  
    ArrayList<Double> sp = panel.startPoint;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 4; i++) {
      if (i % 2 == 0) {
        ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(panel.width, depth, toothWidth, lastRole, nextRole);
        double corner = edgeSpec.get(0);
        double n = edgeSpec.get(1);
          
        for (int j = 0; j < n; j++) {
          sb.append(String.format("<path d=\"M%.3f %.3f ", sp.get(0), sp.get(1)));

        }
      } else {
        ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(panel.height, depth, toothWidth, lastRole, nextRole);
        double corner = edgeSpec.get(0);
        double n = edgeSpec.get(1);
      }
      

      
    }
    return "";
  }

  // This function sets the final edge length for later testing
  private static void setFinalLength(Panel panel, double length, double depth) {
    double finalLength = length + (depth * 2);
    panel.finalEdgeLengths.add(finalLength);
  }

  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
