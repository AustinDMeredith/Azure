/* Author: Austin Meredith
 * Date Created: 10.27.25
 * Last Changed: 11.3.25
 * Description: Inherates box spec to generate a simple box 
 * */

public class SimpleBox extends BoxSpec {
  
  public SimpleBox (double height, double width, double depth, double tolerance, double teethWidth, String engraving) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.tolerance = tolerance;
    this.teethWidth = teethWidth;
    this.engraving = engraving;

    // add all the panels to the array
    this.panels.add(new Panel("p-0", Panel.PanelRole.front, this.height, this.width));
    this.panels.add(new Panel("p-1", Panel.PanelRole.front, this.height, this.width));
    this.panels.add(new Panel("p-2", Panel.PanelRole.right, this.height, this.depth));
    this.panels.add(new Panel("p-3", Panel.PanelRole.left, this.height, this.depth));
    this.panels.add(new Panel("p-4", Panel.PanelRole.bottom, this.width, this.depth));
    this.panels.add(new Panel("p-5", Panel.PanelRole.bottom, this.width, this.depth));

    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
  
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth);
    }
    IngravingService.addEngravings(engraving, panels);
  }
}
