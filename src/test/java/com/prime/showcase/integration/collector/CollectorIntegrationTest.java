package com.prime.showcase.integration.collector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CollectorIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("collector.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldCollectSingleData() {
		String book1 = "Book 1", author1 = "Author 1";

		addBook(book1, author1);

		waitUntilAjaxRequestCompletes();

		List<WebElement> books = getBooksFromCollector();

		assertThat(books.size(), equalTo(1));

		List<WebElement> tdElements = getTdElements(books.get(0));

		verifyBook1(book1, author1, tdElements);
	}

	private void verifyBook1(String book1, String author1, List<WebElement> tdElements) {
		assertThat(tdElements.get(0).findElement(By.tagName("div")).getText(), equalTo(book1));
		assertThat(tdElements.get(1).findElement(By.tagName("div")).getText(), equalTo(author1));
	}

	private List<WebElement> getTdElements(WebElement bookTrElement) {
		return bookTrElement.findElements(By.tagName("td"));
	}

	private List<WebElement> getBooksFromCollector() {
		return findElementById("form:booksTable").findElement(By.id("form:booksTable_data")).findElements(By.tagName("tr"));
	}

	@Test
	public void shouldAddAndRemoveMultipleData() {
		String book1 = "Book 1", author1 = "Author 1";
		String book2 = "Book 2", author2 = "Author 2";

		addBook(book1, author1);
		waitUntilAjaxRequestCompletes();

		addBook(book2, author2);
		waitUntilAjaxRequestCompletes();

		List<WebElement> books = getBooksFromCollector();

		assertThat(books.size(), equalTo(2));

		List<WebElement> firstBooksTdElements = getTdElements(books.get(0));
		List<WebElement> secondBooksTdElements = getTdElements(books.get(1));

		verifyBook1(book1, author1, firstBooksTdElements);
		verifyBook1(book2, author2, secondBooksTdElements);

		WebElement removeLink = secondBooksTdElements.get(2).findElement(By.tagName("a"));
		removeLink.click();

		waitUntilAjaxRequestCompletes();

		books = getBooksFromCollector();

		assertThat(books.size(), equalTo(1));

		firstBooksTdElements = getTdElements(books.get(0));
		
		verifyBook1(book1, author1, firstBooksTdElements);
	}

	private void addBook(String book1, String author1) {
		WebElement titleInput = findElementById("form:txt_title");
		WebElement authorInput = findElementById("form:txt_author");
		WebElement button = findElementById("form:btn_add");

		titleInput.sendKeys(book1);
		authorInput.sendKeys(author1);
		button.click();
	}
}
