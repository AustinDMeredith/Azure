import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{
    @Test 
    public void firstTest()
    {
        assertTrue(true);

        
        BoxSpec boxSpec = new BoxSpec(100, 100, 100, 1, 8);
        SetEdgeRoles roleSetter = new SetEdgeRoles();
        Panel panel1 = new Panel("p-0", Panel.PanelRole.side);

        boxSpec.panels.add(panel1);
        roleSetter.setRoles(boxSpec.panels);
  
        assertEquals(Panel.PanelRole.side, panel1.role);
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(0));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(1));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(2));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(3));


    }    
}
