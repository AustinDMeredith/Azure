import java.util.ArrayList;

public class IngravingService {
  public static String addEngravings (String engraving, Panel panel) {
    StringBuilder sb = new StringBuilder();
      double px = panel.startPoint.get(0) + panel.width / 2;
      double py = panel.startPoint.get(1) + panel.height / 2;
      sb.append("<text font-size=\"20px\" style=\"font-family: sans-serif ; font-weight: bold; font-style: normal; fill: rgb(0,0,0)\" text-anchor=\"middle\" transform=\"matrix( 1.000 0.000 0.000 1.000 " + px + " " + py + " )\">" + engraving + "</text>");
    return sb.toString();
  }
}
