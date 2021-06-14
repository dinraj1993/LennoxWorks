package lennox.lennox;
import lennox.lennox.lennoxTest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

/**
 * Hello world!
 *
 */
public class lennoxPage extends lennoxTest 
{

	public static By signIn = By.xpath("//a[text()='Sign In']");
	public static By userName = By.xpath("//input[@id='j_username']");
	public static By passWord = By.xpath("//input[@id='j_password']");
	public static By btnSignIn = By.xpath("//button[contains(text(),'Sign')]");
	public static By txtSignedIn = By.xpath("//span[contains(@class,'signedin')]");
	public static By btnCloseCookie = By.xpath("//div[@id='onetrust-close-btn-container']");
	public static By iconHamburger = By.xpath("//i[contains(@class,'hamburger')]");
	public static By txtHeader = By.xpath("//h1");
	public static By subTxtDescription = By.xpath("//h1//..//following-sibling::p");
	public static By btnNext = By.xpath("//a[@rel='next']");
	public static By prodDescription = By.xpath("//div[@class='OT-product-shop']//h1");
	public static By detailsDeviceSKU = By.xpath("(//div[@class='OT-product-shop']//p[@class='sku']/span)[1]");	
	public static By detailsModel = By.xpath("//div[@class='OT-product-shop']//p//span[@class='pdp-model-number']");
	public static By detailsYourPrice = By.xpath("(//div[@class='OT-product-shop']//p[@class='price']//following-sibling::span)[1]");
	public static By detailsListPrice = By.xpath("(//div[@class='OT-product-shop']//p[@class='price']//following-sibling::span//following-sibling::text()[1])[2]");
	public static By detailsShip = By.xpath("//label[contains(@for,'dm-ship')]//span");
	public static By detailsPick = By.xpath("(//label[contains(@for,'dm-pick')]//..//following-sibling::div)[1]");
	public static By logout = By.xpath("//*[@id='logout_id']");
		


	public static By mainLink(String text){
		By mainLink = By.xpath("(//a[text()='"+text+"'])[2]");
		return mainLink;
	}

	public static By subLink(String text1,String text2){
		By subLink = By.xpath("//a[text()='"+text1+"']//i//..//..//a[text()='"+text2+"']");
		return subLink;
	}

	public static By subLinkNavigation(String text1){
		By subLinkNavigation = By.xpath("//ul[preceding-sibling::h4]//li//a[text()='"+text1+"']");
		return subLinkNavigation;
	}

	public static By skuDetails(String text1){
		By skuDetails = By.xpath("//div[@class='sku' and contains(.,'"+text1+"')]");
		return skuDetails;
	}

	public static By productDescription(String text1){
		By productDescription = By.xpath("//div[@class='sku' and contains(.,'"+text1+"')]//preceding-sibling::div//h2");
		return productDescription;
	}

	public static By modelPart(String text1){
		By modelPart = By.xpath("//div[@class='sku' and contains(.,'"+text1+"')]//following-sibling::*[contains(text(),'Model')]//following-sibling::text()");
		return modelPart;
	}

	public static By yourPrice(String text1){
		By yourPrice = By.xpath("//div[@class='sku' and contains(.,'"+text1+"')]//following-sibling::div//p[@class='your-price']//span//following-sibling::text()[1]");
		return yourPrice;
	}

	public static By listPrice(String text1){
		By listPrice = By.xpath("//div[@class='sku' and contains(.,'"+text1+"')]//following-sibling::div//p[@class='list-price']//span//following-sibling::text()[1]");
		return listPrice;
	}

	public static By standardShipping(String text1){
		By standardShipping = By.xpath("//label[@for='shipping-method-"+text1+"']//..//following-sibling::div[contains(@class,'availability')]");
		return standardShipping;
	}

	public static By pickUpAvailability(String text1){
		By pickUpAvailability = By.xpath("//label[@for='pickup-method-"+text1+"']//..//following-sibling::div[contains(@class,'availability')]");
		return pickUpAvailability;
	}

	public static By zipCode(String text1){
		By zipCode = By.xpath("//label[@for='pickup-method-"+text1+"']//..//following-sibling::div//span[contains(@class,'zip')]");
		return zipCode;
	}

	public static By btnAddToCart(String text1){
		By btnAddToCart = By.xpath("//button[@id='product-button-"+text1+"']");
		return btnAddToCart;
	}

	public static By image(String text1){
		By image = By.xpath("//a[contains(@href,'"+text1+"')]//img");
		return image;
	}

	public static void productComparision(WebDriver driver,Map<String,Map<String,String>> map2,ArrayList<String> headerList){
		System.out.println("in page"+map2);
		elementClick(driver,signIn);
		for(Entry<String,Map<String,String>> e:map2.entrySet()){
			elementClick(driver,signIn);
			System.out.println("key"+e.getKey()+"value"+e.getValue());
			Map<String,String> internalMap = e.getValue();
			System.out.println("internalmap is"+internalMap);
			if(internalMap.get(headerList.get(1)).equals("Y")){
				System.out.println("TestCaseId "+internalMap.get(headerList.get(2)));
				System.out.println("TestCaseName "+internalMap.get(headerList.get(3)));
				System.out.println("TestCaseDescription "+internalMap.get(headerList.get(4)));
				elementSendKeys(driver,userName,internalMap.get(headerList.get(5)));
				elementSendKeys(driver,passWord,internalMap.get(headerList.get(6)));
				elementClick(driver,btnCloseCookie);
				wait(driver,500);
				driver.findElement(btnSignIn).click();
				wait(driver,400);
				System.out.println("SignedIn user name is"+driver.findElement(txtSignedIn).getText());
				elementClick(driver,iconHamburger);
				elementClick(driver,btnCloseCookie);
				elementClick(driver,mainLink(internalMap.get(headerList.get(7))));
				elementClick(driver,subLink(internalMap.get(headerList.get(7)),internalMap.get(headerList.get(8))));
				wait(driver,200);	
				elementClick(driver,btnCloseCookie);
				elementClick(driver,subLinkNavigation(internalMap.get(headerList.get(8))));
				wait(driver,400);
				elementClick(driver,btnCloseCookie);			
				Assert.assertEquals(internalMap.get(headerList.get(8)), getElementText(driver,txtHeader));
				Assert.assertEquals(internalMap.get(headerList.get(9)), getElementText(driver,subTxtDescription));
				wait(driver,400);
				HashMap<String,String> productDetails = new HashMap();
				try{
					int n=1;
					for(int i=0;i<n;i++){
						System.out.println("skudetails"+skuDetails(internalMap.get(headerList.get(10))));
						try{
						if(driver.findElement(skuDetails(internalMap.get(headerList.get(10)))).isDisplayed()){
							System.out.println("Inside if");
							wait(driver,100);
							File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(scrFile, new File("screenshot.png"));
							JavascriptExecutor jse = (JavascriptExecutor)driver;
							jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(productDescription(internalMap.get(headerList.get(10)))));
							productDetails.put("ProductDescription",getElementText(driver,productDescription(internalMap.get(headerList.get(10)))));
							System.out.println("model is"+getElementText(driver,modelPart(internalMap.get(headerList.get(10)))));
							productDetails.put("ModelPart",getElementText(driver,modelPart(internalMap.get(headerList.get(10)))));
							Assert.assertTrue(getElementText(driver,modelPart(internalMap.get(headerList.get(10)))).contains(internalMap.get(headerList.get(11))));
							productDetails.put("YourPrice",getElementText(driver,yourPrice(internalMap.get(headerList.get(10)))));
							Assert.assertTrue(getElementText(driver,yourPrice(internalMap.get(headerList.get(10)))).contains(internalMap.get(headerList.get(12))));
							productDetails.put("ListPrice",getElementText(driver,listPrice(internalMap.get(headerList.get(10)))));
							Assert.assertTrue(getElementText(driver,listPrice(internalMap.get(headerList.get(10)))).contains(internalMap.get(headerList.get(13))));
							productDetails.put("StandardShipping",getElementText(driver,standardShipping(internalMap.get(headerList.get(10)))));
							productDetails.put("PickUpAvailability",getElementText(driver,pickUpAvailability(internalMap.get(headerList.get(10)))));
							productDetails.put("ZipCode",getElementText(driver,zipCode(internalMap.get(headerList.get(10)))));
							System.out.println(driver.findElement(btnAddToCart(internalMap.get(headerList.get(10)))).getAttribute("outerHTML"));
							driver.findElement(btnAddToCart(internalMap.get(headerList.get(10)))).getAttribute("outerHTML").contains("disabled");	
							elementClick(driver,image(internalMap.get(headerList.get(10))));
							Assert.assertEquals(productDetails.get("ProductDescription"),getElementText(driver,prodDescription) );
							Assert.assertEquals(productDetails.get("ModelPart"),getElementText(driver,detailsModel));
							Assert.assertEquals(productDetails.get("YourPrice"),getElementText(driver,detailsYourPrice));
							Assert.assertEquals(productDetails.get("ListPrice"),getElementText(driver,detailsListPrice));
							Assert.assertEquals(productDetails.get("StandardShipping"),getElementText(driver,detailsShip));
							Assert.assertEquals(productDetails.get("PickUpAvailability"),getElementText(driver,detailsPick));
							File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(scrFile, new File("screenshot.png"));
						}
						}
						catch(Exception e2){
							e2.printStackTrace();
							System.out.println("Inside else");
							JavascriptExecutor jse = (JavascriptExecutor)driver;
							jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(btnNext));
							elementClick(driver,btnNext);
							wait(driver,400);
							elementClick(driver,btnCloseCookie);
							n++;
						}
						elementClick(driver,txtSignedIn);
						wait(driver,400);
						elementClick(driver,logout);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
					System.out.println("Catallog not found/error occured during operation");
				}
			}        		
		}

	}

	private static void elementClick(WebDriver driver,By locator){
		driver.findElement(locator).click();
	}

	private static void elementSendKeys(WebDriver driver,By locator,String value){
		driver.findElement(locator).sendKeys(value);
	}

	private static String getElementText(WebDriver driver,By locator){
		String value = driver.findElement(locator).getText();
		return value;
	}

	private static void wait(WebDriver driver, int value){
		driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
	}
}
