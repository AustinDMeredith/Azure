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

    BoxSpec box1 = new HingedBox(100, 100, 100, 15, 5, "", tols);

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

  @Test
  public void verifyEdgeRoles () {
    
    ArrayList<Double> tols = new ArrayList<Double>();
    for (int i = 0; i < 12; i++) tols.add(1.0);

    BoxSpec box1 = new HingedBox(100, 100, 100, 15, 5, "", tols);

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

}
