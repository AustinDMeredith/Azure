import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{

  @Test 
  public void firstTest()
  {
    assertTrue(true);
    BoxSpec box1 = new SimpleBox(200, 250, 250, 2, 14, "");
    BoxSpec box2 = new SimpleBox(40, 40, 40, 2, 7, "");

    // 
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        if (j % 2 == 0) {
          assertEquals(box1.panels.get(i).width, box1.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2.panels.get(i).width, box2.panels.get(i).finalEdgeLengths.get(j), 0);
        } else {
          assertEquals(box1.panels.get(i).height, box1.panels.get(i).finalEdgeLengths.get(j), 0);
          assertEquals(box2.panels.get(i).height, box2.panels.get(i).finalEdgeLengths.get(j), 0);
        }
      }
    }
  }
  
  // add more tests here
}
