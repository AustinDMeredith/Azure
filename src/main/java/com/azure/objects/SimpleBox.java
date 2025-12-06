package com.azure.objects;
/* Author: Austin Meredith
 * Date Created: 10.27.25
 * Last Changed: 11.13.25
 * Description: Inherates box spec to generate a simple box 
 * */

import com.azure.util.pathGen.PathGen;
import com.azure.util.services.IngravingService;
import com.azure.util.services.LayoutService;
import com.azure.util.services.SetEdgeRoles;
import com.azure.util.services.SvgGen;
import com.azure.util.services.AddPanels;
import com.azure.util.services.ValidationService;

import java.util.ArrayList;

public class SimpleBox extends BoxSpec {
  
  public SimpleBox (double height, double width, double depth, Panel.PanelRole lidType, double teethWidth, String engraving, double engravingSize, ArrayList<Double> tols) {
    this.boxType = BoxSpec.BoxType.simple;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.lidType = lidType;
    this.teethWidth = teethWidth;
    this.engraving = engraving;

    ValidationService.verifyInput(height, width, depth, teethWidth, engraving, engravingSize, this.boxType, lidType);
    // add all the panels to the array
    AddPanels.Set(height, width, depth, lidType, boxType, this.panels);

    for (int i = 0; i < 6; i++) {
      this.panels.get(i).tolerance = tols.get(i);
    }

    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
  
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth, this.boxType);
    }
    IngravingService.addEngravings(engraving, engravingSize, panels, boxType);

    this.svg = SvgGen.generateFile(this.panels);
  }
}
