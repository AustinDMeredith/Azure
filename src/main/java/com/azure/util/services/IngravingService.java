package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 10.29.25
 * Last Changed: 11.3.25
 * Description: This class holds two functions, one to add personalized engravings to the side, and one to add default ingravings which name the sides
 * */

import java.util.ArrayList;

import com.azure.objects.Panel;
import com.azure.objects.BoxSpec;

public class IngravingService {
  public static void addEngravings (String engraving, double size, ArrayList<Panel> panels, BoxSpec.BoxType boxType) {
    boolean isHinged = (boxType == BoxSpec.BoxType.hinged);
    if (isHinged) { // if the box is hinged then only place the engraving on the bottom panels
      for (int i = 1; i < 8; i+=2) {
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
        sb.append("<text dominant-baseline=\"middle\" font-size=\"" + size + "\" style=\"font-family: sans-serif ; font-weight: bold; font-style: normal; fill: rgb(0,0,0)\" text-anchor=\"middle\" transform=\"matrix( 1.000 0.000 0.000 1.000 " + px + " " + py + " )\">" + engraving + "</text>\n");
        
        panel.path = sb.toString();
      }
    } else { // if the box is not hinged then place the engraving on the sides
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
        sb.append("<text dominant-baseline=\"middle\" font-size=\"" + size + "\" style=\"font-family: sans-serif ; font-weight: bold; font-style: normal; fill: rgb(0,0,0)\" text-anchor=\"middle\" transform=\"matrix( 1.000 0.000 0.000 1.000 " + px + " " + py + " )\">" + engraving + "</text>\n");
        
        panel.path = sb.toString();
      }
    }
  }
}
