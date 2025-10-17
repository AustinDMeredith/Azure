import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{

  @Test 
  public void firstTest()
  {
    assertTrue(true);
    BoxSpec box1 = new BoxSpec(100, 100, 100, 2, 8);
    BoxSpec box2 = new BoxSpec(85, 50, 70, 2, 10);

    // 
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(box1.getWidth(), box1.panels.get(i).finalEdgeLengths.get(j), 0);
        assertEquals(box2.getWidth(), box1.panels.get(i).finalEdgeLengths.get(j), 0);
      }
    }
  }    
}
