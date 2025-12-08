package com.azure.util.pathGen;

import java.util.ArrayList;
import com.azure.util.services.EdgeSpec;
import com.azure.objects.Panel;
import com.azure.util.services.KerfService;

public class RailHoleGen {
  public static String gen (double length, double toothWidth, double depth, int dx, int dy, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, Panel panel) {
    final double edgeInset = 3.2 * 2;      
    final int px = -dy, py = dx;
    ArrayList<Double> sp = panel.startPoint; // MUST be top-left of this panel in SVG coords
    ArrayList<Double> startPoints = new ArrayList<Double>();
    StringBuilder sb = new StringBuilder();
    
    // gets the corner length and ammount of teeth(n)
    ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole, false);
    double corner = edgeSpec.get(0);
    double n = edgeSpec.get(1);

    // calls the kerf service to the toothkerf and the corner kerf
    ArrayList<Double> kerf = KerfService.getKerf();
    double toothKerf = kerf.get(0);
    double cornerKerf = kerf.get(1);

    // places the first starting point in the correct pos
    double cx = sp.get(0) + dx * (corner) - 3.175 + cornerKerf;
    double cy = sp.get(1) + dy * (corner) + py * edgeInset;

    startPoints.add(cx);

    for (int j = 0; j < n; j++) {
      sb.append(String.format("<path d=\"M%.3f %.3f ", cx, cy));
      // along edge
      sb.append(rel(dx * (toothWidth - toothKerf), dy * (toothWidth - toothKerf)));
      // inward
      sb.append(rel(px * depth, py * depth));
      // back along edge
      sb.append(rel(dx * -(toothWidth - toothKerf), dy * -(toothWidth - toothKerf)));
      // back outward to the inset baseline
      sb.append(rel(px * -depth, py * -depth));
      sb.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");

      // advance to next slot origin along the edge
      if (j < n - 1) {
        cx += dx * (2 * toothWidth);
        cy += dy * (2 * toothWidth);
        startPoints.add(cx);
      }
    }

    // used for testing
    panel.holeStartPoints = startPoints;
    return sb.toString();
  } 

  // helper for appending the lines using relative positions
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}      
     
     
     
     
