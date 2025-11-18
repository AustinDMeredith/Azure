package com.azure.util.pathGen;


import com.azure.objects.Panel;

public class FlatPathGen {
  public static String railGen (double length, double dx, double dy, Panel panel, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole) {
    // cheak for male on adjacent edges
    final boolean lastIsMale = (lastRole == Panel.EdgeRole.male);
    final boolean nextIsMale = (nextRole == Panel.EdgeRole.male);
    final boolean isBackRail = (panel.role == Panel.PanelRole.backRail);
    final boolean isTopRail = (panel.role == Panel.PanelRole.topRightRail || panel.role == Panel.PanelRole.topLeftRail);

    // if there is an adjacent male edge, reduce length by depth and if not reduce length by 12 to account for top side rails
    if (isBackRail) {
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 16;
    } else if (isTopRail) {
      length -= 3.175;
    } else {
      length = lastIsMale || nextIsMale ? length - 3.175 : length - 3.175 * 2;
    }
    StringBuilder sb = new StringBuilder();

    sb.append(rel(dx * length, dy * length));

    return sb.toString();
  }

  public static String gen (double length, double dx, double dy, Panel panel) {


    StringBuilder sb = new StringBuilder();
    sb.append(rel(dx * length, dy * length));
  
    return sb.toString();
  }
  
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
