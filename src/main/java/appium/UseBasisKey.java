package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
//用来运行基础键盘
public class UseBasisKey {
    /* 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void key(AndroidDriver driver,String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        java.util.HashMap<String, Integer> keycode= new java.util.HashMap<String, Integer>();
        String [] num=value.split(",");
        int code=0;
        for (int i = 0;i<num.length;i++){
               code=Integer.valueOf(num[i]);
            keycode.put("keycode", code);
            js.executeScript("mobile: keyevent",keycode);
        }
    }
}
//        KEYCODE_0 按键'0'7
//        KEYCODE_1 按键'1'8
//        KEYCODE_2 按键'2'9
//        KEYCODE_3 按键'3'10
//        KEYCODE_4 按键'4'11
//        KEYCODE_5 按键'5'12
//        KEYCODE_6 按键'6'13
//        KEYCODE_7 按键'7'14
//        KEYCODE_8 按键'8'15
//        KEYCODE_9 按键'9'16
//15995106013     8,12,16,16,12,8,7,13,7,8,10