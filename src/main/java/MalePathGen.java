/* Author: Austin Meredith
 * Date: 10.13.25
 * Description: Scrpit for generating male edge paths
 * */

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MalePathGen {
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

      String edgeSpec = EdgeSpec.getEdgeSpec(length, depth, toothWidth, lastRole, nextRole);
      JsonObject json = JsonParser.parseString(edgeSpec).getAsJsonObject();
      double corner = json.get("corner").getAsDouble();
      double n = json.get("n").getAsDouble();

      // Effective corner travel on each side after accounting for neighbor male-depth retraction.
      double leftCornerTravel  = corner - (lastIsMale ? depth : 0.0);
      double rightCornerTravel = corner - (nextIsMale ? depth : 0.0);

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
      sb.append(rel(dx * leftCornerTravel, dy * leftCornerTravel));

      // ---- slots pattern ----
      // Pattern for each slot i in [0..n-1]:
      //   in by depth, along baseline by toothWidth, out by depth,
      //   AND if not last slot: advance another toothWidth baseline before next slot.
      for (int i = 0; i < n; i++) {
        // go OUT by 'depth'
        sb.append(rel(px * depth, py * depth));
        // half step along the edge baseline
        sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
        // return to baseline
        sb.append(rel(px * -depth, py * -depth));

        // land between slots (except after the last slot)
        if (i < n - 1) {
          sb.append(rel(dx * toothWidth, dy * toothWidth));
        }
      }

      // ---- right corner offset ----
      sb.append(rel(dx * rightCornerTravel, dy * rightCornerTravel));

      final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
      setFinalLength(panel, corner, patternFootprint);

      return sb.toString();
    }

  private static void setFinalLength(Panel panel, double corner, double patternFootprint) {
    double finalLength = (2 * corner) + patternFootprint;
    panel.finalEdgeLengths.add(finalLength);
  }
  
  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
