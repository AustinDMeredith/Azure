/* Author: Austin Meredith
 * Date: 10.13.25
 * Description: Scrpit for generating female edge paths
 * */

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

      // ---- corner policy ----
      // Minimum corner offset for structural integrity.
      // You can tune this rule; a common heuristic is max(depth, 0.5*toothWidth).
      final double baseMinCorner = Math.max(depth, 0.5 * toothWidth);

      final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
      final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);

      // If neighbor is male, we retract by 'depth' at that corner, so ensure corner >= depth there.
      double requiredLeftCorner  = lastIsMale ? depth : 0.0;
      double requiredRightCorner = nextIsMale ? depth : 0.0;

      // Global minimum that satisfies structure + neighbors
      final double minCorner = Math.max(baseMinCorner, Math.max(requiredLeftCorner, requiredRightCorner));

      // ---- choose number of slots n so corners can be >= minCorner ----
      // For n slots, baseline footprint inside the pattern is (2n - 1) * toothWidth.
      // Remaining baseline is shared by the two corners: corner = (length - (2n - 1)*toothWidth)/2.
      // We need corner >= minCorner  => (2n - 1)*toothWidth <= length - 2*minCorner
      final double maxFootprint = Math.max(0.0, length - 2.0 * minCorner);
      int n = (int) Math.floor((maxFootprint / toothWidth + 1.0) / 2.0); // derived from inequality above

      // Always allow n >= 0
      if (n < 0) n = 0;

      // If the edge is very short, n can be 0 (no slots). That’s fine & robust.
      // Recompute corner to absorb the remainder exactly.
      final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth);
      double corner = (length - patternFootprint) / 2.0;

      // Due to rounding, corner should already be >= minCorner, but clamp defensively
      if (corner < minCorner) {
        // Reduce n until corner is valid
        while (n > 0) {
          n--;
          double f = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth);
          corner = (length - f) / 2.0;
          if (corner >= minCorner) break;
        }
        // If still invalid (extremely short edge), set n=0 and clamp corner to length/2 (no pattern)
        if (corner < minCorner) {
          n = 0;
          corner = Math.max(minCorner, length / 2.0);
          if (corner * 2.0 > length) {
            // With no teeth, just walk the baseline; offsets won’t be used.
            corner = length / 2.0;
          }
        }
      }

      // Now we have: n >= 0, and corner chosen so that:
      //   2*corner + (2n - 1)*toothWidth == length   (exact),
      //   and corner >= minCorner.

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
        // go in by 'depth'
        sb.append(rel(px * -depth, py * -depth));
        // along baseline (slot width)
        sb.append(rel(dx * toothWidth, dy * toothWidth));
        // out by 'depth'
        sb.append(rel(px * depth, py * depth));

        // land between slots (except after the last slot)
        if (i < n - 1) {
          sb.append(rel(dx * toothWidth, dy * toothWidth));
        }
      }

      // ---- right corner offset ----
      sb.append(rel(dx * rightCornerTravel, dy * rightCornerTravel));

      int interiorSteps = (2 * n) + 1;
      setFinalLength(panel, leftCornerTravel, rightCornerTravel, toothWidth, interiorSteps);

      return sb.toString();
    }

  private static void setFinalLength(Panel panel, double leftCornerTravel, double rightCornerTravel, double toothWidth, int interiorSteps) {
    double finalLength = leftCornerTravel + rightCornerTravel + interiorSteps * toothWidth;
    panel.finalEdgeLengths.add(finalLength);
  }

  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
