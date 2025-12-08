package com.azure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import com.azure.objects.*;

public class SimpleBasedBoxTest {

  /******* This test verifys that the generated edge length equals the entered length for said edge *******/
  @Test 
  public void verifyLengths() {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box2 = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);
    BoxSpec box3 = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box4 = new BasedBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);

    // Run through each edge and test if it equals the required length
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          assertEquals(box1.panels.get(i).width, box1.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2.panels.get(i).width, box2.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box3.panels.get(i).width, box3.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box4.panels.get(i).width, box4.panels.get(i).finalEdgeLengths.get(j), 0);
        } else {
          assertEquals(box1.panels.get(i).height, box1.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2.panels.get(i).height, box2.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box3.panels.get(i).height, box3.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box4.panels.get(i).height, box4.panels.get(i).finalEdgeLengths.get(j), 0);
        }
      }
    }
  }
  
  /******* This test verifys that all normal lid based and simple boxes get assigned the correct panel roles *******/
  @Test
  public void verifyPanelRoles () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);

    BoxSpec BasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec SimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);

    // test based box panel roles
    assertEquals(BasedBox.panels.get(0).role, Panel.PanelRole.front);
    assertEquals(BasedBox.panels.get(1).role, Panel.PanelRole.back);
    assertEquals(BasedBox.panels.get(2).role, Panel.PanelRole.right);
    assertEquals(BasedBox.panels.get(3).role, Panel.PanelRole.left);
    assertEquals(BasedBox.panels.get(4).role, Panel.PanelRole.top);
    assertEquals(BasedBox.panels.get(5).role, Panel.PanelRole.basedBottom);

    //test simple box panel roles
    assertEquals(SimpleBox.panels.get(0).role, Panel.PanelRole.front);
    assertEquals(SimpleBox.panels.get(1).role, Panel.PanelRole.back);
    assertEquals(SimpleBox.panels.get(2).role, Panel.PanelRole.right);
    assertEquals(SimpleBox.panels.get(3).role, Panel.PanelRole.left);
    assertEquals(SimpleBox.panels.get(4).role, Panel.PanelRole.top);
    assertEquals(SimpleBox.panels.get(5).role, Panel.PanelRole.bottom);

  }

  /******* This test verifys that all normal lid based and simple boxes get assigned the correct edge roles *******/
  @Test
  public void verifyEdgeRoles () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec basedBox = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec simpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);

    // Simple Box
    // front panel
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(0).edges.get(0));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(0).edges.get(1));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(0).edges.get(2));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(0).edges.get(3));

    // back panel
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(1).edges.get(0));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(1).edges.get(1));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(1).edges.get(2));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(1).edges.get(3));

    // right panel
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(2).edges.get(0));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(2).edges.get(1));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(2).edges.get(2));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(2).edges.get(3));

    // left panel
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(3).edges.get(0));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(3).edges.get(1));
    assertEquals(Panel.EdgeRole.male, simpleBox.panels.get(3).edges.get(2));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(3).edges.get(3));

    // lid panel
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(4).edges.get(0));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(4).edges.get(1));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(4).edges.get(2));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(4).edges.get(3));

    // bottom
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(5).edges.get(0));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(5).edges.get(1));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(5).edges.get(2));
    assertEquals(Panel.EdgeRole.female, simpleBox.panels.get(5).edges.get(3));
 
    // Based Box
    // front panel
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(0).edges.get(0));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(0).edges.get(1));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(0).edges.get(2));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(0).edges.get(3));

    // back panel
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(1).edges.get(0));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(1).edges.get(1));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(1).edges.get(2));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(1).edges.get(3));

    // right panel
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(2).edges.get(0));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(2).edges.get(1));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(2).edges.get(2));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(2).edges.get(3));

    // left panel
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(3).edges.get(0));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(3).edges.get(1));
    assertEquals(Panel.EdgeRole.male, basedBox.panels.get(3).edges.get(2));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(3).edges.get(3));

    // lid panel
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(4).edges.get(0));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(4).edges.get(1));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(4).edges.get(2));
    assertEquals(Panel.EdgeRole.female, basedBox.panels.get(4).edges.get(3));

    // bottom
    assertEquals(Panel.EdgeRole.bottom, basedBox.panels.get(5).edges.get(0));
    assertEquals(Panel.EdgeRole.bottom, basedBox.panels.get(5).edges.get(1));
    assertEquals(Panel.EdgeRole.bottom, basedBox.panels.get(5).edges.get(2));
    assertEquals(Panel.EdgeRole.bottom, basedBox.panels.get(5).edges.get(3));
  }
}
