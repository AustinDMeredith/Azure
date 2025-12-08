package com.azure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import com.azure.objects.*;

public class SlidingLidTest {
  // test to make sure rails are the correct size
  @Test
  public void verifyLength() {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    BoxSpec box2 = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "", 10, tols);
    BoxSpec box3 = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    BoxSpec box4 = new BasedBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "", 10, tols);

    // Run through each edge of the rails and lid and test if it equals the required length
    // lid
    assertEquals(243.65, box1.panels.get(4).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box2.panels.get(4).finalEdgeLengths.get(0), 0);
    assertEquals(243.65, box3.panels.get(4).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box4.panels.get(4).finalEdgeLengths.get(0), 0);
    
    assertEquals(246.825, box1.panels.get(4).finalEdgeLengths.get(1), 0);
    assertEquals(36.825, box2.panels.get(4).finalEdgeLengths.get(1), 0);
    assertEquals(246.825, box3.panels.get(4).finalEdgeLengths.get(1), 0);
    assertEquals(36.825, box4.panels.get(4).finalEdgeLengths.get(1), 0);
    
    assertEquals(243.65, box1.panels.get(4).finalEdgeLengths.get(2), 0);
    assertEquals(33.65, box2.panels.get(4).finalEdgeLengths.get(2), 0);
    assertEquals(243.65, box3.panels.get(4).finalEdgeLengths.get(2), 0);
    assertEquals(33.65, box4.panels.get(4).finalEdgeLengths.get(2), 0);
    
    assertEquals(246.825, box1.panels.get(4).finalEdgeLengths.get(3), 0);
    assertEquals(36.825, box2.panels.get(4).finalEdgeLengths.get(3), 0);
    assertEquals(246.825, box3.panels.get(4).finalEdgeLengths.get(3), 0);
    assertEquals(36.825, box4.panels.get(4).finalEdgeLengths.get(3), 0);
    
    // bottom left rail
    assertEquals(243.65, box1.panels.get(6).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box2.panels.get(6).finalEdgeLengths.get(0), 0);
    assertEquals(243.65, box3.panels.get(6).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box4.panels.get(6).finalEdgeLengths.get(0), 0);
    
    assertEquals(4.825, box1.panels.get(6).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box2.panels.get(6).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box3.panels.get(6).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box4.panels.get(6).finalEdgeLengths.get(1), 0);
    
    assertEquals(250, box1.panels.get(6).finalEdgeLengths.get(2), 0);
    assertEquals(40, box2.panels.get(6).finalEdgeLengths.get(2), 0);
    assertEquals(250, box3.panels.get(6).finalEdgeLengths.get(2), 0);
    assertEquals(40, box4.panels.get(6).finalEdgeLengths.get(2), 0);
    
    assertEquals(4.825, box1.panels.get(6).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box2.panels.get(6).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box3.panels.get(6).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box4.panels.get(6).finalEdgeLengths.get(3), 0);
    
    // bottom right rail
    assertEquals(250, box1.panels.get(7).finalEdgeLengths.get(2), 0);
    assertEquals(40, box2.panels.get(7).finalEdgeLengths.get(2), 0);
    assertEquals(250, box3.panels.get(7).finalEdgeLengths.get(2), 0);
    assertEquals(40, box4.panels.get(7).finalEdgeLengths.get(2), 0);
    
    assertEquals(4.825, box1.panels.get(7).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box2.panels.get(7).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box3.panels.get(7).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box4.panels.get(7).finalEdgeLengths.get(1), 0);
    
    assertEquals(243.65, box1.panels.get(7).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box2.panels.get(7).finalEdgeLengths.get(0), 0);
    assertEquals(243.65, box3.panels.get(7).finalEdgeLengths.get(0), 0);
    assertEquals(33.65, box4.panels.get(7).finalEdgeLengths.get(0), 0);
    
    assertEquals(4.825, box1.panels.get(7).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box2.panels.get(7).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box3.panels.get(7).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box4.panels.get(7).finalEdgeLengths.get(3), 0);
    
    // top left rail
    assertEquals(246.825, box1.panels.get(8).finalEdgeLengths.get(0), 0);
    assertEquals(36.825, box2.panels.get(8).finalEdgeLengths.get(0), 0);
    assertEquals(246.825, box3.panels.get(8).finalEdgeLengths.get(0), 0);
    assertEquals(36.825, box4.panels.get(8).finalEdgeLengths.get(0), 0);
    
    assertEquals(4.825, box1.panels.get(8).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box2.panels.get(8).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box3.panels.get(8).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box4.panels.get(8).finalEdgeLengths.get(1), 0);

    assertEquals(250, box1.panels.get(8).finalEdgeLengths.get(2), 0);
    assertEquals(40, box2.panels.get(8).finalEdgeLengths.get(2), 0);
    assertEquals(250, box3.panels.get(8).finalEdgeLengths.get(2), 0);
    assertEquals(40, box4.panels.get(8).finalEdgeLengths.get(2), 0);
    
    assertEquals(4.825, box1.panels.get(8).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box2.panels.get(8).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box3.panels.get(8).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box4.panels.get(8).finalEdgeLengths.get(3), 0);
    
    // top right rail
    assertEquals(246.825, box1.panels.get(9).finalEdgeLengths.get(0), 0);
    assertEquals(36.825, box2.panels.get(9).finalEdgeLengths.get(0), 0);
    assertEquals(246.825, box3.panels.get(9).finalEdgeLengths.get(0), 0);
    assertEquals(36.825, box4.panels.get(9).finalEdgeLengths.get(0), 0);
    
    assertEquals(4.825, box1.panels.get(9).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box2.panels.get(9).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box3.panels.get(9).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box4.panels.get(9).finalEdgeLengths.get(1), 0);
    
    assertEquals(250, box1.panels.get(9).finalEdgeLengths.get(2), 0);
    assertEquals(40, box2.panels.get(9).finalEdgeLengths.get(2), 0);
    assertEquals(250, box3.panels.get(9).finalEdgeLengths.get(2), 0);
    assertEquals(40, box4.panels.get(9).finalEdgeLengths.get(2), 0);
    
    assertEquals(4.825, box1.panels.get(9).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box2.panels.get(9).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box3.panels.get(9).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box4.panels.get(9).finalEdgeLengths.get(3), 0);
    
    // back rail
    assertEquals(250, box1.panels.get(10).finalEdgeLengths.get(2), 0);
    assertEquals(40, box2.panels.get(10).finalEdgeLengths.get(2), 0);
    assertEquals(250, box3.panels.get(10).finalEdgeLengths.get(2), 0);
    assertEquals(40, box4.panels.get(10).finalEdgeLengths.get(2), 0);
    
    assertEquals(4.825, box1.panels.get(10).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box2.panels.get(10).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box3.panels.get(10).finalEdgeLengths.get(1), 0);
    assertEquals(4.825, box4.panels.get(10).finalEdgeLengths.get(1), 0);
    
    assertEquals(234, box1.panels.get(10).finalEdgeLengths.get(0), 0);
    assertEquals(24, box2.panels.get(10).finalEdgeLengths.get(0), 0);
    assertEquals(234, box3.panels.get(10).finalEdgeLengths.get(0), 0);
    assertEquals(24, box4.panels.get(10).finalEdgeLengths.get(0), 0);
    
    assertEquals(4.825, box1.panels.get(10).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box2.panels.get(10).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box3.panels.get(10).finalEdgeLengths.get(3), 0);
    assertEquals(4.825, box4.panels.get(10).finalEdgeLengths.get(3), 0);
  }
  

  // test to varify Slidable panel holes are placed correctly
  @Test
  public void verifySlidablePanelHoles() {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    BoxSpec box2 = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);

    double box1RightSP = box1.panels.get(2).holeStartPoints.get(0);
    double box1LeftSP = box1.panels.get(3).holeStartPoints.get(0);
    double box2RightSP = box2.panels.get(2).holeStartPoints.get(0);
    double box2LeftSP = box2.panels.get(3).holeStartPoints.get(0);

    for (int i = 0; i < 8; i++) {

      assertEquals(box1RightSP, box1.panels.get(2).holeStartPoints.get(i), 0);
      assertEquals(box1LeftSP, box1.panels.get(3).holeStartPoints.get(i), 0);
      assertEquals(box2RightSP, box1.panels.get(2).holeStartPoints.get(i), 0);
      assertEquals(box2LeftSP, box1.panels.get(3).holeStartPoints.get(i), 0);

      box1RightSP += 2 * 14;
      box1LeftSP += 2 * 14;
      box2RightSP += 2 * 14;
      box2LeftSP += 2 * 14;
    } 
  }
  // test to make sure rails have the correct specifications

  
  // test to make sure panels for sliding lid boxes get correct edge roles
  @Test
  public void verifyEdgeRoles () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec slidingLidBasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    //BoxSpec slidingLidSimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "");

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


  // test to make sure boxes get correct panel roles
  @Test
  public void verifyPanelRoles () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec slidingLidBasedBox = new BasedBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    BoxSpec slidingLidSimpleBox = new SimpleBox(40, 40, 40, Panel.PanelRole.slidingLid, 7, "", 10, tols);

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
}
