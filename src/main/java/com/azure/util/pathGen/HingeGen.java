package com.azure.util.pathGen;

public class HingeGen {
  public static String gen(double radius, double cx, double cy) {
    double stroke = .1;
    cx += 5;
    cy += 5;
    return String.format("<circle cx=\"%f\" cy=\"%f\" r=\"%f\" stroke=\"%s\" />",
        cx, cy, radius, stroke);
  }
}
