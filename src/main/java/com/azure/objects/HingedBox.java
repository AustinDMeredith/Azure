package com.azure.objects;

import java.util.ArrayList;

import com.azure.util.services.SetEdgeRoles;
import com.azure.util.services.LayoutService;
import com.azure.util.services.AddPanels;
import com.azure.util.services.IngravingService;
import com.azure.util.services.SvgGen;
import com.azure.util.pathGen.PathGen;

public class HingedBox extends BoxSpec {
  public HingedBox (double height, double width, double depth, double teethWidth, double radius, String engraving, ArrayList<Double> tolerances) {
    this.boxType = BoxSpec.BoxType.hinged;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.radius = radius;
    this.lidType = Panel.PanelRole.top;
    this.teethWidth = teethWidth;
    this.engraving = engraving;

    AddPanels.setHinged(height, width, depth, radius, this.panels);
    
    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
    
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth);
    }
    IngravingService.addEngravings(engraving, panels);

    this.svg = SvgGen.generateFile(this.panels);
  }
}
