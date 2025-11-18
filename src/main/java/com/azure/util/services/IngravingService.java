package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 10.29.25
 * Last Changed: 11.3.25
 * Description: This class holds two functions, one to add personalized engravings to the side, and one to add default ingravings which name the sides
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;

public class IngravingService {
  public static void addEngravings (String engraving, ArrayList<Panel> panels) {
    for (int i = 0; i < 4; i++) {
      Panel panel = panels.get(i);
      double px, py;
      if (i == 0 || i == 1) {
        px = panel.startPoint.get(0) + (panel.width - 6.35) / 2;
        py = panel.startPoint.get(1) + (panel.height - 3.175) / 2;
      } else {
        px = panel.startPoint.get(0) + panel.width / 2;
        py = panel.startPoint.get(1) + (panel.height - 3.175) / 2;
      }
      
      StringBuilder sb = new StringBuilder();
      sb.append(panel.path);
      sb.append("<text dominant-baseline=\"middle\" font-size=\"20px\" style=\"font-family: sans-serif ; font-weight: bold; font-style: normal; fill: rgb(0,0,0)\" text-anchor=\"middle\" transform=\"matrix( 1.000 0.000 0.000 1.000 " + px + " " + py + " )\">" + engraving + "</text>\n");
      
      panel.path = sb.toString();
    }
  }

  // normal engraving function should go here
}
