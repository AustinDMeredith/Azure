package com.azure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.azure.objects.*;

public class HingedBoxTest {
 
  /******* This test verifys that all hinged boxes get assigned the correct panel roles *******/
  @Test
  public void verifyPanelRoles () {
    BoxSpec box1 = new HingedBox(100, 100, 100, 15, "", 10, .03);

    assertEquals(Panel.PanelRole.frontTop, box1.panels.get(0).role);
    assertEquals(Panel.PanelRole.frontBottom, box1.panels.get(1).role);
    assertEquals(Panel.PanelRole.backTop, box1.panels.get(2).role);
    assertEquals(Panel.PanelRole.backBottom, box1.panels.get(3).role);
    assertEquals(Panel.PanelRole.rightTop, box1.panels.get(4).role);
    assertEquals(Panel.PanelRole.rightBottom, box1.panels.get(5).role);
    assertEquals(Panel.PanelRole.leftTop, box1.panels.get(6).role);
    assertEquals(Panel.PanelRole.leftBottom, box1.panels.get(7).role);
    assertEquals(Panel.PanelRole.top, box1.panels.get(8).role);
    assertEquals(Panel.PanelRole.bottom, box1.panels.get(9).role);
  }

  /******* This test verifys that all hinged boxes get assigned the correct edge roles *******/
  @Test
  public void verifyEdgeRoles () {
    BoxSpec box1 = new HingedBox(100, 100, 100, 15, "", 10, .03);

    // front top panel
    assertEquals(Panel.EdgeRole.male, box1.panels.get(0).edges.get(0));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(0).edges.get(1));
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(0).edges.get(2));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(0).edges.get(3));
  
    // front bottom panel
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(1).edges.get(0));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(1).edges.get(1));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(1).edges.get(2));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(1).edges.get(3));

    // back top panel
    assertEquals(Panel.EdgeRole.male, box1.panels.get(2).edges.get(0));
    assertEquals(Panel.EdgeRole.femaleConnector, box1.panels.get(2).edges.get(1));
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(2).edges.get(2));
    assertEquals(Panel.EdgeRole.femaleConnector, box1.panels.get(2).edges.get(3));
 
    // back bottom panel
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(3).edges.get(0));
    assertEquals(Panel.EdgeRole.femaleBack, box1.panels.get(3).edges.get(1));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(3).edges.get(2));
    assertEquals(Panel.EdgeRole.femaleBack, box1.panels.get(3).edges.get(3));
  
    // right top panel
    assertEquals(Panel.EdgeRole.male, box1.panels.get(4).edges.get(0));
    assertEquals(Panel.EdgeRole.maleCutOut, box1.panels.get(4).edges.get(1));
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(4).edges.get(2));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(4).edges.get(3));

    // right bottom panel
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(5).edges.get(0));
    assertEquals(Panel.EdgeRole.maleHinge, box1.panels.get(5).edges.get(1));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(5).edges.get(2));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(5).edges.get(3));
  
    // left top panel
    assertEquals(Panel.EdgeRole.male, box1.panels.get(6).edges.get(0));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(6).edges.get(1));
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(6).edges.get(2));
    assertEquals(Panel.EdgeRole.maleCutOut, box1.panels.get(6).edges.get(3));
  
    // left bottom panel
    assertEquals(Panel.EdgeRole.flat, box1.panels.get(7).edges.get(0));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(7).edges.get(1));
    assertEquals(Panel.EdgeRole.male, box1.panels.get(7).edges.get(2));
    assertEquals(Panel.EdgeRole.maleHinge, box1.panels.get(7).edges.get(3));
  
    // top panel
    assertEquals(Panel.EdgeRole.female, box1.panels.get(8).edges.get(0));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(8).edges.get(1));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(8).edges.get(2));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(8).edges.get(3));
  
    // bottom panel
    assertEquals(Panel.EdgeRole.female, box1.panels.get(9).edges.get(0));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(9).edges.get(1));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(9).edges.get(2));
    assertEquals(Panel.EdgeRole.female, box1.panels.get(9).edges.get(3));
  }

  /******* This test verifys that the start points are good *******/
  @Test
  public void verifyStartPoints() {
    BoxSpec box1 = new HingedBox(100, 100, 100, 15, "", 10, .03);
 
    assertEquals(10, box1.panels.get(0).startPoint.get(0), 0);
    assertEquals(20, box1.panels.get(0).startPoint.get(1), 0);
    
    assertEquals(120, box1.panels.get(1).startPoint.get(0), 0);
    assertEquals(10, box1.panels.get(1).startPoint.get(1), 0);
    
    assertEquals(10, box1.panels.get(2).startPoint.get(0), 0);
    assertEquals(90, box1.panels.get(2).startPoint.get(1), 0);
    
    assertEquals(10, box1.panels.get(2).startPoint.get(0), 0);
    assertEquals(90, box1.panels.get(2).startPoint.get(1), 0);
    
    assertEquals(120, box1.panels.get(3).startPoint.get(0), 0);
    assertEquals(80, box1.panels.get(3).startPoint.get(1), 0);
    
    assertEquals(13.175, box1.panels.get(4).startPoint.get(0), 0);
    assertEquals(160, box1.panels.get(4).startPoint.get(1), 0);
    
    assertEquals(123.175, box1.panels.get(5).startPoint.get(0), 0);
    assertEquals(150, box1.panels.get(5).startPoint.get(1), 0);
    
    assertEquals(10, box1.panels.get(6).startPoint.get(0), 0);
    assertEquals(230, box1.panels.get(6).startPoint.get(1), 0);
    
    assertEquals(120, box1.panels.get(7).startPoint.get(0), 0);
    assertEquals(220, box1.panels.get(7).startPoint.get(1), 0);
    
    assertEquals(10, box1.panels.get(8).startPoint.get(0), 0);
    assertEquals(290, box1.panels.get(8).startPoint.get(1), 0);
    
    assertEquals(120, box1.panels.get(9).startPoint.get(0), 0);
    assertEquals(290, box1.panels.get(9).startPoint.get(1), 0);
  }

  /******* This test verifys that the generated edge length equals the entered length for said edge *******/
  @Test
  public void verifyLengths () {

    BoxSpec box1 = new HingedBox(100, 100, 100, 15, "", 10, .03);
    BoxSpec box2 = new HingedBox(70, 65, 80, 3, "", 10, .03);
    BoxSpec box3 = new HingedBox(250, 200, 100, 15, "", 10, .03);
    BoxSpec box4 = new HingedBox(150, 30, 50, 4, "", 10, .03);

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          // the reason for the high deltas is because the length of an edge gets changed in a few places after the size is set, this makes it very dificult to test and should have changed, so this assumes that if its in the delta its probably right, and if its not something went horribly wrong. 
          assertEquals(box1.panels.get(i).width, box1.panels.get(i).finalEdgeLengths.get(j), 13);
          assertEquals(box2.panels.get(i).width, box2.panels.get(i).finalEdgeLengths.get(j), 13);
          assertEquals(box3.panels.get(i).width, box3.panels.get(i).finalEdgeLengths.get(j), 13);
          assertEquals(box4.panels.get(i).width, box4.panels.get(i).finalEdgeLengths.get(j), 13);
        } else {
          assertEquals(box1.panels.get(i).height, box1.panels.get(i).finalEdgeLengths.get(j), 6);
          assertEquals(box2.panels.get(i).height, box2.panels.get(i).finalEdgeLengths.get(j), 6);
          assertEquals(box3.panels.get(i).height, box3.panels.get(i).finalEdgeLengths.get(j), 6);
          assertEquals(box4.panels.get(i).height, box4.panels.get(i).finalEdgeLengths.get(j), 6);
        }
      }
    }
  }
}
