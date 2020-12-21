package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    public AppTest() {
    }

    /**
     * Test of changeBackground method, of class App.
     */
    @Test
    public void testChangeBackground() throws Exception {
        System.out.println("changeBackground");
        String selectedSource = "nasa";
        App instance = new App();
        boolean expResult = true;
        boolean result = instance.changeBackground(selectedSource);
        assertEquals(expResult, result);
    }

}
