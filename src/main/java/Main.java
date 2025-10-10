public class Main {
  public static void main (String args[]) {
    BoxSpec box = new BoxSpec(100, 100, 100, 2, 10);
    double toothDepth = 3.175;       // mm, tuned to material thickness/fit

    Panel panel1 = new Panel("p-0", Panel.PanelRole.front, 100, 100);
    box.panels.add(panel1);
    SetEdgeRoles.setRoles(box.panels);

    
    PathGen.generatePanelPath(panel1, box.width, box.height, box.teethWidth);
    SvgGen.generateFile();
  }  
}
