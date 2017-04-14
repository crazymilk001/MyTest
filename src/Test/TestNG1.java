package Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;  
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG1 {
	
	 public static FirefoxDriver dr = new FirefoxDriver();
	  
  @Test(dataProvider = "dp",priority =2)
  public void f(Integer n, Integer s, Integer t) throws InterruptedException {
		
		Select period = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlPeriod")));
		period.selectByIndex(n);
		Thread.sleep(3000);
		
		Select weekend = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlWeekEnd")));
		weekend.selectByIndex(s);

		Select orgunit = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlOrgUnit")));
		orgunit.selectByIndex(t);
			
		String js = "var user_input = document.getElementById(\"ctl00_ContentPlaceHolder1_ddlPeriod\").value;return user_input";
		String title = (String)((JavascriptExecutor)dr).executeScript( js);  
		System.out.println("title:" + title);
		
		dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnSearch")).click();
  }
  @Test(enabled=false)
  public void f1() throws InterruptedException {
		
		Select period = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlPeriod")));
		period.selectByIndex(4);
		Thread.sleep(3000);
		
		Select weekend = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlWeekEnd")));
		weekend.selectByIndex(4);

		Select orgunit = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlOrgUnit")));
		orgunit.selectByIndex(4);
			

		dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnSearch")).click();
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("after method");
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1,1,1 },
      new Object[] { 2,2,2 }

    };
  }
  
  @BeforeClass
  public void beforeClass() {
	  
	  dr.get("http://localhost/PandaRG.Ops.LaborPerformanceReport.WebApplication/LaborReport.aspx");
	  dr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	  dr.manage().window().setSize(new Dimension(400,800));
	  login();
	  
  }
  public static void login() {
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_txtID")).sendKeys("1000711");
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnLogin")).click();
	  
  }


  @AfterClass
  public void afterClass() {
	  dr.quit();
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("before test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("after test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("after suite");
  }

}
