package com.azure.util.pathGen;
/* Author: Austin Meredith
 * Date Created: 10.13.25
 * Last Changed: 11.15.25
 * Description: Script for generating female edge paths
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;
import com.azure.util.services.EdgeSpec;
import com.azure.util.services.KerfService;

public class FemalePathGen {
  // public function to call on for edge gen
  public static String gen(double length, double toothWidth, double depth, int dx, int dy, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, Panel panel) {
    // Perpendicular (flip) to the edge direction for "in/out" moves
    final int px = dy, py = -dx;

    ArrayList<Double> specs = getEdgeSpecs (lastRole, nextRole, length,toothWidth, depth, panel.role, panel);
    double n = specs.get(0);
    double toothKerf = specs.get(1);
    double leftCornerTravel = specs.get(2);
    double rightCornerTravel = specs.get(3);
    double corner = specs.get(4);
    double cornerKerf = specs.get(5);

    if (leftCornerTravel < 0 || rightCornerTravel < 0) {
      // As a last resort (pathological tiny edges), fall back to a straight edge.
      return String.format("l%.3f %.3f ", dx * length, dy * length);
    }

    String path = genEdge(lastRole, nextRole, dx, dy, px, py, toothWidth, toothKerf, depth, n, leftCornerTravel, rightCornerTravel, panel.role);

    final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
    final double kerfSubtracted = ((n / 2) + 1) * toothKerf;
    final double kerfAdded = (n / 2) * toothKerf + cornerKerf * 2;
    setFinalLength(panel, corner, patternFootprint, kerfAdded, kerfSubtracted);

    return path;
  }

  // helper to define the specifications for the edge
  private static ArrayList<Double> getEdgeSpecs (Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, double length, double toothWidth, double depth, Panel.PanelRole role, Panel panel) {
    double corner;
    double n;
    double leftCornerTravel;
    double rightCornerTravel;

    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);      
    final boolean lastIsFlat = (lastRole == Panel.EdgeRole.flat);
    final boolean nextIsFlat = (nextRole == Panel.EdgeRole.flat);
    final boolean lastIsSlidingFront = (lastRole == Panel.EdgeRole.slidableFront);
    final boolean nextIsSlidingFront = (nextRole == Panel.EdgeRole.slidableFront);
    final boolean isBackBottom = (role == Panel.PanelRole.backBottom);
    final boolean isBackTop = (role == Panel.PanelRole.backTop);
    
    length -= isBackBottom ? 4.825 : 0;
    
    ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole, (isBackTop || isBackBottom));
    corner = edgeSpec.get(0);
    n = edgeSpec.get(1);

    if (role == Panel.PanelRole.backTop) {
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      leftCornerTravel  = corner - (lastIsMale ? depth + 3 : 8.0) + (nextIsMale ? 3 : 0);
      rightCornerTravel = corner - (nextIsMale ? depth + 3 : 8.0) + (lastIsMale ? 3 : 0);
    } else if (role == Panel.PanelRole.backBottom) {
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      leftCornerTravel = corner - (lastIsMale ? depth : 0.0) - (lastIsFlat ? depth : 0);
      rightCornerTravel = corner - (nextIsMale ? depth : 0.0) - (nextIsFlat ? depth : 0);
    } else {
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      leftCornerTravel  = corner - (lastIsMale ? depth : 0.0) - (lastIsSlidingFront ? depth * 2 : 0.0);
      rightCornerTravel = corner - (nextIsMale ? depth : 0.0) - (nextIsSlidingFront ? depth * 2 : 0.0);
    }

    // calls the kerf service to the toothkerf and the corner kerf
    ArrayList<Double> kerf = KerfService.getKerf(n);
    double toothKerf = kerf.get(0);
    double cornerKerf = kerf.get(1);

    double tol = panel.tolerance;
    toothKerf -= tol;
    cornerKerf += (n * tol) / 2;
    
    leftCornerTravel  +=  cornerKerf;
    rightCornerTravel += cornerKerf;

    // These should be >= 0 by construction; clamp tiny negatives caused by FP error.
    final double EPS = 1e-9;
    if (leftCornerTravel  < 0 && leftCornerTravel  > -EPS)  leftCornerTravel  = 0;
    if (rightCornerTravel < 0 && rightCornerTravel > -EPS)  rightCornerTravel = 0;

    ArrayList<Double> specs = new ArrayList<Double>();
    specs.add(n);
    specs.add(toothKerf);
    specs.add(leftCornerTravel);
    specs.add(rightCornerTravel);
    specs.add(corner);
    specs.add(cornerKerf);

    return specs;
  }

  // helper to generate the full edge
  private static String genEdge (Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, int dx, int dy, int px, int py, double toothWidth, double toothKerf, double depth, double n, double leftCornerTravel, double rightCornerTravel, Panel.PanelRole role) {
    StringBuilder sb = new StringBuilder();
    final boolean lastIsFlat = (lastRole == Panel.EdgeRole.flat);
    final boolean nextIsFlat = (nextRole == Panel.EdgeRole.flat);

    if (lastIsFlat && role == Panel.PanelRole.backBottom) {
      sb.append(rel(dx * 8, dy * 8));
      sb.append(rel(px * depth, py * depth));
    }

    if (lastIsFlat && role == Panel.PanelRole.backTop) {
      sb.append(rel(dx * (3 + (2*toothKerf)), dy * (3 + (2*toothKerf))));
      sb.append(rel(px * -depth, py * -depth));
      sb.append(rel(dx * (5 - toothKerf), dy * (5 - toothKerf)));
      sb.append(rel(px * depth, py * depth));
    }
    // ---- left corner offset ----
    sb.append(rel(dx * leftCornerTravel, dy * leftCornerTravel));

    // ---- inner teeth gen ----
    sb = innerTeethGen(sb, dx, dy, px, py, toothWidth, toothKerf, depth, n); 

    // ---- right corner offset ----
    sb.append(rel(dx * rightCornerTravel, dy * rightCornerTravel));

    if (nextIsFlat && role == Panel.PanelRole.backBottom) {
      sb.append(rel(px * -depth, py * -depth));
      sb.append(rel(dx * 8, dy * 8));
    }

    if (nextIsFlat && role == Panel.PanelRole.backTop) {
      sb.append(rel(px * -depth, py * -depth));
      sb.append(rel(dx * (5 - toothKerf), dy * (5 - toothKerf)));
      sb.append(rel(px * depth, py * depth));
      sb.append(rel(dx * (3 + (2*toothKerf)), dy * (3 + (2*toothKerf))));
    }

    return sb.toString();
  }

  // helper to generate the inner teeth of the edge
  private static StringBuilder innerTeethGen (StringBuilder sb, int dx, int dy, int px, int py, double toothWidth, double toothKerf, double depth, double n) {
    // ---- slots pattern ----
    // Pattern for each slot i in [0..n-1]:
    //   in by depth, along baseline by toothWidth, out by depth,
    //   AND if not last slot: advance another toothWidth baseline before next slot.
    for (int i = 0; i < n; i++) {
      // go in by 'depth'
      sb.append(rel(px * -depth, py * -depth));
      // along baseline (slot width)
      sb.append(rel(dx * (toothWidth - toothKerf), dy * (toothWidth - toothKerf)));
      // out by 'depth'
      sb.append(rel(px * depth, py * depth));

      // land between slots (except after the last slot)
      if (i < n - 1) {
        sb.append(rel(dx * (toothWidth + toothKerf), dy * (toothWidth + toothKerf)));
      }
    }
    return sb;
  }

  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }

  // helper to calculate final length of edge
  private static void setFinalLength(Panel panel, double corner, double patternFootprint, double kerfAdded, double kerfSubtracted) {
    double finalLength = (2 * corner) + patternFootprint + (kerfAdded - kerfSubtracted);
    panel.finalEdgeLengths.add(finalLength);
  }
}
