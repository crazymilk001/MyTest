package Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNG2 {
	
  FirefoxDriver dr = new FirefoxDriver();
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	 List<WebElement> webElements = dr.findElements(By.tagName("input"));
	 
	 for (WebElement item : webElements ){
		 
		 if (item.getAttribute("type").equals("checkbox")) {
			 item.click();
			 
		 }
		 
	 }
	// dr.navigate().refresh();
	 
	 System.out.println(webElements.size());
	 System.out.println(dr.getWindowHandle());
	 
	 List<WebElement> webElements1 = dr.findElements(By.cssSelector("input[type=checkbox]"));
	// webElements1.get(webElements.size()-1).click();
	 
	// dr.switchTo().frame("frame1");
	// dr.findElement(By.id("kw")).sendKeys("webdriver");
	// dr.findElement(By.id("su")).click();
	// dr.switchTo().defaultContent();
	 String js = "var bool = document.getElementById(\"text1\").disabled;return bool;";  

	 boolean title = (boolean)((JavascriptExecutor)dr).executeScript( js); 

	 System.out.println(title);
	
	 dr.findElement(By.id("bt1")).click();
	 dr.switchTo().alert().accept();
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" }
   
    };
  }
  @BeforeTest
  public void beforeTest() {
	  dr.get("http://localhost:63827/HTMLPage1.htm");
	  dr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	  dr.manage().window().maximize();

  }

  @AfterTest
  public void afterTest() {
	  dr.quit();
  }

}
