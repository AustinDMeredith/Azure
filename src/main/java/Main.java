public class Main {
  public static void main (String args[]) {
    BoxSpec boxSpec = new BoxSpec(100, 100, 100, 1, 8);
    SetEdgeRoles roleSetter = new SetEdgeRoles();
    Panel panel1 = new Panel("p-0", Panel.PanelRole.side);

    boxSpec.panels.add(panel1);
    roleSetter.setRoles(boxSpec.panels);
  }  
}
