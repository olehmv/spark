package spark;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.junit.Assert.*;

import org.fluentlenium.adapter.FluentTest;

public class TaskTest extends FluentTest {
	public WebDriver webDriver = new HtmlUnitDriver();

	@Override
	public WebDriver getDefaultDriver() {
		return webDriver;
	}

	@ClassRule
	public static TaskServerRule server = new TaskServerRule();

	@Test
	public void Task_instantiatesCorrectly_true() {
		Task myTask = new Task("Mow the lawn");
		assertEquals(true, myTask instanceof Task);
	}

	@Test
	public void Task_instantiatesWithDescription_String() {
		Task myTask = new Task("Mow the lawn");
		assertEquals("Mow the lawn", myTask.getDescription());
	}

	@Test
	public void rootTaskTest() {
		goTo("http://localhost:4567/");
		assertThat(pageSource()).contains("Task list!");
	}

	@Test
	public void taskIsCreatedTest() {
		goTo("http://localhost:4567/");
		fill("#description").with("Mow the lawn");
		submit(".btn");
		assertThat(pageSource()).contains("Your task has been saved.");
	}

	@Test
	public void taskIsDisplayedTest() {
		goTo("http://localhost:4567/");
		fill("#description").with("Mow the lawn");
		submit(".btn");
		click("a", withText("Go Back"));
		assertThat(pageSource()).contains("Mow the lawn");
	}

	@Test
	public void multipleTasksAreDisplayedTest() {
		goTo("http://localhost:4567/");
		fill("#description").with("Mow the lawn");
		submit(".btn");
		click("a", withText("Go Back"));
		fill("#description").with("Buy groceries");
		submit(".btn");
		click("a", withText("Go Back"));
		assertThat(pageSource()).contains("Mow the lawn");
		assertThat(pageSource()).contains("Buy groceries");
	}

}