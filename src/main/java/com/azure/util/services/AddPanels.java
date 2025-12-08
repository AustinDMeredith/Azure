package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.13.25
 * Last Changed: 11.15.25
 * Description: Adds the required panels to the box from which it was called 
 * */

import java.util.ArrayList;

import com.azure.objects.BoxSpec;
import com.azure.objects.Panel;

public class AddPanels {
  // constructs and adds all the nessicary panels to the box from which the function was called (based and simple boxes only)
  public static void Set (double height, double width, double depth, Panel.PanelRole lidType, BoxSpec.BoxType boxType, ArrayList<Panel> panels) {
    // Check if lid is sliding or not
    boolean isSlidingLid = (lidType == Panel.PanelRole.slidingLid); 
    // check if the lid is lifting or not
    boolean isLiftingLid = (lidType == Panel.PanelRole.liftingLid);
    // Check if box is based or not
    boolean isBased = (boxType == BoxSpec.BoxType.based);
    // Check if box is hinged or not
    boolean isHinged = (boxType == BoxSpec.BoxType.hinged);
    // If box is based, bottom equals "basedBottom", if not bottom equals "bottom"
    Panel.PanelRole bottom = isBased ? Panel.PanelRole.basedBottom : Panel.PanelRole.bottom;

    if (!isSlidingLid && !isHinged && !isLiftingLid) { // if the lid is normal (top)
      panels.add(new Panel("p-0", Panel.PanelRole.front, height, width));
      panels.add(new Panel("p-1", Panel.PanelRole.back, height, width));
      panels.add(new Panel("p-2", Panel.PanelRole.right, height, depth));
      panels.add(new Panel("p-3", Panel.PanelRole.left, height, depth));
      panels.add(new Panel("p-4", lidType, depth, width));
      panels.add(new Panel("p-5", bottom, depth, width));
    } else if (isSlidingLid) { // if the lid is sliding
      panels.add(new Panel("p-0", Panel.PanelRole.slidableFront, height, width));
      panels.add(new Panel("p-1", Panel.PanelRole.slidableBack, height, width));
      panels.add(new Panel("p-2", Panel.PanelRole.slidableRight, height, depth));
      panels.add(new Panel("p-3", Panel.PanelRole.slidableLeft, height, depth));
      panels.add(new Panel("p-4", lidType, depth - 3.175, width - 3.175 * 2));
      panels.add(new Panel("p-5", bottom, depth, width));
      panels.add(new Panel("p-6", Panel.PanelRole.bottomLeftRail, 8, depth));
      panels.add(new Panel("p-7", Panel.PanelRole.bottomRightRail, 8, depth));
      panels.add(new Panel("p-8", Panel.PanelRole.topLeftRail, 8, depth));
      panels.add(new Panel("p-9", Panel.PanelRole.topRightRail, 8, depth));
      panels.add(new Panel("p-10", Panel.PanelRole.backRail, 8, width));
    } else { // if the lid is lifting
      panels.add(new Panel("p-0", Panel.PanelRole.front, height, width));
      panels.add(new Panel("p-1", Panel.PanelRole.back, height, width));
      panels.add(new Panel("p-2", Panel.PanelRole.right, height, depth));
      panels.add(new Panel("p-3", Panel.PanelRole.left, height, depth));
      panels.add(new Panel("p-4", lidType, depth, width));
      panels.add(new Panel("p-5", bottom, depth, width));
      panels.add(new Panel("p-6", Panel.PanelRole.innerLid, depth - 3.175, width - 3.175));
      panels.add(new Panel("p-7", Panel.PanelRole.handle, 20, width * .20));
    } 
  }

  // constructs and adds all the nessicary panels to the box from which the function was called (hinged boxes only)
  public static void setHinged (double height, double width, double depth, ArrayList<Panel> panels){
      panels.add(new Panel("p-0", Panel.PanelRole.frontTop, height * .40, width));
      panels.add(new Panel("p-1", Panel.PanelRole.frontBottom, height * .60, width));
      panels.add(new Panel("p-2", Panel.PanelRole.backTop, height * .40, width));
      panels.add(new Panel("p-3", Panel.PanelRole.backBottom, height * .60, width));
      panels.add(new Panel("p-4", Panel.PanelRole.rightTop, height * .40, depth));
      panels.add(new Panel("p-5", Panel.PanelRole.rightBottom, height * .60, depth));
      panels.add(new Panel("p-6", Panel.PanelRole.leftTop, height * .40, depth));
      panels.add(new Panel("p-7", Panel.PanelRole.leftBottom, height * .60, depth));
      panels.add(new Panel("p-8", Panel.PanelRole.top, depth, width));
      panels.add(new Panel("p-9", Panel.PanelRole.bottom, depth, width));
  }
}
