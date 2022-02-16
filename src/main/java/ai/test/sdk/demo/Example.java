package ai.test.sdk.demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ai.test.sdk.TestAiDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * A brief example demonstrating the capabilities of test.ai enhanced Selenium.
 * 
 * @author Alexander Wu (alec@test.ai)
 *
 */
public class Example
{
	/**
	 * Main driver
	 * 
	 * @param args Program arguments, should be an Array of length 1 containing a String with the user's API key.
	 * @throws Throwable if anything went wrong
	 */
	public static void main(String[] args) throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver chromeDriver = new ChromeDriver();

		try
		{
			TestAiDriver driver = new TestAiDriver(chromeDriver, args[0]);
			driver.get("https://testaistore.com/");

			Thread.sleep(2000);

			WebElement storeNavLink = driver.findElementById("menu-item-45", "store_nav_link");
			storeNavLink.click();

			Thread.sleep(2000);

			WebElement searchProductsInput = driver.findElementByCssSelector("#woocommerce-product-search-field-0", "search_products_input");
			searchProductsInput.sendKeys("Shoes");

			Thread.sleep(2000);

			WebElement searchButton = driver.findElementByXPath("//*[@id=\"woocommerce_product_search-4\"]/form/button", "search_button");
			searchButton.click();

			Thread.sleep(2000);

			WebElement greenShoes = driver.findElementByXPath("//*[@id=\"main\"]/div/ul/li[2]/div[1]/a", "green_shoes");
			greenShoes.click();

			Thread.sleep(2000);

			WebElement addToCart = driver.findElementByXPath("//*[@id=\"product-2719\"]/div[2]/form/button", "add_to_cart");
			addToCart.click();

			Thread.sleep(5000);
		}
		finally
		{
			chromeDriver.quit();
		}
	}
}
