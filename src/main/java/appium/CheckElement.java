package appium;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class CheckElement {
	/* 
	 * @Description:  
	 * @Param:  
	 * @return:  
	 * @Author: xufeng 
	 * @Date: 2018/11/5 
	 */
   public static boolean isAlive(String element,AndroidDriver driver){
	   try {
		   driver.findElement(By.id(element));
		   return true;
	} catch (Exception e) {
		return false;
	}
   }
}
