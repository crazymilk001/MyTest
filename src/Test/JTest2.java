package Test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


@RunWith(value = Parameterized.class)
public class JTest2 {
	FirefoxDriver dr = new FirefoxDriver();
	private List<String[]> list1;

	@Before
	public void setUp() throws Exception {
dr.get("http://localhost/PandaRG.Ops.LaborPerformanceReport.WebApplication/LaborReport.aspx");
dr.manage().timeouts().setScriptTimeout(500, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		//dr.quit();
	}
	@Parameters
	public static List<ArrayList<String[]>> testData() throws IOException {
		    
		    InputStream is;
			is = new FileInputStream("d:\\labor.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			
			ArrayList<String[]> records =new ArrayList<String[]>();
			List<ArrayList<String[]>> list1 =new ArrayList<ArrayList<String[]>>();
					
			for (int i = 0; i <= rowNum; i++) {
						
				XSSFRow row = sheet.getRow(i);
				int colNum = row.getLastCellNum();
				String[] data = new String[colNum];
				
				for (int j = 0; j < colNum; j++) {
					
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					
					data[j] = row.getCell(j).getStringCellValue();
					
				}
				records.add(data);
			}
			workbook.close();
			list1.add(records);
			return list1;
				
	}
    public JTest2 (List<String[]> records) {
    	this.list1 =records;
    	
    }
	@Test
	public void test() throws InterruptedException {
		dr.findElement(By.id("ctl00_ContentPlaceHolder1_txtID")).sendKeys("1000711");
		dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnLogin")).click();
		Select period = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlPeriod")));
		period.selectByIndex(2);
		Thread.sleep(3000);
		
		Select weekend = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlWeekEnd")));
		weekend.selectByIndex(2);

		Select orgunit = new Select(dr.findElement(By.id("ctl00_ContentPlaceHolder1_ddlOrgUnit")));
		orgunit.selectByIndex(2);
		
		
		

		dr.findElement(By.id("ctl00_ContentPlaceHolder1_btnSearch")).click();

		Table table = new Table(dr);
		By by = By.id("ctl00_ContentPlaceHolder1_grdReport1");
					
		int RowCount = table.getRowCount(by);
		//assertEquals(7, RowCount);
		By by1 = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_grdReport1']/tbody/tr[2]");
		int CellCount = table.getColumnCount(by1);
		//assertEquals(6, CellCount);
		System.out.println(RowCount);
		System.out.println(CellCount);
		
		String column ="";
		
		
		for (int i=2; i<=RowCount-1; i++) {
			
			for (int j=0; j<=CellCount; j++) {
				column=String.valueOf(i) + "." + String.valueOf(j);
				//System.out.println(column);
				String values = table.getCellText(by, column);
				
				System.out.println(values);
				System.out.println(list1.get(i)[j]);
			//	assertEquals(values, list1.get(i)[j]);
					
			}
			
		}
		
	}


}
