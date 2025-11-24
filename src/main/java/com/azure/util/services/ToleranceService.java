package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.12.25
 * Last Changed: 11.12.25
 * Description: This class will return a tolerance either in a global sense or a per-panel (ArrayList) sense
 * */

import com.azure.objects.Panel;
import java.util.ArrayList;


public class ToleranceService {
  // Class atributes
  private static double high = .05;
  private static double medium = .03;
  private static double low = .01;
  private static double globalCurrent = low;

  // we need a function the returns a new array list with the nessicary amount of panels on request

  public static void setGlobalCurrent (String tolerance) {
    if (tolerance == "low") {
      globalCurrent = low;
    } else if (tolerance == "medium") {
      globalCurrent = medium;
    } else {
      globalCurrent = high;
    }
  }

  public static void setPerPanelTolerance (ArrayList<Panel> panels, ArrayList<Double> tolerances) {
    for (Panel panel : panels) {
      

    }
  }

  public static double getGlobalCurrent () { return globalCurrent; }
}
