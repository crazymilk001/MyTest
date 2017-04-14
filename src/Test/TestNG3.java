package Test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNG3 {
	
  FirefoxDriver dr = new FirefoxDriver();
  @Test
  public void f() throws InterruptedException {
	  
	  Actions a = new Actions(dr);
	  a.moveToElement(dr.findElement(By.linkText("设置"))).perform();
	  Thread.sleep(3000);
	  //dr.findElement(By.className("setpref")).click();
	  a.click(dr.findElement(By.xpath(".//*[@class='setpref']"))).perform();
	  Thread.sleep(3000);
	  a.click(dr.findElement(By.xpath(".//*[@id='gxszButton']/a[1]"))).perform();
	  Thread.sleep(3000);
	 
	  dr.switchTo().alert().accept();
	  File file =((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
	  try {
		FileUtils.copyFile(file, new File("d:\\a.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" }
   
    };
  }
  @BeforeTest
  public void beforeTest() {
	  dr.get("http://www.baidu.com");
	  //dr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	 // dr.manage().window().maximize();

  }

  @AfterTest
  public void afterTest() {
	  dr.quit();
  }

}
