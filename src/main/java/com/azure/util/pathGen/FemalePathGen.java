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
import com.azure.util.services.ToleranceService;

public class FemalePathGen {
  public static String gen(
      double length,
      double toothWidth,
      double depth,
      int dx, int dy,
      Panel.EdgeRole lastRole,
      Panel.EdgeRole nextRole,
      Panel panel
    ) {
      // ---- basic validation ----
      if (length <= 0 || toothWidth <= 0 || depth <= 0) {
        throw new IllegalArgumentException("length, toothWidth, and depth must be > 0");
      }
      // Keep "slot" depth reasonable relative to tooth width
      if (depth >= toothWidth) {
        // You can choose to clamp instead of throw; throwing is safer.
        throw new IllegalArgumentException("depth must be < toothWidth to keep material between slots.");
      }

      // Perpendicular (flip) to the edge direction for "in/out" moves
      final int px = dy, py = -dx;

      final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
      final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);      
      final boolean lastIsSlidingFront = (lastRole == Panel.EdgeRole.slidableFront);
      final boolean nextIsSlidingFront = (nextRole == Panel.EdgeRole.slidableFront);

      // calls edge spec to get the number of teeth (n) and the corner length (corner)
      ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole, false);
      double corner = edgeSpec.get(0);
      double n = edgeSpec.get(1);

      // calls the kerf service to the toothkerf and the corner kerf
      ArrayList<Double> kerf = KerfService.getKerf(n);
      double toothKerf = kerf.get(0);
      double cornerKerf = kerf.get(1);

      double tol = ToleranceService.getGlobalCurrent();
      toothKerf -= tol;
      cornerKerf -= tol;
      
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      double leftCornerTravel  = corner - (lastIsMale ? depth : 0.0) - (lastIsSlidingFront ? depth * 2 : 0.0);
      double rightCornerTravel = corner - (nextIsMale ? depth : 0.0) - (nextIsSlidingFront ? depth * 2 : 0.0);

      // These should be >= 0 by construction; clamp tiny negatives caused by FP error.
      final double EPS = 1e-9;
      if (leftCornerTravel  < 0 && leftCornerTravel  > -EPS)  leftCornerTravel  = 0;
      if (rightCornerTravel < 0 && rightCornerTravel > -EPS)  rightCornerTravel = 0;

      if (leftCornerTravel < 0 || rightCornerTravel < 0) {
        // As a last resort (pathological tiny edges), fall back to a straight edge.
        return String.format("l%.3f %.3f ", dx * length, dy * length);
      }

      StringBuilder sb = new StringBuilder();

      // ---- left corner offset ----
      sb.append(rel(dx * (leftCornerTravel + cornerKerf), dy * (leftCornerTravel + cornerKerf)));

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

      // ---- right corner offset ----
      sb.append(rel(dx * (rightCornerTravel + cornerKerf), dy * (rightCornerTravel + cornerKerf)));

      final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
      setFinalLength(panel, corner, patternFootprint);

      return sb.toString();
    }

  public static String backGen (
      double length,
      double toothWidth,
      double depth,
      int dx, int dy,
      Panel.EdgeRole lastRole,
      Panel.EdgeRole nextRole,
      Panel panel
    ) {
      // ---- basic validation ----
      if (length <= 0 || toothWidth <= 0 || depth <= 0) {
        throw new IllegalArgumentException("length, toothWidth, and depth must be > 0");
      }
      // Keep "slot" depth reasonable relative to tooth width
      if (depth >= toothWidth) {
        // You can choose to clamp instead of throw; throwing is safer.
        throw new IllegalArgumentException("depth must be < toothWidth to keep material between slots.");
      }

      // Perpendicular (flip) to the edge direction for "in/out" moves
      final int px = dy, py = -dx;

      final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
      final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);      
      final boolean lastIsFlat = (lastRole == Panel.EdgeRole.flat);
      final boolean nextIsFlat = (nextRole == Panel.EdgeRole.flat);

      // calls edge spec to get the number of teeth (n) and the corner length (corner)
      ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length - 4.825, depth, toothWidth, lastRole, nextRole, true);
      double corner = edgeSpec.get(0);
      double n = edgeSpec.get(1);

      // calls the kerf service to the toothkerf and the corner kerf
      ArrayList<Double> kerf = KerfService.getKerf(n);
      double toothKerf = kerf.get(0);
      double cornerKerf = kerf.get(1);

      double tol = ToleranceService.getGlobalCurrent();
      toothKerf -= tol;
      cornerKerf -= tol;
      
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      double leftCornerTravel = corner - (lastIsMale ? depth : 0.0) - (lastIsFlat ? depth : 0);
      double rightCornerTravel = corner - (nextIsMale ? depth : 0.0) - (nextIsFlat ? depth : 0);

      // These should be >= 0 by construction; clamp tiny negatives caused by FP error.
      final double EPS = 1e-9;
      if (leftCornerTravel  < 0 && leftCornerTravel  > -EPS)  leftCornerTravel  = 0;
      if (rightCornerTravel < 0 && rightCornerTravel > -EPS)  rightCornerTravel = 0;

      if (leftCornerTravel < 0 || rightCornerTravel < 0) {
        // As a last resort (pathological tiny edges), fall back to a straight edge.
        return String.format("l%.3f %.3f ", dx * length, dy * length);
      }

      StringBuilder sb = new StringBuilder();

      if (lastIsFlat) {
        sb.append(rel(dx * 8, dy * 8));
        sb.append(rel(px * depth, py * depth));
      }

      // ---- left corner offset ----
      sb.append(rel(dx * (leftCornerTravel + cornerKerf), dy * (leftCornerTravel + cornerKerf)));

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

      // ---- right corner offset ----
      sb.append(rel(dx * (rightCornerTravel + cornerKerf), dy * (rightCornerTravel + cornerKerf)));

      if (nextIsFlat) {
        sb.append(rel(px * -depth, py * -depth));
        sb.append(rel(dx * 8, dy * 8));
      }

      final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
      setFinalLength(panel, corner, patternFootprint);

      return sb.toString();
    }

  public static String connectorGen (
      double length,
      double toothWidth,
      double depth,
      int dx, int dy,
      Panel.EdgeRole lastRole,
      Panel.EdgeRole nextRole,
      Panel panel
    ) {
      // ---- basic validation ----
      if (length <= 0 || toothWidth <= 0 || depth <= 0) {
        throw new IllegalArgumentException("length, toothWidth, and depth must be > 0");
      }
      // Keep "slot" depth reasonable relative to tooth width
      if (depth >= toothWidth) {
        // You can choose to clamp instead of throw; throwing is safer.
        throw new IllegalArgumentException("depth must be < toothWidth to keep material between slots.");
      }

      // Perpendicular (flip) to the edge direction for "in/out" moves
      final int px = dy, py = -dx;

      final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
      final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);      
      final boolean lastIsFlat = (lastRole == Panel.EdgeRole.flat);
      final boolean nextIsFlat = (nextRole == Panel.EdgeRole.flat);

      // calls edge spec to get the number of teeth (n) and the corner length (corner)
      ArrayList<Double> edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole, true);
      double corner = edgeSpec.get(0);
      double n = edgeSpec.get(1);

      // calls the kerf service to the toothkerf and the corner kerf
      ArrayList<Double> kerf = KerfService.getKerf(n);
      double toothKerf = kerf.get(0);
      double cornerKerf = kerf.get(1);

      double tol = ToleranceService.getGlobalCurrent();
      toothKerf -= tol;
      cornerKerf -= tol;
      
      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      double leftCornerTravel  = corner - (lastIsMale ? depth + 3 : 8.0) + (nextIsMale ? 3 : 0);
      double rightCornerTravel = corner - (nextIsMale ? depth + 3 : 8.0) + (lastIsMale ? 3 : 0);

      // These should be >= 0 by construction; clamp tiny negatives caused by FP error.
      final double EPS = 1e-9;
      if (leftCornerTravel  < 0 && leftCornerTravel  > -EPS)  leftCornerTravel  = 0;
      if (rightCornerTravel < 0 && rightCornerTravel > -EPS)  rightCornerTravel = 0;

      if (leftCornerTravel < 0 || rightCornerTravel < 0) {
        // As a last resort (pathological tiny edges), fall back to a straight edge.
        return String.format("l%.3f %.3f ", dx * length, dy * length);
      }

      StringBuilder sb = new StringBuilder();

      if (lastIsFlat) {
        sb.append(rel(dx * (3 + toothKerf), dy * (3 + toothKerf)));
        sb.append(rel(px * -depth, py * -depth));
        sb.append(rel(dx * (5 - toothKerf), dy * (5 - toothKerf)));
        sb.append(rel(px * depth, py * depth));
      }

      // ---- left corner offset ----
      sb.append(rel(dx * (leftCornerTravel + cornerKerf), dy * (leftCornerTravel + cornerKerf)));

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

      // ---- right corner offset ----
      sb.append(rel(dx * (rightCornerTravel + cornerKerf), dy * (rightCornerTravel + cornerKerf)));

      if (nextIsFlat) {
        sb.append(rel(px * -depth, py * -depth));
        sb.append(rel(dx * (5 - toothKerf), dy * (5 - toothKerf)));
        sb.append(rel(px * depth, py * depth));
        sb.append(rel(dx * (3 + toothKerf), dy * (3 + toothKerf)));
      }

      final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
      setFinalLength(panel, corner, patternFootprint);

      return sb.toString();
    }
  private static void setFinalLength(Panel panel, double corner, double patternFootprint) {
    // 2*corner + (2n - 1)*toothWidth == length
    double finalLength = (2 * corner) + patternFootprint;
    panel.finalEdgeLengths.add(finalLength);
  }

  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
