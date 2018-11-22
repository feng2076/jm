package selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class Check {
    @Test
	public static String check(String id,String url){
		
		System.setProperty("webdriver.firefox","D:/firefox/firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		WebElement name = driver.findElement(By.name("LoginForm[username]"));
		name.sendKeys("admin");
		WebElement pw = driver.findElement(By.name("LoginForm[password]"));
		pw.sendKeys("123456");
		WebElement btn = driver.findElement(By.name("submit_btn"));
		btn.click();
		WebElement jkgl=driver.findElement(By.id("header_loan"));
		jkgl.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().frame("main");
		WebElement link=driver.findElement(By.xpath("//a[@href='/backend/web/index.php?r=pocket%2Fcheck-status&id="+id+"']"));
		link.click();
		try {
			Alert alt = driver.switchTo().alert();
			alt.accept();
		} catch (Exception e) {
			System.out.println("跳过审核错误");
		}
		driver.switchTo().defaultContent();
		WebElement rgcs=driver.findElement(By.xpath("//a[@href='/backend/web/index.php?r=pocket%2Fother-trail-list']"));
		rgcs.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().frame("main");
		WebElement dycsh=driver.findElement(By.xpath("/backend/web/index.php?r=pocket%2Fpocket-first-trail&id="+id+"&view=other"));
		dycsh.click();
		WebElement tj=driver.findElement(By.id("submit_btn"));
		tj.click();
		driver.close();
		
//		} catch (Exception e) {
//			return "初审失败";
//		}
		return "初审成功";
	}
}
