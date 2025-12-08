package com.azure.util.pathGen;
/* Author: Austin Meredith
 * Date Created: 10.13.25
 * Last Changed: 11.15.25
 * Description: Scrpit for generating male edge paths
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;
import com.azure.util.services.EdgeSpec;
import com.azure.util.services.KerfService;

public class MalePathGen {
  public static String gen(double length, double toothWidth, double depth, int dx, int dy, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, Panel panel, Panel.EdgeRole edgeRole) {
    // Perpendicular (flip) to the edge direction for "in/out" moves
    final int px = dy, py = -dx;

    ArrayList<Double> specs = getEdgeSpecs (lastRole, nextRole, length,toothWidth, depth, panel.role, edgeRole, panel);
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

    String path = genEdge(lastRole, nextRole, dx, dy, px, py, toothWidth, toothKerf, depth, n, leftCornerTravel, rightCornerTravel, panel.role, edgeRole);

    final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
    final double kerfAdded = ((n / 2) + 1) * toothKerf;
    final double kerfSubtracted = (n / 2) * toothKerf + cornerKerf * 2;
    setFinalLength(panel, corner, patternFootprint, kerfAdded, kerfSubtracted);

    return path;
    }

 // helper to define the specifications for the edge
  private static ArrayList<Double> getEdgeSpecs (Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, double length, double toothWidth, double depth, Panel.PanelRole role, Panel.EdgeRole edgeRole, Panel panel) {
    double corner;
    double n;
    double leftCornerTravel;
    double rightCornerTravel;

    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male || lastRole == Panel.EdgeRole.maleHinge || lastRole == Panel.EdgeRole.maleCutOut);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male || nextRole == Panel.EdgeRole.maleHinge || nextRole == Panel.EdgeRole.maleCutOut);      
    final boolean isTopRightRail = (role == Panel.PanelRole.topRightRail);
    final boolean isTopLeftRail = (role == Panel.PanelRole.topLeftRail);
    final boolean isBottomRightRail = (role == Panel.PanelRole.bottomRightRail);
    final boolean isBottomLeftRail = (role == Panel.PanelRole.bottomLeftRail);
    final boolean isBackRail = (role == Panel.PanelRole.backRail);
    final boolean isHinged = (edgeRole == Panel.EdgeRole.maleHinge || edgeRole == Panel.EdgeRole.maleCutOut);
    
    length -= (edgeRole == Panel.EdgeRole.maleHinge) ? 4.825 : 0;
    
    ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole, isHinged);
    corner = edgeSpec.get(0);
    n = edgeSpec.get(1);

    if (edgeRole == Panel.EdgeRole.maleCutOut) {
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      leftCornerTravel  = corner - (lastIsMale ? depth + 3 : 0.0) - ((role == Panel.PanelRole.leftTop) ? 8 : 0) + (nextIsMale ? 3 : 0);
      rightCornerTravel = corner - (nextIsMale ? depth + 3 : 0.0) - ((role == Panel.PanelRole.rightTop) ? 8 : 0) + (lastIsMale ? 3 : 0);
    } else {
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      leftCornerTravel  = corner - (lastIsMale ? depth : 0.0);
      rightCornerTravel = corner - (nextIsMale ? depth : 0.0);            
      leftCornerTravel = isBackRail ? leftCornerTravel - 8 : leftCornerTravel;
      rightCornerTravel = isBackRail ? rightCornerTravel - 8 : rightCornerTravel;
      leftCornerTravel = isTopLeftRail || isBottomRightRail || isBottomLeftRail ? leftCornerTravel - depth : leftCornerTravel;
      rightCornerTravel = isTopRightRail || isBottomLeftRail || isBottomRightRail ? rightCornerTravel - depth : rightCornerTravel;
    }

    // calls the kerf service to the toothkerf and the corner kerf
    ArrayList<Double> kerf = KerfService.getKerf();
    double toothKerf = kerf.get(0);
    
    double tol = panel.tolerance;
    toothKerf -= tol;
    double cornerKerf = toothKerf / 2;
    
    leftCornerTravel  -= cornerKerf;
    rightCornerTravel -= cornerKerf;

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
  private static String genEdge (Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, int dx, int dy, int px, int py, double toothWidth, double toothKerf, double depth, double n, double leftCornerTravel, double rightCornerTravel, Panel.PanelRole role, Panel.EdgeRole edgeRole) {
    double hingeRM = 0;
    StringBuilder sb = new StringBuilder();
 
    if (role == Panel.PanelRole.leftTop && edgeRole == Panel.EdgeRole.maleCutOut) {
      sb.append(relA(-8, -8, 0));
      sb.append(rel(px * -depth, py * -depth));
    } 

    if (role == Panel.PanelRole.rightBottom && lastRole == Panel.EdgeRole.flat) {
        sb.append(relA(8, -8, 1));
        sb.append(relA(8, 8, 1));
        sb.append(relA(-8, 8, 1));
        sb.append(rel(px * -depth, py * -depth));
        hingeRM = 3.175;
      }

    // ---- left corner offset ----
    sb.append(rel(dx * (leftCornerTravel - hingeRM), dy * (leftCornerTravel - hingeRM)));
    hingeRM = (edgeRole == Panel.EdgeRole.maleHinge && role == Panel.PanelRole.leftBottom) ? 3.175 : 0;

    // ---- inner teeth gen ----
    sb = innerTeethGen(sb, dx, dy, px, py, toothWidth, toothKerf, depth, n); 

    // ---- right corner offset ----
    sb.append(rel(dx * (rightCornerTravel - hingeRM), dy * (rightCornerTravel - hingeRM)));
 
    if (role == Panel.PanelRole.rightTop && edgeRole == Panel.EdgeRole.maleCutOut) {
      sb.append(rel(px * depth, py * depth));
      sb.append(relA(-8, 8, 0));
    }  

    if (role == Panel.PanelRole.leftBottom && nextRole == Panel.EdgeRole.flat) {
      sb.append(rel(px * depth, py * depth));
      sb.append(relA(-8, -8, 1));
      sb.append(relA(8, -8, 1));
      sb.append(relA(8, 8, 1));
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
      // go out by 'depth'
      sb.append(rel(px * depth, py * depth));
      // along baseline (slot width)
      sb.append(rel(dx * (toothWidth + toothKerf), dy * (toothWidth + toothKerf)));
      // go in by 'depth'
      sb.append(rel(px * -depth, py * -depth));

      // land between slots (except after the last slot)
      if (i < n - 1) {
        sb.append(rel(dx * (toothWidth - toothKerf), dy * (toothWidth - toothKerf)));
      }
    }
    return sb;
  }

  private static void setFinalLength(Panel panel, double corner, double patternFootprint, double kerfAdded, double kerfSubtracted) {
    double finalLength = (2 * corner) + patternFootprint + (kerfAdded - kerfSubtracted);
    panel.finalEdgeLengths.add(finalLength);
  }
  
  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }

  private static String relA(double dx, double dy, int sweep) {
      return String.format("a 8 8 0 0 %d %.3f %.3f ", sweep, dx, dy);
  }
}
