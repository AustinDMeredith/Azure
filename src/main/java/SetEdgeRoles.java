/* Author: Austin Meredith
 * Date Created: 10.5.25
 * Last Changed: 11.3.25
 * Description: This class will have a function to set the roles of each edge with some sort of methodalegy, I have no idea what 
 *              that will look like right now tho.
 * */
import java.util.ArrayList;

public class SetEdgeRoles {
  public static void setRoles (ArrayList<Panel> panels) {
    for (Panel panel : panels) {
      if (panel.role == Panel.PanelRole.front) { // Sets all edge roles to male for front panels
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.male);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.male);
      } else if (panel.role == Panel.PanelRole.bottom) { // Sets all edge roles to female for bottom panels
        panel.edges.add(0, Panel.EdgeRole.female);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.female);
        panel.edges.add(3, Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.right) { // Alternates edge roles male and female for right panels
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.left) { // Alternates edge roles male and female for right panels
        panel.edges.add(0, Panel.EdgeRole.male);
        panel.edges.add(1, Panel.EdgeRole.female);
        panel.edges.add(2, Panel.EdgeRole.male);
        panel.edges.add(3, Panel.EdgeRole.female);
      } else if (panel.role == Panel.PanelRole.basedBottom) { // Sets all edge roles to bottom for Based Bottom panels
        panel.edges.add(0, Panel.EdgeRole.bottom);
        panel.edges.add(1, Panel.EdgeRole.bottom);
        panel.edges.add(2, Panel.EdgeRole.bottom);
        panel.edges.add(3, Panel.EdgeRole.bottom);
      }
    }
  }
}
