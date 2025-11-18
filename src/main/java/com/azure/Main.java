package com.azure;
/* Author: Austin Meredith
 * Date Created: 10.3.25
 * Last Changed: 11.15.25
 * Description: This class is incharge of calling constuctors for boxes and calling the function to generate svg files
 */

import com.azure.objects.*;
import com.azure.util.services.SvgGen;
import com.azure.util.services.ToleranceService;

public class Main {
 
  public static void main (String args[]) {

    ToleranceService.setGlobalCurrent("medium");
    BoxSpec slidingLidSimpleBox = new BasedBox(50, 50, 70, Panel.PanelRole.slidingLid, 6, "");
    SvgGen.generateFile(slidingLidSimpleBox.panels);
    //BoxSpec box1 = new SimpleBox(50, 50, 50, Panel.PanelRole.top, 6, "");
    //SvgGen.generateFile(box1.panels);
    System.out.print("Successfully Generated SVG!\n");
  }
}
