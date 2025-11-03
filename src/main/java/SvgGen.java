/* Author: Austin Meredith
 * Date: 10.5.25
 * Description: This class will generate the svg. We will probably have a .svg that has all the boilerplate that we just manipulate
 *              the stuff we need to change.
 * */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SvgGen {
  static double x = 250;
  static double y = 350;
  
  // prefix and viewbox data
  static String prefix = "<?xml version='1.0' encoding='utf-8'?>\n";
  static String viewBox = "<svg height=\"" + y + "mm" + "\"" + " viewBox=\"0.0 0.0 " + x + " " + y + "\" width=\"" + x + "mm" + "\" \n";
  static String urls = """
  xmlns="http://www.w3.org/2000/svg" xmlns:cc="http://creativecommons.org/ns#" xmlns:dc="http://purl.org/dc/elements/1.1/" 
  xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
  xmlns:svg="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">

    """;  

  // function to generate file
  static public void generateFile (ArrayList<Panel> panels) {
    try { // clears the box.svg, later we'll make new files and name them
      FileWriter clear = new FileWriter("box.svg");
      clear.write("");
      clear.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    // appends data to the cleared file
    try (FileWriter writer = new FileWriter("box.svg", true)) {
      writer.write(prefix);
      writer.write(viewBox);
      writer.write(urls);
      //writer.write("<rect height=\"100%\" width=\"100%\" fill=\"rgba(255, 255, 255, 1)\"/>\n");
      // writes paths for panels
      for (Panel panel : panels) {
        writer.write("<g id=\"" + panel.id + "\" style=\"fill:none;stroke-linecap:round;stroke-linejoin:round;\">\n");
        writer.write("  <path d = \"" + panel.path);
        writer.write("</g>\n");
      }

      writer.write("</svg>");
      System.out.println("Successfully wrote to box.svg");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
}


