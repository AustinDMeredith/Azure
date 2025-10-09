public class Main {
  public static void main (String args[]) {
    BoxSpec.height = 100;
    BoxSpec.width = 50;
    BoxSpec.depth = 40;
    BoxSpec.tolerance = 0;
    BoxSpec.teethPerEdge = 4;
    double toothDepth = 3.175;       

    Panel panel1 = new Panel("p-0", Panel.PanelRole.side);
    BoxSpec.panels.add(panel1);
    SetEdgeRoles.setRoles(BoxSpec.panels);

    PathGen.generatePanelPath(panel1, BoxSpec.width, BoxSpec.height, BoxSpec.teethPerEdge, toothDepth);

    SvgGen.generateFile();
  }  
}
