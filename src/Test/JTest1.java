/**
 * 
 */
package Test;

import static org.junit.Assert.*;

import java.sql.Driver;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author jenny.zhang
 *
 */
public class JTest1 {
	WebDriver dr = new FirefoxDriver();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dr.get("http://www.baidu.com");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dr.quit();
	}

	@Test
	public void test() {
		String url1 = dr.getCurrentUrl();
		System.out.println(url1);
		String title =dr.getTitle();
		System.out.println(title);
		System.out.println(dr.getWindowHandle());
		//((JavascriptExecutor)dr).executeScript("alert(\"hello world\")");
		//dr.findElement(By.id("kw")).sendKeys("selenium");
		//dr.findElement(By.id("su")).click();
		List<WebElement> elements = dr.findElements(By.tagName("input"));
		
		for (WebElement e : elements) {
			
			System.out.println(e.getAttribute("id"));
			
			
		}
	
		Set<Cookie> cookies =dr.manage().getCookies();
		
		for (Cookie c : cookies) {
			
			System.out.println(String.format("%s -> %s -> %s -> %s -> %s", c.getDomain(), c.getPath(),c.getExpiry(),c.getClass(),c.getName()));
			
		}
		
		WebDriverWait wait = new WebDriverWait( dr, 10);
		
		
		
		
		
	}

}
