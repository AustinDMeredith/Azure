package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.12.25
 * Last Changed: 11.12.25
 * Description: This class will return a tolerance either in a global sense or a per-panel (ArrayList) sense
 * */

public class ToleranceService {
  // Class atributes
  private static double high = .05;
  private static double medium = .03;
  private static double low = .01;


  public static double getTolerance (int index) {
    if (index == 2) return low;
    else if (index == 1) return medium;
    return high;
  }
}
