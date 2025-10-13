/* Author: Austin Meredith
 * Date: 10.13.25
 * Description: Scrpit for generating male edge paths
 * */

public class MalePathGen {
  public static String gen(double length, double toothWidth, double depth, int dx, int dy, Panel.EdgeRole lastRole, Panel.EdgeRole nextRole) {

    // sets the amount of teeth per edge and the corner offset
    double teethD = length / toothWidth;
    int teeth = (int)teethD / 2;
    double cornerOffset = ((length % teeth) + (toothWidth * 3)) / 2;
    teeth--;
    // flip when traversing backwards to keep corners aligned visually
    int px = dy, py = -dx;

    StringBuilder sb = new StringBuilder();
 
    if (lastRole == Panel.EdgeRole.male) {
      sb.append(rel(dx * (cornerOffset - depth), dy * (cornerOffset - depth)));
    } else {
      sb.append(rel(dx * cornerOffset, dy * cornerOffset));
    }
    for (int i = 0; i < teeth; i++) {
      if (i == teeth - 1) {
        // go OUT by 'depth'
        sb.append(rel(px * depth, py * depth));
        // half step along the edge baseline
        sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
        // return to baseline
        sb.append(rel(px * -depth, py * -depth));
      } else {
        // go OUT by 'depth'
        sb.append(rel(px * depth, py * depth));
        // half step along the edge baseline
        sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
        // return to baseline
        sb.append(rel(px * -depth, py * -depth));
        // second half step along baseline
        sb.append(rel(dx * (toothWidth), dy * (toothWidth)));
      }
    }
    if (nextRole == Panel.EdgeRole.male) {
      sb.append(rel(dx * (cornerOffset - depth), dy * (cornerOffset - depth)));
    } else {
      sb.append(rel(dx * cornerOffset, dy * cornerOffset));
    }
    
    return sb.toString();
  }
  
  // helper for relative "l" segment
  private static String rel(double rx, double ry) {
    return String.format("l%.3f %.3f ", rx, ry);
  }
}
