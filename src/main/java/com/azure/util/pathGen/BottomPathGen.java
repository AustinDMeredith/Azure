package com.azure.util.pathGen;
/* Author: Austin Meredith
 * Date Created: 10.29.25
 * Last Changed: 11.3.25
 * Description: Class holds 2 functions, 1 to make the flat edge and 1 to make the holes for the teeth to slot into.
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;
import com.azure.util.services.EdgeSpec;

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

  // This function generates the hole paths for a based bottom panel
  public static String holeGen(double toothWidth, double depth, Panel panel, boolean isBasePanel) {
    if (toothWidth <= 0 || depth <= 0) throw new IllegalArgumentException();
    if (depth >= toothWidth) throw new IllegalArgumentException("depth must be < toothWidth");

    final double edgeInset  = isBasePanel ? depth : 0.0;   // perpendicular inset
    final double sideInset  = isBasePanel ? depth : 0.0;   // along-edge extra inset

    ArrayList<Double> sp = panel.startPoint; // MUST be top-left of this panel in SVG coords
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      // Edge directions (clockwise walk): top(+X), right(+Y), bottom(-X), left(-Y)
      final int dx, dy, last, next;
      if (i == 0) { dx = 1;  dy = 0;  next = 1; last = 3; }
      else if (i == 1) { dx = 0;  dy = 1;  next = 2; last = 0; }
      else if (i == 2) { dx = -1; dy = 0;  next = 3; last = 1; }
      else { dx = 0;  dy = -1; next = 0; last = 2; }

      // Inward normal for SVG coordinates (y increases downward)
      final int px = -dy, py = dx;

      // Edge length depends on edge orientation
      final double edgeLen = (i % 2 == 0) ? panel.width + depth * 2 : panel.height + depth * 2;

      // Get corner offset and slot count for this edge
      ArrayList<Double> spec = EdgeSpec.getEdgeSpec(edgeLen - depth * 2, depth, toothWidth, panel.edges.get(last), panel.edges.get(next), false);
      final double corner = spec.get(0);
      final int n = (int)Math.round(spec.get(1));

      // Start at: startPoint + (corner + sideInset) along edge + (edgeInset) inward
      double cx = sp.get(0) + dx * (corner + sideInset) + px * edgeInset;
      double cy = sp.get(1) + dy * (corner + sideInset) + py * edgeInset;

      // Draw n slots; each slot is toothWidth (along edge) by depth (inward)
      for (int j = 0; j < n; j++) {
        sb.append(String.format("<path d=\"M%.3f %.3f ", cx, cy));
        // along edge
        sb.append(rel(dx * toothWidth, dy * toothWidth));
        // inward
        sb.append(rel(px * depth, py * depth));
        // back along edge
        sb.append(rel(dx * -toothWidth, dy * -toothWidth));
        // back outward to the inset baseline
        sb.append(rel(px * -depth, py * -depth));
        sb.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");

        // advance to next slot origin along the edge
        if (j < n - 1) {
          cx += dx * (2 * toothWidth);
          cy += dy * (2 * toothWidth);
        }
      }

      // Advance the panel pen to the next corner (complete this edge walk)
      sp.set(0, sp.get(0) + dx * edgeLen);
      sp.set(1, sp.get(1) + dy * edgeLen);
    }

    return sb.toString();
  }


  // This function sets the final edge length for later testing
  private static void setFinalLength(Panel panel, double length, double depth) {
    // the real final length is length + (depth * 2) however we need to update our testing harnes for this to be tested properly
    double finalLength = length;
    panel.finalEdgeLengths.add(finalLength);
  }

  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
