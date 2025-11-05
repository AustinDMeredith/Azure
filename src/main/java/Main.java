/* Author: Austin Meredith
 * Date Created: 10.3.25
 * Last Changed: 11.3.25
 * Description: This class is incharge of calling constuctors for boxes and calling the function to generate svg files
 */

import java.util.Scanner;

public class Main {
  public static void main (String args[]) {
    Scanner in = new Scanner(System.in);
    boolean running = true;

    while(running) {
      // Prompt for generating boxes or quiting
      clearConsole();
      System.out.print("Welcome To Azure Box Creations (Enter Number)\n 1. Generate A Box\n 2. Quit\n ABC>");
      String firstSTR = in.nextLine();
      int first = Integer.parseInt(firstSTR);

      if (first == 1) {
        // Grab the type of box the user wants 
        clearConsole();
        System.out.print("What Type of Box Would you Like? (Enter Number)\n 1. BasedBox\n 2. SimpleBox\n ABC>");
        String typeSTR = in.nextLine();
        int type = Integer.parseInt(typeSTR);

        // Grab the specs for the box the user wants
        clearConsole();
        System.out.print("Please Enter Your specifications In a Comma Seperated List (<Height>, <Width>, <Depth>, <ToothWidth>)\n ABC>");
        String specsSTR = in.nextLine();

        String[] parts = specsSTR.split(", "); 
        double h = Integer.parseInt(parts[0]);
        double w = Integer.parseInt(parts[1]);
        double d = Integer.parseInt(parts[2]);
        double tW = Integer.parseInt(parts[3]);

        System.out.print("\nWould You Like An Engraving?\n 1. Yes\n 2.No\n ABC>");
        String doEngraveSTR = in.nextLine();
        int doEngrave = Integer.parseInt(doEngraveSTR);
        String engraving;

        if (doEngrave == 1) {
          System.out.print("\nWhat Whould You Like To Engrave? (Must Be Two Characters)\n ABC>");
          engraving = in.nextLine();
        } else {
          engraving = "";
        }
        if (type == 1) {
          BoxSpec box = new BasedBox(h, w, d, 0, tW, engraving);
          SvgGen.generateFile(box.panels);
          System.out.print("Successfully Generated SVG!");
        } else {
          BoxSpec box = new SimpleBox(h, w, d, 0, tW, engraving);
          SvgGen.generateFile(box.panels);
          System.out.print("Successfully Generated SVG!");
        }
      } else {
        clearConsole();
        running = false;
        break;
      }
    }


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