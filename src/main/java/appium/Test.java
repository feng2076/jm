package appium;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test {
	 public static void main(String[] args) throws MalformedURLException, InterruptedException {

	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("deviceName", "Android Emulator");
	        capabilities.setCapability("automationName", "Appium");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("platformVersion", "7.1");
	        capabilities.setCapability("appPackage", "com.kdlc.xyjk");
	        capabilities.setCapability("appActivity", "com.kdlc.mcc.component.SelectURLAtctivity");

	        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

	        driver.findElement(By.id("com.kdlc.xqb:id/et_git")).sendKeys("dev-321-yao-mjb-41adks.dev");
	        driver.findElement(By.id("com.android.bbkcalculator:id/digit5")).click();
	        driver.findElement(By.id("com.android.bbkcalculator:id/digit9")).click();
	        driver.findElement(By.id("com.android.bbkcalculator:id/clear1")).click();
	        driver.findElement(By.id("com.android.bbkcalculator:id/plus")).click();
	        driver.findElement(By.id("com.android.bbkcalculator:id/digit6")).click();
	        driver.findElement(By.id("com.android.bbkcalculator:id/equal")).click();
	        Thread.sleep(2000);

	        String result = driver.findElement(By.id("com.android.bbkcalculator:id/edit_result_text")).getText();
	        System.out.println(result);

	        driver.quit();
	    }
}
