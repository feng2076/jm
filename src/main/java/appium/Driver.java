package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class Driver {
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void DriverClick(AndroidDriver driver,String filepath){
        boolean isAlive=driver.findElementById(filepath).isDisplayed();
        if(isAlive){
            driver.findElementById(filepath).click();
        }else{
            System.out.println(filepath+"没定位到相关元素");
        }
    }
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void DriverSetValue(AndroidDriver driver,String filepath,String value){
        boolean isAlive=driver.findElementById(filepath).isDisplayed();
        if(isAlive){
            driver.findElementById(filepath).sendKeys(value);
        }else{
            System.out.println(filepath+"没定位到相关元素");
        }
    }
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public  static boolean  DriverIsDisplayed(AndroidDriver driver,String filepath){
        boolean isAlive=true;
        try {
            isAlive=driver.findElementById(filepath).isDisplayed();
        }catch (Exception e){
            isAlive=false;
        }
        return isAlive;
    }
    /* 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static WebElement DriverFindElement(AndroidDriver driver, String filepath){
        WebElement web=null;
        try{
            web=driver.findElementById(filepath);
        }catch (Exception e){
            System.out.println(filepath+"没定位到相关元素");
        }
        return web;
    }
}
