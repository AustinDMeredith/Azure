package com.azure.util.pathGen;

import java.util.ArrayList;
import com.azure.objects.Panel;
import com.azure.util.services.KerfService;

public class LidAccessoryGen {
  public static String holeGen (double length, double x0, double y0, Panel panel) {
    StringBuilder sb = new StringBuilder();
    double depth = 3.175;
    int dx = 1, dy = 0;
    double px = dy, py = -dx;
    
    // calls the kerf service to the toothkerf and the corner kerf
    ArrayList<Double> kerfArr = KerfService.getKerf(1);
    double kerf = kerfArr.get(0);

    double tol = panel.tolerance;
    kerf -= tol;
    
    sb.append(String.format("<path d=\" M %.3f %.3f ", x0, y0));
    sb.append(rel(dx * (length - kerf), dy * (length - kerf)));
    sb.append(rel(px * -(depth - kerf), py * -(depth - kerf)));
    sb.append(rel(dx * -(length - kerf), dy * -(length - kerf)));
    sb.append(rel(px * (depth - kerf), py * (depth - kerf)));
    sb.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");
    
    return sb.toString();
  }

  public static String handleGen (double length, double x0, double y0) {
    StringBuilder sb = new StringBuilder();
    
    double h = 20.0;   // handle height
    double r = 5.0;    // corner radius

    // Start at left top corner + radius (so arcs cap the ends)
    double startX = x0 + r;
    double startY = y0;

    sb.append(String.format("<path d=\"M %.3f %.3f ", startX, startY));

    // Top edge: go right (length - 2r)
    sb.append(rel(length - (2 * r), 0));

    // Top-right corner: quarter-circle downwards
    sb.append(String.format("a %.3f %.3f 0 0 1 %.3f %.3f ", r, r, r, r));

    // Right side: down h - 2r
    sb.append(rel(0, h - 2 * r));

    sb.append(rel(2, 0));

    sb.append(rel(0, 2));

    // Bottom edge: back left
    sb.append(rel(-(length + 4), 0));

    sb.append(rel(0, -2));

    sb.append(rel(2, 0));

    // Left side: up
    sb.append(rel(0, -(h - 2 * r)));

    // Top-left corner
    sb.append(String.format("a %.3f %.3f 0 0 1 %.3f %.3f ", r, r, r, -r));

    sb.append("\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\" fill=\"none\"/>\n");

    return sb.toString();
  }
  
  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
