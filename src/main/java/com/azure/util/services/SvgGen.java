package com.azure.util.services;
/* Author: Austin Meredith
 * Date Created: 10.5.25
 * Last Changed: 11.15.25
 * Description: This class will generate the svg. We will probably have a .svg that has all the boilerplate that we just manipulate
 *              the stuff we need to change.
 * */
import java.util.ArrayList;

import com.azure.objects.Panel;

public class SvgGen {
  static ArrayList<Double> viewBoxSize = LayoutService.getVeiwBoxSize();

  static double x = 1000;
  static double y = 1000;

  // prefix and viewbox data
  static String prefix = "<?xml version='1.0' encoding='utf-8'?>\n";
  static String viewBox = "<svg height=\"" + y + "mm" + "\"" + " viewBox=\"0.0 0.0 " + x + " " + y + "\" width=\"" + x + "mm" + "\" \n";
  static String urls = """
  xmlns="http://www.w3.org/2000/svg" xmlns:cc="http://creativecommons.org/ns#" xmlns:dc="http://purl.org/dc/elements/1.1/" 
  xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
  xmlns:svg="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">

    """;  

  // function to generate file
  static public String generateFile (ArrayList<Panel> panels) {
    
      StringBuilder sb = new StringBuilder();

      // append the boilerplate data
      sb.append(prefix);
      sb.append(viewBox);
      sb.append(urls);
      //writer.write("<rect height=\"100%\" width=\"100%\" fill=\"rgba(255, 255, 255, 1)\"/>\n");
      // writes paths for panels
      for (Panel panel : panels) {
        sb.append("<g id=\"" + panel.id + "\" style=\"fill:none;stroke-linecap:round;stroke-linejoin:round;\">\n");
        sb.append("  <path d = \"" + panel.path);
        sb.append("</g>\n");
      }

      // append the close tag
      sb.append("</svg>");

      return sb.toString();
  }
}
