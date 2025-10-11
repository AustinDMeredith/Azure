/* Author: Austin Meredith
 * Date: 10.5.25
 * Description: This class will have a function to set the roles of each edge with some sort of methodalegy, I have no idea what 
 *              that will look like right now tho.
 * */
import java.util.ArrayList;

public class SetEdgeRoles {
  public static void setRoles (ArrayList<Panel> panels) {
    for (Panel panel : panels) {
      if (panel.role == Panel.PanelRole.front) {
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.male);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.bottom) {
        panel.edges.add(0, Panel.EdgeRole.female);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.female);
        panel.edges.add(3, Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.right) {
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.left) {
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.female);
      }
    }
  }
}
