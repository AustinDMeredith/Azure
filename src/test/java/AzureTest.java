import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{
    @Test 
    public void firstTest()
    {
        assertTrue(true);

    BoxSpec.height = 100;
    BoxSpec.width = 100;
    BoxSpec.depth = 100;
    BoxSpec.tolerance = 2;
    BoxSpec.teethPerEdge = 8;
    Panel panel1 = new Panel("p-0", Panel.PanelRole.side);

    BoxSpec.panels.add(panel1);
    SetEdgeRoles.setRoles(BoxSpec.panels);

    int teethPerEdge = 8;          // from BoxSpec if you like:contentReference[oaicite:6]{index=6}
    double toothDepth = 3.175;       // mm, tuned to material thickness/fit
    double width = 100, height = 100;
    
    PathGen.generatePanelPath(panel1, width, height, teethPerEdge, toothDepth);


    }    
}
