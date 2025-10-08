import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{
    @Test 
    public void firstTest()
    {
        BoxSpec.height = 100;
        BoxSpec.width = 100;
        BoxSpec.depth = 100;
        BoxSpec.tolerance = 2;
        BoxSpec.teethPerEdge = 8;
        double toothDepth = 3.175;       

        Panel panel1 = new Panel("p-0", Panel.PanelRole.side);
        BoxSpec.panels.add(panel1);
        SetEdgeRoles.setRoles(BoxSpec.panels);

        PathGen.generatePanelPath(panel1, BoxSpec.width, BoxSpec.height, BoxSpec.teethPerEdge, toothDepth);

        assertTrue(true);

        assertEquals(BoxSpec.height, 100, 0);
        assertEquals(BoxSpec.width, 100, 0);
        assertEquals(BoxSpec.depth, 100, 0);
        assertEquals(BoxSpec.tolerance, 2, 0);
        assertEquals(BoxSpec.teethPerEdge, 8, 0);

        assertEquals(BoxSpec.panels.get(0).edges.get(0), Panel.EdgeRole.male);
        assertEquals(BoxSpec.panels.get(0).edges.get(1), Panel.EdgeRole.female);
        assertEquals(BoxSpec.panels.get(0).edges.get(2), Panel.EdgeRole.male);
        assertEquals(BoxSpec.panels.get(0).edges.get(3), Panel.EdgeRole.female);

        assertEquals(BoxSpec.panels.get(0).path, "M5.000 5.000 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l6.250 0.000 l0.000 -3.175 l6.250 0.000 l-0.000 3.175 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l3.175 0.000 l0.000 6.250 l-3.175 -0.000 l0.000 6.250 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-6.250 0.000 l0.000 3.175 l-6.250 0.000 l-0.000 -3.175 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 l-3.175 0.000 l0.000 -6.250 l3.175 -0.000 l0.000 -6.250 Z");
    }    
}
