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

    // verifys the user input and throws an exception if any input is invalid (exception gets caught in the front end and displays which text box the error came from)
    ValidationService.verifyInput(height, width, depth, teethWidth, engraving, engravingSize, this.boxType, lidType);
    
    // constructs all the nessicary panels and stores it in the object
    AddPanels.Set(height, width, depth, lidType, boxType, this.panels);

    // adds the tolerance the user input to the outside panals (excludes sliding lid rails, inner lifting lid, and handle)
    for (int i = 0; i < 6; i++) {
      this.panels.get(i).tolerance = tols.get(i);
    }

    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
  
    // generates the paths for all panels
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth, this.boxType);
    }
 
    // adds engraving to the side, front and back panels
    IngravingService.addEngravings(engraving, engravingSize, panels, boxType);

    // generates the svg and stores it in the object
    this.svg = SvgGen.generateFile(this.panels);
  }
}
