package com.azure;

import static org.junit.Assert.assertEquals;
//import org.junit.Test;
import java.util.ArrayList;
import com.azure.objects.*;

public class SlidingLidTest {
  // test to make sure rails are the correct size
  public void verifyLength() {
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 6; i++) tols.add(.03);
    BoxSpec box1 = new SimpleBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box2 = new SimpleBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);
    BoxSpec box3 = new BasedBox(200, 250, 250, Panel.PanelRole.top, 14, "", 10, tols);
    BoxSpec box4 = new BasedBox(40, 40, 40, Panel.PanelRole.top, 7, "", 10, tols);

    // Run through each edge and test if it equals the required length
    for (int i = 6; i < 10; i++) {
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
  

  // test to make sure rails have the correct specifications

}
