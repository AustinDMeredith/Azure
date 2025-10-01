import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AzureTest 
{
    @Test 
    public void firstTest()
    {
        assertTrue(true); 
        Azure azure = new Azure();
        assertEquals(10, azure.add(7, 3));
    }    
}
