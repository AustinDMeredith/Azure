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

public class SimpleBox extends BoxSpec {
  
  public SimpleBox (double height, double width, double depth, Panel.PanelRole lidType, double teethWidth, String engraving) {
    this.boxType = BoxSpec.BoxType.simple;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.lidType = lidType;
    this.teethWidth = teethWidth;
    this.engraving = engraving;

    // add all the panels to the array
    AddPanels.Set(height, width, depth, lidType, boxType, this.panels);

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
