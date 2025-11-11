package com.azure;
/* Author: Austin Meredith
 * Date Created: 10.3.25
 * Last Changed: 11.3.25
 * Description: This class is incharge of calling constuctors for boxes and calling the function to generate svg files
 */

import java.util.Scanner;
import java.util.ArrayList;
import com.azure.objects.BasedBox;
import com.azure.objects.BoxSpec;
import com.azure.objects.SimpleBox;
import com.azure.util.services.SvgGen;
import com.azure.util.services.ValidationService;

public class Main {
  public static void main (String args[]) {
    Scanner in = new Scanner(System.in);
    boolean running = true;
    boolean valid = false;

    // Prompt for generating boxes or quiting
    clearConsole();
    System.out.print("Welcome To Azure Box Creations (Enter Number)\n 1. Generate A Box\n 2. Quit\n ABC>");
    
    while(running) {
      // Reprompt until valid input is entered
      valid = false;
      int first = -1;
      while (!valid) {
        String firstSTR = in.nextLine();
        first = ValidationService.validateChoice(firstSTR);
        if (first != -1) valid = true;
      }

      if (first == 1) {
        // Prompt for type of box
        clearConsole();
        System.out.print("What Type of Box Would you Like? (Enter Number)\n 1. BasedBox\n 2. SimpleBox\n ABC>");

        // Reprompt until a valid input is entered
        valid = false;
        int type = -1;
        while (!valid) {
          String typeSTR = in.nextLine();
          type = ValidationService.validateChoice(typeSTR);
          if (type != -1) valid = true;
        }

        // Prompt user for specifications
        clearConsole();
        System.out.print("Please Enter Your specifications In a Comma Seperated List (units = mm) (<Height>, <Width>, <Depth>, <ToothWidth>)\n ABC>");

        // Repormpt until valid input is entered
        valid = false;
        double h = -1, w = -1, d = -1, tW = -1;
        while (!valid) {
          String specsSTR = in.nextLine();
          clearConsole();
          ArrayList<Double> specs = ValidationService.validateSpecs(specsSTR);
          if (specs.get(0) != -1.0) {
            h = specs.get(0);
            w = specs.get(1);
            d = specs.get(2);
            tW = specs.get(3);
            valid = true;
          }
        }

        // Pprompt user for engraving
        System.out.print("Would You Like An Engraving?\n 1. Yes\n 2. No\n ABC>");

        // Reprompt until valid input is entered
        valid = false;
        int doEngrave = -1;
        while (!valid) {
          String doEngraveSTR = in.nextLine();
          doEngrave = ValidationService.validateChoice(doEngraveSTR);
          if (doEngrave != -1) valid = true;
        }

        String engraving = "false";

        clearConsole();
        if (doEngrave == 1) {
          System.out.print("\nWhat Whould You Like To Engrave? (Must Be Two Characters)\n ABC>");
          valid = false;
          while (!valid) {
            engraving = in.nextLine();
            clearConsole();
            engraving = ValidationService.validateEngraving(engraving);
            if (engraving != "false") valid = true; 
          }
        } else {  
          engraving = "";
        }
        clearConsole();
        if (type == 1) {
          BoxSpec box = new BasedBox(h, w, d, 0, tW, engraving);
          SvgGen.generateFile(box.panels);
          System.out.print("Successfully Generated SVG!\n");
        } else {
          BoxSpec box = new SimpleBox(h, w, d, 0, tW, engraving);
          SvgGen.generateFile(box.panels);
          System.out.print("Successfully Generated SVG!\n");
        }
      } else {
        clearConsole();
        running = false;
        break;
      }

      // Prompt for generating boxes or quiting
      System.out.print("Azure Box Creations (Enter Number)\n 1. Generate A Box\n 2. Quit\n ABC>");
    }
    
    // close scanner 
    in.close();
  }  

  public static void clearConsole() {
      try {
          if (System.getProperty("os.name").contains("Windows")) {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
              System.out.print("\033[H\033[2J");
              System.out.flush();
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
