package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.3.25
 * Last Change: 11.3.25
 * Descrition: This class handels setting and returning kerf values so that teeth fit tightly.
 * */

import java.util.ArrayList;

public class KerfService {
  static double kerf = .7;
  public static ArrayList<Double> getKerf (double n) {
    ArrayList<Double> settings = new ArrayList<>();
    double toothKerf = kerf / 2;
    double cornerKerf = kerf / 4;    
    settings.add(toothKerf);
    settings.add(cornerKerf);

    return settings;
  }

  public static void setKerf (double newKerf) {
    kerf = newKerf;
  }
}
