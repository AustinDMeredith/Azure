/* Author: Austin Meredith
 * Date: 10.29.25
 * Description: Class holds 2 functions, 1 to make the flat edge and 1 to make the holes for the teeth to slot into.
 * */

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

  public static String holeGen(
    double length,
    double toothWidth,
    double depth,
    int dx, int dy,
    Panel.EdgeRole lastRole,
    Panel.EdgeRole nextRole,
    Panel panel
  ) {

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
