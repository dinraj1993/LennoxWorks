package lennox.lennox;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import lennox.lennox.lennoxPage;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lennoxTest  {

	public static FileInputStream fis;

	public static String launchURL ="https://liidaveqa.com";

	public static String excelLocation = "C:\\Users\\Dinesh\\workspace\\lennox\\testdata.xlsx";

	public static String execBrowser= "C:\\Users\\Dinesh\\workspace\\lennox\\chromedriver.exe";

	public static ArrayList<String> outputList = new ArrayList();
	public static ArrayList<String> headerList = new ArrayList();
	public static void main(String arg[]) throws Exception{
		try{
			fis = new FileInputStream(new File(excelLocation));
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Map<String,Map<String,String>> map2 = new LinkedHashMap();
			Row row = sheet.getRow(0);
			Iterator iterator = row.cellIterator();
			while(iterator.hasNext()){
				Cell cell = (Cell)iterator.next();
				String header1=cell.toString();
				System.out.println("header 1 is"+header1);
				headerList.add(header1);
			}
			System.out.println("headerList is"+headerList);
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
				Row row1 = sheet.getRow(i);
				Cell rowNo = row1.getCell(0);
				double number = Double.parseDouble(rowNo.toString());
				int serialNo = (int)number;
				String num = String.valueOf(serialNo);
				Map<String,String> map = new LinkedHashMap();
				for(int j=1;j<headerList.size();j++){
					Cell c = row1.getCell(j);
					if(c == null || c.getCellType()== Cell.CELL_TYPE_BLANK){
						System.out.println("Data is not there in the excel for" +headerList.get(j) +"in serial number of" +num);
					}
					else{
						map.put(headerList.get(j),c.toString());
					}
				}
				map2.put(num,map);
			}
			System.out.println("final map2 value is"+map2);
			fis.close();
			System.setProperty("webdriver.chrome.driver", execBrowser);
			WebDriver driver=new ChromeDriver();
//			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("test-type");
//            options.addArguments("--start-maximized");
//            options.addArguments("--disable-web-security");
//            options.addArguments("--allow-running-insecure-content");
//            capabilities.setCapability("chrome.binary","./src//lib//chromedriver");
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);          
//            driver = new ChromeDriver(capabilities);
			
//						driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.get(launchURL);
			driver.findElement(By.xpath("//button[contains(text(),'Advanced')]")).click();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[contains(text(),'Advanced')]")).click();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
			lennoxPage.productComparision(driver,map2,headerList);
			driver.quit();

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}

