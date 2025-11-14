package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.13.25
 * Last Changed: 11.13.25
 * Description: Adds the required panels to the box from which it was called 
 * */

import java.util.ArrayList;

import com.azure.objects.BoxSpec;
import com.azure.objects.Panel;

public class AddPanels {
  public static void Set (double height, double width, double depth, Panel.PanelRole lidType, BoxSpec.BoxType boxType, ArrayList<Panel> panels) {
    // Check if lid is sliding or not
    boolean isSlidingLid = (lidType == Panel.PanelRole.slidingLid); 
    // Check if box is based or not
    boolean isBased = (boxType == BoxSpec.BoxType.based);
    // Check if box is hinged or not
    boolean isHinged = (boxType == BoxSpec.BoxType.hinged);
    // If box is based, bottom equals "basedBottom", if not bottom equals "bottom"
    Panel.PanelRole bottom = isBased ? Panel.PanelRole.basedBottom : Panel.PanelRole.bottom;

    if (!isSlidingLid && !isHinged) {
      panels.add(new Panel("p-0", Panel.PanelRole.front, height, width));
      panels.add(new Panel("p-1", Panel.PanelRole.back, height, width));
      panels.add(new Panel("p-2", Panel.PanelRole.right, height, depth));
      panels.add(new Panel("p-3", Panel.PanelRole.left, height, depth));
      panels.add(new Panel("p-4", lidType, width, depth));
      panels.add(new Panel("p-5", bottom, width, depth));
    } else if (isSlidingLid && !isHinged) {
      panels.add(new Panel("p-0", Panel.PanelRole.slidableFront, height, width));
      panels.add(new Panel("p-1", Panel.PanelRole.slidableBack, height, width));
      panels.add(new Panel("p-2", Panel.PanelRole.slidableRight, height, depth));
      panels.add(new Panel("p-3", Panel.PanelRole.slidableLeft, height, depth));
      panels.add(new Panel("p-4", lidType, width, depth));
      panels.add(new Panel("p-5", bottom, width, depth));
      panels.add(new Panel("p-6", Panel.PanelRole.bottomLeftRail, depth, 6));
      panels.add(new Panel("p-7", Panel.PanelRole.bottomRightRail, depth, 6));
      panels.add(new Panel("p-8", Panel.PanelRole.topLeftRail, depth, 6));
      panels.add(new Panel("p-9", Panel.PanelRole.topRightRail, depth, 6));
      panels.add(new Panel("p-10", Panel.PanelRole.backRail, depth, 6));
    }
  }
}
