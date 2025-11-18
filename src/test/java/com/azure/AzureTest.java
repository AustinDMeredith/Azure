package com.azure;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.azure.objects.*;
public class AzureTest {

  /******* This test verifys that the generated edge length equals the entered length for said edge *******/
  @Test 
  public void verifyLengths() {
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.top, 14, "");
    BoxSpec box2 = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "");
    BoxSpec box3 = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "");
    BoxSpec box4 = new BasedBox(40, 40, 40, Panel.PanelRole.top, 7, "");

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
  
  // test to varify Slidable panel holes are placed correctly
  @Test
  public void verifySlidablePanelHoles() {
    BoxSpec slidingLidBasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "");
    BoxSpec slidingLidSimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "");

    //assertEquals(BasedBox.panels., 0);



  }

  // test to make sure boxes get correct panel roles
  @Test
  public void verifyPanelRoles () {
    BoxSpec slidingLidBasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "");
    BoxSpec slidingLidSimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "");

    BoxSpec BasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "");
    BoxSpec SimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "");

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

    // Test sliding lid based box panel roles
    assertEquals(slidingLidBasedBox.panels.get(0).role, Panel.PanelRole.slidableFront);
    assertEquals(slidingLidBasedBox.panels.get(1).role, Panel.PanelRole.slidableBack);
    assertEquals(slidingLidBasedBox.panels.get(2).role, Panel.PanelRole.slidableRight);
    assertEquals(slidingLidBasedBox.panels.get(3).role, Panel.PanelRole.slidableLeft);
    assertEquals(slidingLidBasedBox.panels.get(4).role, Panel.PanelRole.slidingLid);
    assertEquals(slidingLidBasedBox.panels.get(5).role, Panel.PanelRole.basedBottom);
    assertEquals(slidingLidBasedBox.panels.get(6).role, Panel.PanelRole.bottomLeftRail);
    assertEquals(slidingLidBasedBox.panels.get(7).role, Panel.PanelRole.bottomRightRail);
    assertEquals(slidingLidBasedBox.panels.get(8).role, Panel.PanelRole.topLeftRail);
    assertEquals(slidingLidBasedBox.panels.get(9).role, Panel.PanelRole.topRightRail);
    assertEquals(slidingLidBasedBox.panels.get(10).role, Panel.PanelRole.backRail);
    
    // Test sliding lid simple box panel roles
    assertEquals(slidingLidSimpleBox.panels.get(0).role, Panel.PanelRole.slidableFront);
    assertEquals(slidingLidSimpleBox.panels.get(1).role, Panel.PanelRole.slidableBack);
    assertEquals(slidingLidSimpleBox.panels.get(2).role, Panel.PanelRole.slidableRight);
    assertEquals(slidingLidSimpleBox.panels.get(3).role, Panel.PanelRole.slidableLeft);
    assertEquals(slidingLidSimpleBox.panels.get(4).role, Panel.PanelRole.slidingLid);
    assertEquals(slidingLidSimpleBox.panels.get(5).role, Panel.PanelRole.bottom);
    assertEquals(slidingLidSimpleBox.panels.get(6).role, Panel.PanelRole.bottomLeftRail);
    assertEquals(slidingLidSimpleBox.panels.get(7).role, Panel.PanelRole.bottomRightRail);
    assertEquals(slidingLidSimpleBox.panels.get(8).role, Panel.PanelRole.topLeftRail);
    assertEquals(slidingLidSimpleBox.panels.get(9).role, Panel.PanelRole.topRightRail);
    assertEquals(slidingLidSimpleBox.panels.get(10).role, Panel.PanelRole.backRail);
  }


  // test to make sure panels for sliding lid boxes get correct edge roles
  @Test
  public void verifyEdgeRoles () {
    BoxSpec slidingLidBasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "");
    BoxSpec slidingLidSimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "");

    // sliding lid Based Box
    // front panel
    assertEquals(Panel.EdgeRole.slidableFront, slidingLidBasedBox.panels.get(0).edges.get(0));
    assertEquals(Panel.EdgeRole.female, slidingLidBasedBox.panels.get(0).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(0).edges.get(2));
    assertEquals(Panel.EdgeRole.female, slidingLidBasedBox.panels.get(0).edges.get(3));

    // back panel
    assertEquals(Panel.EdgeRole.slidableBack, slidingLidBasedBox.panels.get(1).edges.get(0));
    assertEquals(Panel.EdgeRole.female, slidingLidBasedBox.panels.get(1).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(1).edges.get(2));
    assertEquals(Panel.EdgeRole.female, slidingLidBasedBox.panels.get(1).edges.get(3));

    // right panel
    assertEquals(Panel.EdgeRole.slidableSide, slidingLidBasedBox.panels.get(2).edges.get(0));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(2).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(2).edges.get(2));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(2).edges.get(3));

    // left panel
    assertEquals(Panel.EdgeRole.slidableSide, slidingLidBasedBox.panels.get(3).edges.get(0));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(3).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(3).edges.get(2));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(3).edges.get(3));

    // lid panel
    assertEquals(Panel.EdgeRole.flatLid, slidingLidBasedBox.panels.get(4).edges.get(0));
    assertEquals(Panel.EdgeRole.flatLid, slidingLidBasedBox.panels.get(4).edges.get(1));
    assertEquals(Panel.EdgeRole.flatLid, slidingLidBasedBox.panels.get(4).edges.get(2));
    assertEquals(Panel.EdgeRole.flatLid, slidingLidBasedBox.panels.get(4).edges.get(3));

    // bottom left rail
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(6).edges.get(0));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(6).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(6).edges.get(2));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(6).edges.get(3));

    // bottom right rail
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(7).edges.get(0));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(7).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(7).edges.get(2));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(7).edges.get(3));


    // top left rail
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(8).edges.get(0));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(8).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(8).edges.get(2));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(8).edges.get(3));
    

    // top right rail
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(9).edges.get(0));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(9).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(9).edges.get(2));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(9).edges.get(3));


    // back rail
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(10).edges.get(0));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(10).edges.get(1));
    assertEquals(Panel.EdgeRole.male, slidingLidBasedBox.panels.get(10).edges.get(2));
    assertEquals(Panel.EdgeRole.flat, slidingLidBasedBox.panels.get(10).edges.get(3));
  }


  // test to make sure engraving isnt too big


  // test to make sure panels dont overlap in svg


  // test to make sure tolerance service adjusts path correctly


  // test to make sure kerf service adjusts path correctly
}
