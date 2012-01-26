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
		WebElement inputTextarea = findElementById("inputarea_input");
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
						"lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, " +
						"qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica," +
						" quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula " +
						"quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, " +
						"fiant sollemnes in futurum.Ut a ante vel risus facilisis ultricies ac vel sapien. " +
						"Vestibulum eu tortor nisl. Nam rhoncus, turpis eu mollis pharetra, nunc dolor consequat nisi, " +
						"at pharetra nulla felis fringilla lacus. Phasellus dignissim dolor ut enim sagittis pellentesque. " +
						"Donec ac leo elit. Maecenas magna eros, sagittis a tempor quis, sollicitudin ut est. " +
						"Duis mattis turpis in lectus imperdiet sit amet elementum sapien vestibulum. Aliquam semper" +
						" hendrerit risus, id congue risus suscipit et. Nulla turpis sapien, condimentum ut rutrum sit amet, " +
						"dictum at dui.Nullam tristique hendrerit consectetur. Quisque vulputate mattis arcu in ornare. " +
						"Vestibulum vel eros dui, nec rutrum justo. Etiam imperdiet pulvinar vestibulum. Etiam sit amet " +
						"neque non neque feugiat auctor id quis massa. Nullam volutpat tortor at orci facilisis dictum. " +
						"Phasellus tellus dolor, dignissim a aliquet eget, mattis rutrum nibh.Nunc non leo lectus, " +
						"id adipiscing dui. Fusce at magna nisi, non porta eros. Praesent vehicula, ligula sed vehicula " +
						"facilisis, mauris mi adipiscing tortor, vitae facilisis sem odio id tellus. Morbi iaculis, ligula " +
						"quis accumsan consequat, odio nisi consequat tortor, vel semper nulla ligula sed mi. Etiam quis est lorem. " +
						"Maecenas posuere egestas mi, ut interdum ligula volutpat faucibus");
		
		assertTrue(inputTextarea.getAttribute("style").contains("overflow-x: hidden; overflow-y: auto;"));
	}

}
