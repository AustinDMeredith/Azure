package com.azure.objects;

import java.util.ArrayList;

import com.azure.util.services.AddPanels;
public class HingedBox extends BoxSpec {
  public HingedBox (double height, double width, double depth, double teethWidth, String engraving, ArrayList<Double> tolerances) {
    this.boxType = BoxSpec.BoxType.hinged;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.lidType = Panel.PanelRole.top;
    this.teethWidth = teethWidth;
    this.engraving = engraving;


    AddPanels.Set(height, width, depth, lidType, boxType, this.panels);
  }
}
