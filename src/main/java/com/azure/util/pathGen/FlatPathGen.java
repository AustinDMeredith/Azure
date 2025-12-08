package com.azure.util.pathGen;


import com.azure.objects.Panel;
import com.azure.objects.BoxSpec;

public class FlatPathGen {
  public static String gen (double length, double dx, double dy, Panel panel, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, BoxSpec.BoxType boxType) {
    StringBuilder sb = new StringBuilder();
 
    // some checks for panel role, last and next edge roles, and box types
    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);
    final boolean isBackRail = (panel.role == Panel.PanelRole.backRail);
    final boolean isTopRail = (panel.role == Panel.PanelRole.topRightRail || panel.role == Panel.PanelRole.topLeftRail);
    final boolean isBottomRail = (panel.role == Panel.PanelRole.bottomRightRail || panel.role == Panel.PanelRole.bottomLeftRail);
    final boolean isHinged = (boxType == BoxSpec.BoxType.hinged);

    // removes a certain amount based on what the panel or edge role is
    double hingedRM = 0;
    if (isBackRail) {
      // removes 16mm from the width and depth from the height
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 16;
    } else if (isTopRail) {
      // removes depth from the width
      length -= 3.175;
    } else if (isBottomRail) {
      // removes depth from the height and depth*2 from the length
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 3.175 * 2;
    } else if (isHinged) {
      // removes 8 + depth from the legth if the flat edge is on a hinged box 
      hingedRM = (panel.role == Panel.PanelRole.frontTop || panel.role == Panel.PanelRole.frontBottom || panel.role == Panel.PanelRole.backTop || panel.role == Panel.PanelRole.backBottom) ? 0 : 8 + 3.175;
      hingedRM = (panel.role == Panel.PanelRole.backBottom) ? 3.175 * 2 : hingedRM;
    }
    
    // append the calulated length
    sb.append(rel(dx * (length - hingedRM), dy * (length - hingedRM)));

    // calculate the final length
    setFinalLength(panel, length, hingedRM);
  
    return sb.toString();
  }
  
  // helper for appending the lines using relative positions
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }

  // helper to help calculate the final length
  private static void setFinalLength(Panel panel, double length, double hingedRM) {
    double finalLength = length - hingedRM;
    panel.finalEdgeLengths.add(finalLength);
  }
}
