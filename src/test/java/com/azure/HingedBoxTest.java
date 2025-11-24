package com.azure;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

import com.azure.objects.*;
public class HingedBoxTest {
  

  @Test
  public void verifyPanelRoles () {

    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 12; i++) tols.add(1.0);

    BoxSpec box1 = new HingedBox(100, 100, 100, 15, "", tols);

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
    assertEquals(Panel.PanelRole.hingeRight, box1.panels.get(10).role);
    assertEquals(Panel.PanelRole.hingeLeft, box1.panels.get(11).role);
  }
}
