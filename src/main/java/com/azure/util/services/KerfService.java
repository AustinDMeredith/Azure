package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.3.25
 * Last Change: 11.3.25
 * Descrition: This class handels setting and returning kerf values so that teeth fit tightly.
 * */

import java.util.ArrayList;

public class KerfService {
  static double smallKerf = .3;
  static double mediumKerf = .5;
  static double largeKerf = .7;
  static double currentKerf = mediumKerf;

  public static ArrayList<Double> getKerf () {
    ArrayList<Double> settings = new ArrayList<Double>();
    double toothKerf = currentKerf / 2;
    double cornerKerf = currentKerf / 4;    
    settings.add(toothKerf);
    settings.add(cornerKerf);

    return settings;
  }

  public static void setKerf (int index) {
    if (index == 0) currentKerf = largeKerf;
    else if (index == 1) currentKerf = mediumKerf;
    else currentKerf = smallKerf;
  }
}
