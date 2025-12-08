package com.azure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.ArrayList;

import com.azure.objects.*;
import com.azure.util.services.KerfService;

public class AzureTest {

  // test to make sure validation works
  @Test
  public void verifyValidation () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);

    // engraving too big
    assertThrows(IllegalArgumentException.class, () ->
      new BasedBox(50, 250, 250, Panel.PanelRole.top, 14, "Azure", 80, tols)
    );

    // tooth width too big for width
    assertThrows(IllegalArgumentException.class, () ->
      new SimpleBox(40, 20, 40, Panel.PanelRole.top, 15, "", 10, tols)
    );
    
    // depth too small for sliding lid
    assertThrows(IllegalArgumentException.class, () ->
      new BasedBox(200, 250, 29, Panel.PanelRole.slidingLid, 3, "", 10, tols)
    );

    // height too small for hinged box
    assertThrows(IllegalArgumentException.class, () ->
      new HingedBox(59, 60, 100, 4, "", 10, .03)
    );

    // width to small for lifting lid
    assertThrows(IllegalArgumentException.class, () ->
      new SimpleBox(40, 20, 40, Panel.PanelRole.liftingLid, 3, "", 4, tols)
    );
    
    // valid
    assertDoesNotThrow(() ->
      new BasedBox(50, 250, 250, Panel.PanelRole.top, 14, "AZ", 10, tols)
    );

    // valid
    assertDoesNotThrow(() ->
      new SimpleBox(20, 40, 40, Panel.PanelRole.top, 3, "", 10, tols)
    );
    
    // valid
    assertDoesNotThrow(() ->
      new BasedBox(200, 250, 50, Panel.PanelRole.slidingLid, 14, "", 10, tols)
    );

    // valid
    assertDoesNotThrow(() ->
      new HingedBox(100, 60, 100, 15, "", 10, .03)
    );
    
    // valid
    assertDoesNotThrow(() ->
      new BasedBox(50, 250, 250, Panel.PanelRole.liftingLid, 14, "AZ", 10, tols)
    );
  }

  // test to make sure panels dont overlap in svg
  @Test 
  public void verifyLayout () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box2 = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);
    BoxSpec box3 = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box4 = new BasedBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);
    BoxSpec box5 = new HingedBox(100, 100, 100, 15, "", 10, .03);
    BoxSpec box6 = new SimpleBox(200, 250, 250, Panel.PanelRole.slidingLid, 14, "", 10, tols);
    BoxSpec box7 = new SimpleBox(200, 250, 250, Panel.PanelRole.liftingLid, 14, "", 10, tols);

    // test for overlap in box 1
    for (int i = 1; i < box1.panels.size() - 1; i++) {
      assertEquals(false, overlap(box1.panels.get(i-1), box1.panels.get(i)));
    }
    
    // test for overlap in box 2
    for (int i = 1; i < box2.panels.size() - 1; i++) {
      assertEquals(false, overlap(box2.panels.get(i-1), box2.panels.get(i)));
    }

    // test for overlap in box 3
    for (int i = 1; i < box3.panels.size() - 1; i++) {
      assertEquals(false, overlap(box3.panels.get(i-1), box3.panels.get(i)));
    }
    
    // test for overlap in box 4
    for (int i = 1; i < box4.panels.size() - 1; i++) {
      assertEquals(false, overlap(box4.panels.get(i-1), box4.panels.get(i)));
    }
    
    // test for overlap in box 5
    for (int i = 1; i < box5.panels.size() - 1; i++) {
      assertEquals(false, overlap(box5.panels.get(i-1), box5.panels.get(i)));
    }

    // test for overlap in box 6
    for (int i = 1; i < box6.panels.size() - 1; i++) {
      assertEquals(false, overlap(box6.panels.get(i-1), box6.panels.get(i)));
    }
    
    // test for overlap in box 7
    for (int i = 1; i < box7.panels.size() - 1; i++) {
      assertEquals(false, overlap(box7.panels.get(i-1), box7.panels.get(i)));
    }
  }

  // test to make sure tolerance adjusts path correctly
  @Test
  public void verifyTolerance () {
    ArrayList<Double> low = new ArrayList<Double>();
    ArrayList<Double> medium = new ArrayList<Double>();
    ArrayList<Double> high = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) {
      low.add(.01);
      medium.add(.03);
      high.add(.05);
    }

    // 6 boxes, half get 1 set of dimentions and the other a different set, each element the the 2 sets use different tolerance presets
    BoxSpec box1Low = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, low);
    BoxSpec box1Mid = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, medium);
    BoxSpec box1High = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, high);
    BoxSpec box2Low = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, low);
    BoxSpec box2Mid = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, medium);
    BoxSpec box2High = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, high);

    // run through each box to make sure the change in tolerance does not effect anything
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          assertEquals(box1Low.panels.get(i).width, box1Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1Mid.panels.get(i).width, box1Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1High.panels.get(i).width, box1High.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Low.panels.get(i).width, box2Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Mid.panels.get(i).width, box2Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2High.panels.get(i).width, box2High.panels.get(i).finalEdgeLengths.get(j), 0);
        } else {
          assertEquals(box1Low.panels.get(i).height, box1Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1Mid.panels.get(i).height, box1Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1High.panels.get(i).height, box1High.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Low.panels.get(i).height, box2Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Mid.panels.get(i).height, box2Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2High.panels.get(i).height, box2High.panels.get(i).finalEdgeLengths.get(j), 0);
        }
      }
    }
  }

  // test to make sure kerf service adjusts path correctly
  @Test
  public void verifyKerf () {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);

    // test to see if kerf actully changes
    // low kerf
    KerfService.setKerf(2);
    ArrayList<Double> kerfLow = KerfService.getKerf();
    double toothKerf = kerfLow.get(0) - .03;
    double cornerKerf = toothKerf / 2;
    assertEquals(.12, toothKerf, 0);
    assertEquals(.06, cornerKerf, 0);

    // medium kerf
    KerfService.setKerf(1);
    ArrayList<Double> kerfMid = KerfService.getKerf();
    toothKerf = kerfMid.get(0) - .03;
    cornerKerf = toothKerf / 2;
    assertEquals(.22, toothKerf, 0);
    assertEquals(.11, cornerKerf, 0);
    
    // high kerf
    KerfService.setKerf(0);
    ArrayList<Double> kerfHigh = KerfService.getKerf();
    toothKerf = kerfHigh.get(0) - .03;
    cornerKerf = toothKerf / 2;
    // the actual on this is .31999999999999995 and .15999999999999995 so i would say that is acceptable
    assertEquals(.32, toothKerf, 5e-16);
    assertEquals(.16, cornerKerf, 5e-16);
    
    // 6 boxes, half get 1 set of dimentions and the other a different set, each element the the 2 sets use different kerf presets
    KerfService.setKerf(2);
    BoxSpec box1Low = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, tols);
    KerfService.setKerf(1);
    BoxSpec box1Mid = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, tols);
    KerfService.setKerf(0);
    BoxSpec box1High = new SimpleBox(100, 100, 100, Panel.PanelRole.top, 14, "", 10, tols);
    KerfService.setKerf(2);
    BoxSpec box2Low = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, tols);
    KerfService.setKerf(1);
    BoxSpec box2Mid = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, tols);
    KerfService.setKerf(0);
    BoxSpec box2High = new SimpleBox(40, 50, 60, Panel.PanelRole.top, 3, "", 10, tols);

    // run through each box to make sure the change in kerf does not effect anything
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          assertEquals(box1Low.panels.get(i).width, box1Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1Mid.panels.get(i).width, box1Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1High.panels.get(i).width, box1High.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Low.panels.get(i).width, box2Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Mid.panels.get(i).width, box2Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2High.panels.get(i).width, box2High.panels.get(i).finalEdgeLengths.get(j), 0);
        } else {
          assertEquals(box1Low.panels.get(i).height, box1Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1Mid.panels.get(i).height, box1Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box1High.panels.get(i).height, box1High.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Low.panels.get(i).height, box2Low.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2Mid.panels.get(i).height, box2Mid.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2High.panels.get(i).height, box2High.panels.get(i).finalEdgeLengths.get(j), 0);
        }
      }
    }
  }
  
  // helper for verifyLayout test
  private boolean overlap(Panel a, Panel b) {
    double ax = a.startPoint.get(0);
    double ay = a.startPoint.get(1);
    double aw = a.width;
    double ah = a.height;

    double bx = b.startPoint.get(0);
    double by = b.startPoint.get(1);
    double bw = b.width;
    double bh = b.height;

    double aLeft   = ax;
    double aRight  = ax + aw;
    double aTop    = ay;
    double aBottom = ay + ah;

    double bLeft   = bx;
    double bRight  = bx + bw;
    double bTop    = by;
    double bBottom = by + bh;

    // Overlap if all 4 are true:
    boolean xOverlap = aLeft < bRight && bLeft < aRight;
    boolean yOverlap = aTop  < bBottom && bTop  < aBottom;

    return xOverlap && yOverlap;
  }
}
