import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{
    @Test 
    public void firstTest()
    {
        assertTrue(true);

        // createing a box and constructing a rolesetter instance 
        BoxSpec boxSpec = new BoxSpec(100, 100, 100, 1, 8);
        SetEdgeRoles roleSetter = new SetEdgeRoles();
        Panel panel1 = new Panel("p-0", Panel.PanelRole.side); // creating a panel for testing

        boxSpec.panels.add(panel1); // adding panel to list 
        roleSetter.setRoles(boxSpec.panels); // sets roles for panels
  
        assertEquals(Panel.PanelRole.side, panel1.role);
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(0));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(1));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(2));
        assertEquals(Panel.EdgeRole.male, panel1.edges.get(3));


    }    
}
