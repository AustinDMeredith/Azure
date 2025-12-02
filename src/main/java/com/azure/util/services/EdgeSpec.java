package com.azure.util.services;
/* Author: Austin Meredith
 * Date: 10.22.25
 * Description: Determines corner offset and number of teeth for any given edge. returns an array list, index 0 = corner width, index 1 = number of teeth
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;

public class EdgeSpec {
  public static ArrayList<Double> getEdgeSpec (
    double length, 
    double depth, 
    double toothWidth, 
    Panel.EdgeRole lastRole, 
    Panel.EdgeRole nextRole,
    boolean isHingedBottom
    ) {
    // ---- corner policy ----
    // Minimum corner offset for structural integrity.
    final double baseMinCorner = Math.max(depth * 2, 0.5 * toothWidth);

    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male || lastRole == Panel.EdgeRole.maleHinge || lastRole == Panel.EdgeRole.maleCutOut);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male || nextRole == Panel.EdgeRole.maleHinge || nextRole == Panel.EdgeRole.maleCutOut);
    // If neighbor is male, we retract by 'depth' at that corner, so ensure corner >= depth there.
    double requiredLeftCorner  = lastIsMale ? depth : 0.0;
    double requiredRightCorner = nextIsMale ? depth : 0.0;

    // Global minimum that satisfies structure + neighbors
    double minCorner = Math.max(baseMinCorner, Math.max(requiredLeftCorner, requiredRightCorner));
    minCorner = isHingedBottom ? minCorner + depth : minCorner;
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
    final double patternFootprint = (n == 0) ? 0.0 : ((2 * n - 1) * toothWidth); // the final space between the corners
    double corner = (length - patternFootprint) / 2.0; // the final space of each corner

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
    ArrayList<Double> obj = new ArrayList<Double>();
    obj.add(corner);
    obj.add((double)n);
    return obj;
  }
}
