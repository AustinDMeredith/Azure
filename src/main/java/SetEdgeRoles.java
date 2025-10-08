/* Author: Austin Meredith
 * Date: 10.5.25
 * Description: This class will have a function to set the roles of each edge with some sort of methodalegy, I have no idea what 
 *              that will look like right now tho.
 * */
import java.util.ArrayList;

public class SetEdgeRoles {
  public static void setRoles (ArrayList<Panel> panels) {
    for (int i = 0; i < panels.size(); i++) {
      Panel panel = panels.get(i);
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          panel.edges.add(Panel.EdgeRole.male);  
        } else {
          panel.edges.add(Panel.EdgeRole.female);
        }
      }
    }
  }
}
