package com.prime.showcase.integration.dragdrop;

import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DragDropFMIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "droppableBarca.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
           
            //lineup
            findElementBySelector("form#fmForm .lineup #availablePlayers " + escapeClientId("availablePlayers:15:player") + ".ui-draggable");
            
            //droppables
            findElementBySelector("form#fmForm .squad #LF.ui-droppable");
            findElementBySelector("form#fmForm .squad #CF.ui-droppable");
            findElementBySelector("form#fmForm .squad #RF.ui-droppable");
            findElementBySelector("form#fmForm .squad #LCM.ui-droppable");
            findElementBySelector("form#fmForm .squad #DM.ui-droppable");
            findElementBySelector("form#fmForm .squad #RCM.ui-droppable");
            findElementBySelector("form#fmForm .squad #LB.ui-droppable");
            findElementBySelector("form#fmForm .squad #CB1.ui-droppable");
            findElementBySelector("form#fmForm .squad #CB2.ui-droppable");
            findElementBySelector("form#fmForm .squad #RB.ui-droppable");
            findElementBySelector("form#fmForm .squad #GK.ui-droppable");
            
            //selected squad dom
            findElementBySelector("form#fmForm #selectedSquad #selectedPlayers");
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
}
