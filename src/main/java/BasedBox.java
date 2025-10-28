public class BasedBox extends BoxSpec{
    public BasedBox (double height, double width, double depth, double tolerance, double teethWidth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.tolerance = tolerance;
    this.teethWidth = teethWidth;

    // add all the panels to the array
    this.panels.add(new Panel("p-0", Panel.PanelRole.front, this.height, this.width));
    this.panels.add(new Panel("p-1", Panel.PanelRole.front, this.height, this.width));
    this.panels.add(new Panel("p-2", Panel.PanelRole.right, this.height, this.depth));
    this.panels.add(new Panel("p-3", Panel.PanelRole.left, this.height, this.depth));
    this.panels.add(new Panel("p-4", Panel.PanelRole.bottom, this.width, this.depth));
    this.panels.add(new Panel("p-5", Panel.PanelRole.basedBottom, this.width, this.depth));

    // set roles and find startpoints for panels
    SetEdgeRoles.setRoles(this.panels);
    LayoutService.findStartPoint(this.panels);
  
    for (Panel panel : this.panels) {
      PathGen.generatePanelPath(panel, this.teethWidth);
    }
  }
}
