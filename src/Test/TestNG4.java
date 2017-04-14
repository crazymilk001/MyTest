package Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;  
import org.apache.poi.hslf.record.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNG4 {
	
	 public static FirefoxDriver dr = new FirefoxDriver();
	 
  @Test(dataProvider = "dp")
  public void f(List n, List s) {
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlQuarterYear")).getText();
	  Select period = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlQuarterYear")));
	  period.selectByValue("2016-P3");

	  Select orgunit = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlOrg")));
	  orgunit.selectByIndex(4);
	  
	  Select status = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlStatus")));
	  status.selectByIndex(3);

	  dr.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_btnView1']")).click();
	  
  }
  
  @Test(dataProvider = "dp")
  public void f2(List n, List s) {
	  	  
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_rdGroup2")).click();
	 
	 
	 // String js = "var boo=document.getElementById(\"ctl00_ContentPlaceHolder1_txtEmpID\").ID;return boo;";
	//  String bo = (String) ((JavascriptExecutor)dr).executeScript(js);
	// System.out.println(bo);
	  dr.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_txtEmpID']")).isEnabled();
		  try {
	  Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  dr.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_txtEmpID']")).sendKeys("1150676");
	  dr.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_btnView1']")).click();
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @DataProvider
  public Object[][] dp() {
	  
	  ArrayList status = new ArrayList();
	  status.add("All");
	  status.add("Pending Approval");
	  status.add("Approved");
	  status.add("Completed");
	  status.add("Rejected");
	  List period = new ArrayList();
	  Calendar cal = Calendar.getInstance();
	 
	  int year =  cal.get(Calendar.YEAR);
	  
	  String temp="";
	  for (int i=1;i<=13;i++){
		  temp =String.valueOf(year) +"-P" +String.valueOf(i);
		  period.add(temp);
	  }
	  
    return new Object[][] {
      new Object[] { status, period }
  
    };
  }
  
  @BeforeTest
  public void beforeTest() {
	  dr.get("http://localhost/PandaRG.HR.OpsMgmtBonusPlan.WebApplication/ReviewPeriodicBonus.aspx");
	//  dr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	  
	  login();
  }
  
  public static void login() {
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_txtID")).clear();
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_txtID")).sendKeys("1113737");
	  dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnLogin")).click();
	  
  }
  
  @AfterTest
  public void afterTest() {
	 // dr.quit();
  }

}
