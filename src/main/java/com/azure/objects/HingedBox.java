package com.azure.objects;


import com.azure.util.services.SetEdgeRoles;
import com.azure.util.services.LayoutService;
import com.azure.util.services.AddPanels;
import com.azure.util.services.IngravingService;
import com.azure.util.services.SvgGen;
import com.azure.util.pathGen.PathGen;
import com.azure.util.services.ValidationService;

public class HingedBox extends BoxSpec {
  public HingedBox (double height, double width, double depth, double teethWidth, String engraving, double engravingSize, double tolerance) {
    this.boxType = BoxSpec.BoxType.hinged;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.lidType = Panel.PanelRole.top;
    this.teethWidth = teethWidth;
    this.engraving = engraving;
    
    // verifys the user input and throws an exception if any input is invalid (exception gets caught in the front end and displays which text box the error came from)
    ValidationService.verifyInput(height, width, depth, teethWidth, engraving, engravingSize, this.boxType, lidType);

    // constructs all the nessicary panels and stores it in the object
    AddPanels.setHinged(height, width, depth, this.panels);

    // sets the tolerance for all the panels
    for (Panel panel : this.panels) {
      panel.tolerance = tolerance;
    }
    
    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
    
    // generates the paths for all panels
    for (int i = 0; i < 10; i++) {
      Panel panel = this.panels.get(i);
      PathGen.generatePanelPath(panel, this.teethWidth, this.boxType);
    }

    // adds engraving to the side, front and back panels
    IngravingService.addEngravings(engraving, engravingSize, panels, boxType);

    // generates the svg and stores it in the object
    this.svg = SvgGen.generateFile(this.panels);
  }
}
