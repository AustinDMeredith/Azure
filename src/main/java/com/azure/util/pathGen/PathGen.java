package com.azure.util.pathGen;
/* Author: Austin Meredith
 * Date Changed: 10.7.25
 * Last Changed: 11.15.25
 * Description: Generate SVG path strings with finger-joint teeth for a rectangular panel.
 */

import com.azure.objects.Panel;
import com.azure.objects.BoxSpec;

public class PathGen {

  public static void generatePanelPath(Panel panel, double toothWidth, BoxSpec.BoxType type) {
    if (type == BoxSpec.BoxType.hinged) {
      hingedGen(panel, toothWidth);
    } else {
      nonHingedGen(panel, toothWidth);
    }
  }

  private static void hingedGen(Panel panel, double toothWidth) {
    
    // 1) Start point, tooth depth, height and width of panel
    double x0 = panel.startPoint.get(0);
    double y0 = panel.startPoint.get(1);
    x0 = (panel.role == Panel.PanelRole.leftBottom) ? x0 + 8 : x0;
    x0 = (panel.role == Panel.PanelRole.backBottom) ? x0 + 3.175 : x0;
    double toothDepth = 3.175;
    double w = panel.width;
    double h = panel.height;
    // Build path with a StringBuilder; use absolute M then relative l segments
    StringBuilder d = new StringBuilder(); // used for edges
    d.append(String.format("M%.3f %.3f ", x0, y0));

    // Set up edge sequence: top, right, bottom, left
    int i = 0;
    for (Panel.EdgeRole edgeRole : panel.edges) {
      // rotates direction for edge after each iteration
      int dx, dy, last, next;
      if (i == 0) {dx = 1; dy = 0; next = 1; last = 3;}
      else if (i == 1) {dx = 0; dy = 1; next = 2; last = 0;}
      else if (i == 2) {dx = -1; dy = 0; next = 3; last = 1;}
      else {dx = 0; dy = -1; next = 0; last = 2;}

      if (edgeRole == Panel.EdgeRole.male) {
        if (i % 2 == 0) { // Call male path gen with panel width for top and bottom edges
          d.append(MalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel)); 
        } else { // Call male path gen with panel width for right and left edges
          d.append(MalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));    
        }
      } else if (edgeRole == Panel.EdgeRole.female) {
        if (i % 2 == 0) { // Call female path gen with panel width for top and bottom edges
          d.append(FemalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        } else { // Call female path gen with panel width for right and left edges
          d.append(FemalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        }
      } else if (edgeRole == Panel.EdgeRole.maleHinge) {
        d.append(MalePathGen.hingedGen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
      } else if (edgeRole == Panel.EdgeRole.flat) {
        d.append(FlatPathGen.hingedGen(w, dx, dy, panel));
      } else if (edgeRole == Panel.EdgeRole.maleCutOut) { 
        d.append(MalePathGen.cutOutGen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
      } else if (edgeRole == Panel.EdgeRole.femaleBack) {
        d.append(FemalePathGen.backGen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
      } else if (edgeRole == Panel.EdgeRole.femaleConnector) {
        if (i % 2 == 0) { // Call female path gen with panel width for top and bottom edges
          d.append(FemalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        } else { // Call female path gen with panel width for right and left edges
          d.append(FemalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        }
      }

      i++;
    }
    
    // Close
    d.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");

    if (panel.role == Panel.PanelRole.rightBottom) {
      w -= 8.175;
      x0 += w; 
      d.append(HingeGen.genHinge(x0, y0));
      x0 += 1.825;
      y0 -= 3;
      d.append(HingeGen.genHole(x0, y0, toothDepth));
    }
    if (panel.role == Panel.PanelRole.leftBottom) {
      x0 -= 13;
      d.append(HingeGen.genHinge(x0, y0));
      x0 += 5;
      y0 -= 3;
      d.append(HingeGen.genHole(x0, y0, toothDepth));
    }

    // Assign back to the panel 
    panel.path = d.toString();
  }

  private static void nonHingedGen (Panel panel, double toothWidth) {
    // 1) Start point, tooth depth, height and width of panel
    double x0 = panel.startPoint.get(0);
    double y0 = panel.startPoint.get(1);
    double toothDepth = 3.175;
    double w = panel.width;
    double h = panel.height;
    // Build path with a StringBuilder; use absolute M then relative l segments
    StringBuilder d = new StringBuilder(); // used for edges
    d.append(String.format("M%.3f %.3f ", x0, y0));

    // Set up edge sequence: top, right, bottom, left
    int i = 0;
    for (Panel.EdgeRole edgeRole : panel.edges) {
      // rotates direction for edge after each iteration
      int dx, dy, last, next;
      if (i == 0) {dx = 1; dy = 0; next = 1; last = 3;}
      else if (i == 1) {dx = 0; dy = 1; next = 2; last = 0;}
      else if (i == 2) {dx = -1; dy = 0; next = 3; last = 1;}
      else {dx = 0; dy = -1; next = 0; last = 2;}

      if (edgeRole == Panel.EdgeRole.male) {
        if (i % 2 == 0) { // Call male path gen with panel width for top and bottom edges
          d.append(MalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel)); 
        } else { // Call male path gen with panel width for right and left edges
          d.append(MalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));    
        }
      } else if (edgeRole == Panel.EdgeRole.female || edgeRole == Panel.EdgeRole.slidableSide || edgeRole == Panel.EdgeRole.slidableBack) {
        if (i % 2 == 0) { // Call female path gen with panel width for top and bottom edges
          d.append(FemalePathGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        } else { // Call female path gen with panel width for right and left edges
          d.append(FemalePathGen.gen(h, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
        }
      } else if (edgeRole == Panel.EdgeRole.bottom) {
        if (i % 2 == 0) { // Call bottom path gen with panel width for top and bottom edges
          d.append(BottomPathGen.edgeGen(w, toothDepth, dx, dy, panel));
        } else { // Call bottom path gen with panel width for right and left edges
          d.append(BottomPathGen.edgeGen(h, toothDepth, dx, dy, panel));
        }
      } else if (edgeRole == Panel.EdgeRole.flat) {
        if (i % 2 == 0) { // Call bottom path gen with panel width for top and bottom edges
          d.append(FlatPathGen.railGen(w, dx, dy, panel, panel.edges.get(last), panel.edges.get(next)));
        } else { // Call bottom path gen with panel width for right and left edges
          d.append(FlatPathGen.railGen(h, dx, dy, panel, panel.edges.get(last), panel.edges.get(next)));
        } 
      } else if (edgeRole == Panel.EdgeRole.flatLid || edgeRole == Panel.EdgeRole.slidableFront) { 
        if (i % 2 == 0) { // Call bottom path gen with panel width for top and bottom edges
          d.append(FlatPathGen.gen(w, dx, dy, panel));
        } else { // Call bottom path gen with panel width for right and left edges
          d.append(FlatPathGen.gen(h, dx, dy, panel));
        }
      } 

      i++;
    }
    
    // Close
    d.append("Z\" stroke=\"rgb(0,0,0)\" stroke-width=\"0.1\"/>\n");

    if (panel.role == Panel.PanelRole.basedBottom) {
      d.append(BottomPathGen.holeGen(toothWidth, toothDepth, panel, true));
    }

    if (panel.role == Panel.PanelRole.slidableLeft || panel.role == Panel.PanelRole.slidableRight) {
      int dx = 1; int dy = 0; int next = 1; int last = 3;
      d.append(RailHoleGen.gen(w, toothWidth, toothDepth, dx, dy, panel.edges.get(last), panel.edges.get(next), panel));
    }

    // Assign back to the panel 
    panel.path = d.toString();

  }
}
