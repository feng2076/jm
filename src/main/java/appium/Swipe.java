package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
 //原本自带的swipe已经取消，单独写个滑动接口
public class Swipe {
/* 
 * @Description:
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    // 滑动
    public static void Swipe(AndroidDriver driver,int num){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        java.util.HashMap<String, Double> swipeObj = new java.util.HashMap<String, Double>();
        swipeObj.put("startX", 0.8);
        swipeObj.put("startY", 0.5);
        swipeObj.put("endX", 0.2);
        swipeObj.put("endY", 0.5);
        for (int i = 0; i < num; i++) {
            try {
                js.executeScript("mobile: flick", swipeObj);
            } catch (Exception ex) {
                System.out.println("滑动屏幕失败");
            }
        }
    }
}