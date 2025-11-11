package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 11.9.25
 * Last Changed: 11.9.25
 * Description: This class validates user input and repromts for CLI version or clamps value for GUI version
 */

import java.util.ArrayList;

public class ValidationService {

  /************* Validation for cli interface *************/
  
  // returns true if the entered specifications are valid
  public static ArrayList<Double> validateSpecs (String specsSTR) {
    String[] parts = specsSTR.split(", ");
    boolean changed = false;
    ArrayList<Double> specs = new ArrayList<Double>();

    try {
      double h = Integer.parseInt(parts[0]);
      double w = Integer.parseInt(parts[1]);
      double d = Integer.parseInt(parts[2]);
      double tW = Integer.parseInt(parts[3]);

      if (h < 20.0) h = 20; changed = true;
      if (h > 200.0) h = 200; changed = true;
      if (w < 20.0) w = 20; changed = true;
      if (w > 200.0) w = 200; changed = true;
      if (d < 20.0) d = 20; changed = true;
      if (d > 200.0) d = 200; changed = true;
      if (tW < 4.0) tW = 4; changed = true;
      if (tW > 10.0) tW = 10; changed = true;

      specs.add(h);
      specs.add(w);
      specs.add(d);
      specs.add(tW);

      if (changed) System.out.print("Entered values were out of bounds, new values --> Height: " + h + ", Width: " + w + ", Depth: " + d + ", Tooth Width: " + tW + "\n");
      changed = false;

    } catch (NumberFormatException e) {
      System.out.print("Please enter a comma seperated list of numbers, e.g. 100, 100, 100, 8\n ABC>");
      specs.add(-1.0);
    }
    
    return specs;
  }

  public static int validateChoice (String inputSTR) {
    int choice;
    try {
      choice = Integer.parseInt(inputSTR);

      if (choice != 1 && choice != 2) choice = -1; System.out.print("Please enter a number listed on the screen \n ABC>");

    } catch (NumberFormatException e) {
      System.out.print("Please enter a number listed on the screen \n ABC>");
      choice = -1;
    }

    return choice;
  }

  public static String validateEngraving (String engraving) {
    int length = engraving.length();
    if (length > 2) {
      engraving = "false";
      System.out.print("Please enter a two letter engraving e.g. AZ\n");
    }

    return engraving;
  }
}
