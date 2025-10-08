public class Main {
  public static void main (String args[]) {
    BoxSpec.height = 200;
    BoxSpec.width = 200;
    BoxSpec.depth = 200;
    BoxSpec.tolerance = 2;
    BoxSpec.teethPerEdge = 15;
    double toothDepth = 3.175;       

    Panel panel1 = new Panel("p-0", Panel.PanelRole.side);
    BoxSpec.panels.add(panel1);
    SetEdgeRoles.setRoles(BoxSpec.panels);

    PathGen.generatePanelPath(panel1, BoxSpec.width, BoxSpec.height, BoxSpec.teethPerEdge, toothDepth);

    SvgGen.generateFile();
  }  
}
