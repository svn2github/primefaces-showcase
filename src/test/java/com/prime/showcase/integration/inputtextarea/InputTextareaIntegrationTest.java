package com.prime.showcase.integration.inputtextarea;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertTrue;

public class InputTextareaIntegrationTest extends AbstractIntegrationTest{
	
	@Before
	public void init(){
		driver.get(toShowcaseUrl("inputTextarea.jsf"));
	}
	
	@Test
	public void shouldScrollInputTextAreaWhenTextLong() {
		WebElement inputTextarea = findElementById("inputarea");
        
        Integer rows1 = Integer.valueOf(inputTextarea.getAttribute("rows"));
        
		inputTextarea.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, " +
						"sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. " +
						"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper " +
						"suscipit lobortis nisl ut aliquip ex ea commodo consequat. " +
						"Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat," +
						" vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui " +
						"blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. " +
						"Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id " +
						"quod mazim placerat facer possim assum. Typi non habent claritatem insitam; " +
						"est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt " +
						"lectores legere me.");
		
        Integer rows2 = Integer.valueOf(inputTextarea.getAttribute("rows"));
        
		assertTrue("InputText area should expand.", rows2 > rows1 );
	}

}
