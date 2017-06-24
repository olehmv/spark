package spark;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
	public WebDriver webDriver = new HtmlUnitDriver();

	@Override
	public WebDriver getDefaultDriver() {
		return webDriver;
	}

	@ClassRule
	public static LeapYearServerRule server = new LeapYearServerRule();

	@Test
	public void rootLeapYearTest() {
		goTo("http://localhost:4567/");
		assertThat(pageSource()).contains("Leap year detector");
	}

	@Test
	public void isALeapYear() {
		goTo("http://localhost:4567");
		fill("#year").with("2004");
		submit(".btn");
		assertThat(pageSource()).contains("2004 is a leap year!");
	}

	@Test
	public void isNotALeapYear() {
		goTo("http://localhost:4567");
		fill("#year").with("2003");
		submit(".btn");
		assertThat(pageSource()).contains("2003 is not a leap year!");
	}



}