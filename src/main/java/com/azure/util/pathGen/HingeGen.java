package com.azure.util.pathGen;

import java.util.ArrayList;

import com.azure.objects.Panel;
import com.azure.util.services.KerfService;

public class HingeGen {
  // appends a circle to the given positions
  public static String genHinge (double x0, double y0) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("<path d=\" M %.3f %.3f ", x0, y0));
    sb.append("a 5 5 0 0 1 10 0 ");
    sb.append("a 5 5 0 0 1 -10 0 ");
    sb.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");
    return sb.toString();
  }

  // appends a hole for the back top panel to attach to the hinges
  public static String genHole (double x0, double y0, double depth, Panel panel) {
    double toothWidth = 3;
    int dx = 0, dy = 1;
    final int px = dy, py = -dx;
    ArrayList<Double> kerf = KerfService.getKerf();
    double toothKerf = kerf.get(0);

    double tol = panel.tolerance;
    toothKerf -= tol;
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("<path d= \" M %.3f %.3f ", x0, y0));
    sb.append(rel(px * (depth - toothKerf), py * (depth - toothKerf)));
    sb.append(rel(dx * (toothWidth + toothKerf), dy * (toothWidth + toothKerf)));
    sb.append(rel(px * -(depth - toothKerf), py * -(depth - toothKerf)));
    sb.append(rel(dx * -(toothWidth - toothKerf), dy * -(toothWidth - toothKerf)));
    sb.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");
    return sb.toString();
  }

  // helper for appending the lines using relative positions
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
