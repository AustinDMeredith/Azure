package com.azure.util.pathGen;


import com.azure.objects.Panel;
import com.azure.objects.BoxSpec;

public class FlatPathGen {
  public static String gen (double length, double dx, double dy, Panel panel, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole, BoxSpec.BoxType boxType) {
    StringBuilder sb = new StringBuilder();
    
    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);
    final boolean isBackRail = (panel.role == Panel.PanelRole.backRail);
    final boolean isTopRail = (panel.role == Panel.PanelRole.topRightRail || panel.role == Panel.PanelRole.topLeftRail);
    final boolean isBottomRail = (panel.role == Panel.PanelRole.bottomRightRail || panel.role == Panel.PanelRole.bottomLeftRail);
    final boolean isInnerLid = (panel.role == Panel.PanelRole.innerLid);
    final boolean isHinged = (boxType == BoxSpec.BoxType.hinged);

    double hingedRM = 0;
    if (isBackRail) {
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 16;
    } else if (isTopRail) {
      length -= 3.175;
    } else if (isBottomRail) {
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 3.175 * 2;
    } else if (isHinged) {
      hingedRM = (panel.role == Panel.PanelRole.frontTop || panel.role == Panel.PanelRole.frontBottom || panel.role == Panel.PanelRole.backTop || panel.role == Panel.PanelRole.backBottom) ? 0 : 8 + 3.175;
      hingedRM = (panel.role == Panel.PanelRole.backBottom) ? 3.175 * 2 : hingedRM;
    }
    length -= (isInnerLid) ? 3.175 : 0;
    
    sb.append(rel(dx * (length - hingedRM), dy * (length - hingedRM)));
  
    return sb.toString();
  }
  
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
