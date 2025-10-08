public class Main {
  public static void main (String args[]) {
    BoxSpec.height = 100;
    BoxSpec.width = 100;
    BoxSpec.depth = 100;
    BoxSpec.tolerance = 2;
    BoxSpec.teethPerEdge = 8;
    double toothDepth = 3.175;       

    Panel panel1 = new Panel("p-0", Panel.PanelRole.side);
    BoxSpec.panels.add(panel1);
    SetEdgeRoles.setRoles(BoxSpec.panels);

    PathGen.generatePanelPath(panel1, BoxSpec.width, BoxSpec.height, BoxSpec.teethPerEdge, toothDepth);

    System.out.print(panel1.path);
  }  
}
