/* Author: Austin Meredith
 * Date: 10/7/25
 * Description: Generate SVG path strings with finger-joint teeth for a rectangular panel.
 */

public class PathGen {

  public static void generatePanelPath(Panel panel, double w, double h, int teethPerEdge, double toothDepth) {
    // 1) Start point (e.g., "5, 5")
    String[] s = LayoutService.findStartPoint().split(",");
    double x0 = Double.parseDouble(s[0].trim());
    double y0 = Double.parseDouble(s[1].trim());

    // Build path with a StringBuilder; use absolute M then relative l segments
    StringBuilder d = new StringBuilder();
    d.append(String.format("M%.3f %.3f ", x0, y0));

    // Set up edge sequence: top, right, bottom, left
    // You can consult panel.edges (male/female/top/bottom/slide) to choose patterns later:contentReference[oaicite:3]{index=3}.
    // For now, infer male on top/right and female on bottom/left as a demo.
    boolean[] maleEdge = new boolean[] { true, true, false, false };

    // Top edge → move +X
    d.append(edgeWithTeeth(w, teethPerEdge, toothDepth, 1, 0,  true,  maleEdge[0]));
    // Right edge → move +Y
    d.append(edgeWithTeeth(h, teethPerEdge, toothDepth, 0, 1,  true,  maleEdge[1]));
    // Bottom edge → move -X
    d.append(edgeWithTeeth(w, teethPerEdge, toothDepth, -1,0,  false, maleEdge[2]));
    // Left edge → move -Y
    d.append(edgeWithTeeth(h, teethPerEdge, toothDepth, 0,-1,  false, maleEdge[3]));

    // Close
    d.append("Z");

    // Assign back to the panel (Panel has a 'path' field):contentReference[oaicite:4]{index=4}
    panel.path = d.toString();
  }

  /**
   * Build one edge with teeth as relative segments.
   * @param length total edge length
   * @param teeth  number of teeth (segments)
   * @param depth  tooth depth (how far the tooth sticks out)
   * @param dx,dy  cardinal direction of the edge: (1,0)=+X, (-1,0)=-X, (0,1)=+Y, (0,-1)=-Y
   * @param forward if true we walk forward; if false we walk backward (used for alternating parity)
   * @param male if true teeth go "out", if false they go "in"
   */
  private static String edgeWithTeeth(double length, int teeth, double depth, int dx, int dy, boolean forward, boolean male) {

    // pitch along the main direction per tooth step
    double pitch = length / teeth;

    // Tooth parity: start OUT for male edges; start IN for female
    // Flip when traversing backwards to keep corners aligned visually
    int px = dy, py = -dx;

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < teeth; i++) {
    // half step along the edge baseline
    sb.append(rel(dx * (pitch / 2.0), dy * (pitch / 2.0)));
    // go OUT by 'depth'
    sb.append(rel(px * depth, py * depth));
    // second half step along baseline
    sb.append(rel(dx * (pitch / 2.0), dy * (pitch / 2.0)));
    // return to baseline
    sb.append(rel(px * -depth, py * -depth));
    }

    return sb.toString();
  }

  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
