package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.3.25
 * Last Change: 11.3.25
 * Descrition: This class handels setting and returning kerf values so that teeth fit tightly.
 * */

import java.util.ArrayList;

public class KerfService {
  // presets for the kerf values (accounting for the amount of wood the lazer burns off) 
  static double smallKerf = .3;
  static double mediumKerf = .5;
  static double largeKerf = .7;
  static double currentKerf = mediumKerf;

  // get the corner and tooth kerf values
  public static ArrayList<Double> getKerf () {
    ArrayList<Double> settings = new ArrayList<Double>();
    double toothKerf = currentKerf / 2;
    double cornerKerf = currentKerf / 4;    
    settings.add(toothKerf);
    settings.add(cornerKerf);

    return settings;
  }

  // sets the current kerf to a different preset that the user picks (combo boxes use index to determine the selected preset)
  public static void setKerf (int index) {
    if (index == 0) currentKerf = largeKerf;
    else if (index == 1) currentKerf = mediumKerf;
    else currentKerf = smallKerf;
  }
}
